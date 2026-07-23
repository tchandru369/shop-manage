package com.merchant.management.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.merchant.management.dto.BillingHistoryRes;
import com.merchant.management.dto.CustRewardDetailsRes;

@RestController
@RequestMapping("/services/v1/reward")
@CrossOrigin
public class RewardController {
	
	@GetMapping("/rewardCredit")
	public  List<BillingHistoryRes> viewProducts(@RequestParam String email){
//		//List<BillingHistoryRes> billingHistList = billingService.getBillHistoryDetails(email);
//		for(int i=0;i<billingHistList.size();i++) {
//			System.out.println(billingHistList.get(i).getCustInvoiceIdRes());
//		}
		return null;
	}
	
	@PostMapping("/rewardCredit")
	public  ResponseEntity<CustRewardDetailsRes> custRewardCredit(@RequestParam String rewardType,@RequestParam String ownerRefId){
//		//List<BillingHistoryRes> billingHistList = billingService.getBillHistoryDetails(email);
//		for(int i=0;i<billingHistList.size();i++) {
//			System.out.println(billingHistList.get(i).getCustInvoiceIdRes());
//		}	
		
		return null;
	}
	
	

}
