package com.th.purchase.inquiry.exception;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class ApiError implements Serializable {
    private String errCode;
    private String errDesc;
    private String refNo;
}
