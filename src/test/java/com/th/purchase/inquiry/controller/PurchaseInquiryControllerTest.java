package com.th.purchase.inquiry.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.th.purchase.inquiry.dto.PurchaseInqRq;
import com.th.purchase.inquiry.service.PurchaseInquiryService;
import com.th.purchase.inquiry.service.TransactionService;
import com.th.purchase.inquiry.service.UserAuditService;
import lombok.SneakyThrows;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class PurchaseInquiryControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PurchaseInquiryService purchaseInquiryService;

    @MockBean
    private UserAuditService userAuditService;

    @MockBean
    private TransactionService transactionService;

    @SneakyThrows
    @Test
    public void purchaseInquiry_expectSuccess() {
        mockMvc.perform(post("/v1/inquiry")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(PurchaseInqRq.builder()
                                .biller("GOPAY")
                                .amount(new BigDecimal("10000"))
                                .destNo("456456789")
                                .srcAccountNo("4567864568").build()
                        )))
                .andExpect(status().isOk());
    }

    @SneakyThrows
    @Test
    public void purchaseInquiry_expectError() {
        mockMvc.perform(post("/v1/inquiry")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(PurchaseInqRq.builder()
                                .biller("OVO")
                                .amount(new BigDecimal("10000"))
                                .destNo("456456789")
                                .srcAccountNo("4567864568").build()
                        )))
                .andExpect(status().isBadRequest());
    }
}
