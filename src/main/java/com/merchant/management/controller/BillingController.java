package com.merchant.management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.merchant.management.dto.MerchantDetailRes;
import com.merchant.management.entity.BillingEntity;
import com.merchant.management.entity.BillingEntityRes;
import com.merchant.management.entity.CustomerDetails;
import com.merchant.management.entity.CustomerDetailsRes;
import com.merchant.management.entity.MerchantDetails;
import com.merchant.management.service.BillingService;

@RestController
@RequestMapping("/services/v1/billing")
@CrossOrigin
public class BillingController {
	
	@Autowired
	private BillingService billingService;
	
	@PostMapping("/billCustomer")
	public ResponseEntity payBills(@RequestBody BillingEntity billingEntity) {
		     BillingEntityRes response  = new BillingEntityRes();		   
		   if(billingService.billingService(billingEntity) != null) {
			   response.setResponse("success");
		   }else {
			   response.setErrorCode("Bill not paid successfully");
			   response.setErrorCode("ERR002") ;
			   response.setResponse("failure");
		   }
	      
	       return ResponseEntity.ok(response);    
	} 
    

}
