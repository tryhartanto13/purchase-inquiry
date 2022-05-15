package com.th.purchase.inquiry.service;

import com.th.purchase.inquiry.dto.UserAuditRq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserAuditService {
    @Autowired
    private JmsTemplate jmsTemplate;

    @Value("${user.audit.topic}")
    private String topic;

    public void send(UserAuditRq userAuditRq) {
        jmsTemplate.convertAndSend(topic, userAuditRq);
    }

}
