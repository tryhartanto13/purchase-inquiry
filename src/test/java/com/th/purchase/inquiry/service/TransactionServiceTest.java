package com.th.purchase.inquiry.service;

import com.th.purchase.inquiry.dto.TransactionRq;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

import static com.th.purchase.inquiry.dto.constant.PurchaseConstant.TRANSACTION_INQUIRY;
import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
public class TransactionServiceTest {
    @InjectMocks
    private TransactionService transactionService;
    @Mock
    private RestTemplate restTemplate;

    @Test
    public void storeTransaction_expectSuccess() {
        ReflectionTestUtils.setField(transactionService, "urlTransaction", "test.com");
        transactionService.storeTransaction(TransactionRq.builder()
                .transactionStatus(TRANSACTION_INQUIRY)
                .amount(new BigDecimal("10000"))
                .biller("GOPAY")
                .destNo("321321")
                .srcAccountNo("321321")
                .fee(new BigDecimal("1000"))
                .refNo("321")
                .destName("Mra").build()
        );
        Mockito.verify(restTemplate).postForEntity(any(String.class), any(TransactionRq.class), any());
    }
}
