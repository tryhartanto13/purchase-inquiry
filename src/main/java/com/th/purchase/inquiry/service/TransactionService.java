package com.th.purchase.inquiry.service;

import com.th.purchase.inquiry.dto.TransactionRq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TransactionService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${url.transaction}")
    private String urlTransaction;

    public void storeTransaction(TransactionRq transactionRq) {
        restTemplate.postForEntity(urlTransaction.concat("/v1/storetrx"), transactionRq, HttpStatus.class);
    }
}
