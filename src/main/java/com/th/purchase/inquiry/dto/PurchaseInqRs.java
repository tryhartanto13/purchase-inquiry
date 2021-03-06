package com.th.purchase.inquiry.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class PurchaseInqRs {
    private String refNo;
    private String destNo;
    private String destName;
    private BigDecimal amount;
    private BigDecimal fee;
}
