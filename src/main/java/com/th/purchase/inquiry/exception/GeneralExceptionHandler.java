package com.th.purchase.inquiry.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GeneralExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler({GeneralException.class})
    public ResponseEntity<ApiError> genericException(GeneralException generalException) {
        return new ResponseEntity<>(ApiError.builder()
                .errCode(generalException.getErrCode())
                .errDesc(generalException.getErrDesc())
                .refNo(generalException.getRefNo())
                .build(), HttpStatus.BAD_REQUEST);
    }
}
