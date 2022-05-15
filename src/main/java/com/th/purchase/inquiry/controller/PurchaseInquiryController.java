package com.th.purchase.inquiry.controller;

import com.th.purchase.inquiry.dto.PurchaseInqRq;
import com.th.purchase.inquiry.dto.PurchaseInqRs;
import com.th.purchase.inquiry.service.PurchaseInquiryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/v1")
@Slf4j
public class PurchaseInquiryController {

    @Autowired
    private PurchaseInquiryService piService;

    @PostMapping(value = "/inquiry")
    @ResponseBody
    public PurchaseInqRs inquiry(@RequestBody PurchaseInqRq purchaseInqRq){
        return piService.doInquiry(purchaseInqRq);
    }

}
