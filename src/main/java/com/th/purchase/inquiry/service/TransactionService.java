package com.th.purchase.inquiry.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TransactionService {

    @Autowired
    private RestTemplate restTemplate;
}
