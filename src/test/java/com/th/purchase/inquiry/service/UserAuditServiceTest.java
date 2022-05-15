package com.th.purchase.inquiry.service;

import com.th.common.UserAuditRq;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.util.ReflectionTestUtils;

import static com.th.purchase.inquiry.dto.constant.PurchaseConstant.INQUIRY_ACTIVITY;
import static com.th.purchase.inquiry.dto.constant.PurchaseConstant.SUCCESS;

@RunWith(MockitoJUnitRunner.class)
public class UserAuditServiceTest {
    @InjectMocks
    private UserAuditService userAuditService;
    @Mock
    private JmsTemplate jmsTemplate;

    @Test
    public void send_expectSuccess() {
        ReflectionTestUtils.setField(userAuditService, "topic", "topic");
        userAuditService.send(UserAuditRq.builder().result(SUCCESS).refNo("123")
                .activity(INQUIRY_ACTIVITY).destNo("321").srcAcctNo("32111").build());
        Mockito.verify(jmsTemplate).convertAndSend(Mockito.any(String.class), Mockito.any(UserAuditRq.class));
    }
}
