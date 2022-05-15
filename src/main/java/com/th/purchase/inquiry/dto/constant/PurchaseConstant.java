package com.th.purchase.inquiry.dto.constant;

public class PurchaseConstant {
    public static final String SUCCESS = "SUCCESS";
    public static final int TRANSACTION_FAILED = 0;
    public static final int TRANSACTION_INQUIRY = 1;
    public static final String INQUIRY_ACTIVITY = "Purchase Inquiry";
    public static class ErrorCode {
        private ErrorCode(){}
        public static final String SRC_SYSTEM="3RD";
        public static final String ERR_CODE = "01";
        public static final String ERR_DESC = "Invalid Biller";
        public static final String DEFAULT_ERR_CODE="INT-UE";
        public static final String DEFAULT_ERR_DESC="Unknown Error";
    }
}
