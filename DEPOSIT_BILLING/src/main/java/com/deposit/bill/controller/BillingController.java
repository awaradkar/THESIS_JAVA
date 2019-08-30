package com.deposit.bill.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.deposit.bill.service.BillingService;

@CrossOrigin("*")
@RestController
@RequestMapping("api/v1/billing")
public class BillingController {

	@Autowired
	BillingService billingService;
	
	@RequestMapping(method = RequestMethod.POST, produces = {"application/json"})
	public ResponseEntity<Object> runBilling(){
		
		String responseStr = billingService.runBilling();
		
		return new ResponseEntity<>(responseStr, HttpStatus.OK);
	}
	
}
