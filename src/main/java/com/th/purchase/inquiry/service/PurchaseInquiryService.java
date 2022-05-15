package com.th.purchase.inquiry.service;

import com.th.purchase.inquiry.dto.PurchaseInqRq;
import com.th.purchase.inquiry.dto.PurchaseInqRs;
import com.th.purchase.inquiry.dto.TransactionRq;
import com.th.purchase.inquiry.dto.UserAuditRq;
import com.th.purchase.inquiry.exception.GeneralException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

import static com.th.purchase.inquiry.dto.constant.PurchaseConstant.*;
import static com.th.purchase.inquiry.dto.constant.PurchaseConstant.ErrorCode.*;

@Service
@Slf4j
public class PurchaseInquiryService {
    @Autowired
    private UserAuditService userAuditService;
    @Autowired
    private TransactionService transactionService;

    public PurchaseInqRs doInquiry(PurchaseInqRq purchaseInqRq){
        String result = SUCCESS;
        PurchaseInqRs purchaseInqRs = null;
        String refNo = UUID.randomUUID().toString();
        TransactionRq transactionRq = TransactionRq.builder().refNo(refNo)
                .srcAccountNo(purchaseInqRq.getSrcAccountNo())
                .destNo(purchaseInqRq.getDestNo())
                .biller(purchaseInqRq.getBiller())
                .amount(purchaseInqRq.getAmount())
                .build();
        try {
            inquiry3rd(transactionRq);
            transactionService.storeTransaction(transactionRq);
            return PurchaseInqRs.builder().destNo(transactionRq.getDestNo())
                    .refNo(transactionRq.getRefNo())
                    .destName(transactionRq.getDestName())
                    .fee(transactionRq.getFee())
                    .amount(transactionRq.getAmount())
                    .build();
        } catch (Exception ex) {
            log.error("Exception happened when purchase inquiry, message: [{}]", ex.getMessage());
            throw errorHandler(ex, result);
        } finally {
            userAuditService.send(UserAuditRq.builder().srcAcctNo(purchaseInqRq.getSrcAccountNo()).destNo(purchaseInqRq.getDestNo()).build());
        }
    }

    private void inquiry3rd(TransactionRq transactionRq){
        if(transactionRq.getBiller().equals("GOPAY")) {
            transactionRq.setDestName("Mira");
            transactionRq.setFee(new BigDecimal("1000"));
            transactionRq.setTransactionStatus(TRANSACTION_INQUIRY);
        }else {
            throw GeneralException.builder().refNo(transactionRq.getRefNo())
                    .errCode(SRC_SYSTEM.concat("-").concat(ERR_CODE))
                    .errDesc(ERR_DESC).build();
        }
    }

    private GeneralException errorHandler(Exception ex, String result) {
        String errCode = DEFAULT_ERR_CODE;
        String errDesc = DEFAULT_ERR_DESC;
        String refNo = "";
        if (ex instanceof GeneralException) {
            errCode = ((GeneralException) ex).getErrCode();
            errDesc = ((GeneralException) ex).getErrDesc();
            refNo = ((GeneralException) ex).getRefNo();
        }
        result = errCode;
        return GeneralException.builder().errCode(errCode).errDesc(errDesc).refNo(refNo).build();
    }
}
