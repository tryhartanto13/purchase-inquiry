package com.th.purchase.inquiry.service;

import com.th.purchase.inquiry.dto.PurchaseInqRq;
import com.th.purchase.inquiry.dto.TransactionRq;
import com.th.purchase.inquiry.exception.GeneralException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;

@RunWith(MockitoJUnitRunner.class)
public class PurchaseInquiryServiceTest {
    @InjectMocks
    private PurchaseInquiryService purchaseInquiryService;
    @Mock
    private TransactionService transactionService;
    @Mock
    private UserAuditService userAuditService;

    @Test
    public void doInquiry_expectSuccess() {
        purchaseInquiryService.doInquiry(PurchaseInqRq.builder()
                .biller("GOPAY")
                .amount(new BigDecimal("10000"))
                .destNo("456456789")
                .srcAccountNo("4567864568").build());
        Mockito.verify(transactionService).storeTransaction(Mockito.any(TransactionRq.class));
    }

    @Test(expected = GeneralException.class)
    public void doInquiry_expectError() {
        purchaseInquiryService.doInquiry(PurchaseInqRq.builder()
                .biller("OVO")
                .amount(new BigDecimal("10000"))
                .destNo("456456789")
                .srcAccountNo("4567864568").build());
        Mockito.verify(transactionService).storeTransaction(Mockito.any(TransactionRq.class));
    }
}
