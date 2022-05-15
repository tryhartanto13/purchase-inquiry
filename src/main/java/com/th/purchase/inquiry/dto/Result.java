package com.th.purchase.inquiry.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class Result implements Serializable {
    private String result;
}
