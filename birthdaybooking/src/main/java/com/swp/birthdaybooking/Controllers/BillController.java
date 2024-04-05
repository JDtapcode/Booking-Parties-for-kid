package com.swp.birthdaybooking.Controllers;

import com.swp.birthdaybooking.Dtos.Request.CreateBillRq;
import com.swp.birthdaybooking.Dtos.Response.ResponseObject;
import com.swp.birthdaybooking.services.BillService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/bill")
public class BillController {
    private final BillService billService;

    public BillController(BillService billService) {
        this.billService = billService;
    }

    @PostMapping
    public ResponseEntity<ResponseObject> createBill(@RequestBody CreateBillRq billRq) {
        return ResponseEntity.ok(new ResponseObject("Successful", "Bill retrieved", billService.createBill(billRq)));
    }


}
