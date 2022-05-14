package com.th.purchase.inquiry.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseInqRq implements Serializable {
    private String srcAccountNo;
    private String destNo;
    private String biller;
    private BigDecimal amount;
}
