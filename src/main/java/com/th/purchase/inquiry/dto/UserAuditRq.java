package com.th.purchase.inquiry.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class UserAuditRq implements Serializable {
    private static final long serialVersionUID = 1L;
    private String srcAcctNo;
    private String destNo;
    private String refNo;
    private String activity;
    private String result;
    private String request;
}
