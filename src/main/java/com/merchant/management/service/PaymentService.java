package com.merchant.management.service;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.merchant.management.dto.TransactionDetailsDto;
import com.merchant.management.entity.BillingEntityRes;
import com.razorpay.Account;
import com.razorpay.Order;
import com.razorpay.Payment;
import com.razorpay.RazorpayClient;
import com.razorpay.Transfer;

@Service
public class PaymentService {
	
	@Value("${razorpay.secret-key}")
	private String secretkey;
	
	@Value("${razorpay.secret-id}")
	private String secretId;
	
	private static String CURRENCY = "INR";
	
	
	
	public TransactionDetailsDto createTransaction(Double amount) {
		TransactionDetailsDto transact = new TransactionDetailsDto();
		try {
			
			JSONObject jsonObject = new JSONObject();
			jsonObject.put( "amount", (amount*100));
			jsonObject.put("currency", CURRENCY);
			
			RazorpayClient razorpayClient = new RazorpayClient(secretId,secretkey);
			
			Order order = razorpayClient.orders.create(jsonObject);
			
			System.out.println(order);
			transact = transactionDetails(order);
			

		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	    return transact;
	}
	
	private TransactionDetailsDto transactionDetails(Order order) {
		
		TransactionDetailsDto transaction = new TransactionDetailsDto();
		transaction.setAmount(order.get("amount"));
		transaction.setCurrency(order.get("currency"));
		transaction.setOrderId(order.get("id"));
		transaction.setKey(secretId);
		transaction.setResponse("success");

		
		return transaction;
		
	}
	
	public TransactionDetailsDto transactionToDealer(String dealerUpiId, double amount) {
		TransactionDetailsDto transaction = new TransactionDetailsDto();
		try {
	        RazorpayClient razorpayClient = new RazorpayClient(secretId, secretkey);
	        JSONObject payoutJson = new JSONObject();
	        payoutJson.put("amount", amount * 100); // Amount in smallest currency unit
	        payoutJson.put("currency", "INR");
	        payoutJson.put("upi_id", dealerUpiId);
	        payoutJson.put("purpose", "Payment to dealer");

	        // Create the payout
	        Account payoutResponse = razorpayClient.account.create(payoutJson);
	        transaction.setResponse("success");
	        return transaction;
	    } catch (Exception e) {
	    	System.out.println(e.getMessage());
	    }
		return null;
	}
	
	
	
	

}
