package com.merchant.management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.merchant.management.dto.BillingHistoryRes;
import com.merchant.management.dto.MerchantDetailRes;
import com.merchant.management.dto.OrderRequestDto;
import com.merchant.management.entity.BillingEntity;
import com.merchant.management.entity.BillingEntityRes;
import com.merchant.management.entity.BillingHistory;
import com.merchant.management.entity.ComProdDtls;
import com.merchant.management.entity.CountryStateCity;
import com.merchant.management.entity.CustomerDetails;
import com.merchant.management.entity.CustomerDetailsRes;
import com.merchant.management.entity.MerchantDetails;
import com.merchant.management.entity.ProductDetails;
import com.merchant.management.entity.ShopCustOrderDetails;
import com.merchant.management.service.BillingService;
import com.merchant.management.service.OrderService;

@RestController
@RequestMapping("/services/v1/billing")
@CrossOrigin
public class BillingController {
	
	@Autowired
	private BillingService billingService;
	
	@Autowired
	private OrderService orderService;
	
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
	
	@GetMapping("/viewBillHistory")
	public  List<BillingHistoryRes> viewProducts(@RequestParam String email){
		List<BillingHistoryRes> billingHistList = billingService.getBillHistoryDetails(email);
		for(int i=0;i<billingHistList.size();i++) {
			System.out.println(billingHistList.get(i).getCustInvoiceIdRes());
		}
		return billingHistList;
	}
	
	@GetMapping("/getConStDtls")
	public List<CountryStateCity> getComProdDtls(){
		List<CountryStateCity> conStDtls = billingService.getConStDtls();
		return conStDtls;
	}
    
	@PostMapping("/addOrderReq")
	public ResponseEntity addReq(@RequestBody OrderRequestDto custOrderDtls) {
		     BillingEntityRes response  = orderService.saveCustOrderDetails(custOrderDtls);		   
	       return ResponseEntity.ok(response);    
	}
	
	@PostMapping("/OrderProcReq")
	public ResponseEntity orderProcReq(@RequestBody OrderRequestDto custOrderDtls) {
		     BillingEntityRes response  = orderService.orderProcessRequest(custOrderDtls);		   
	       return ResponseEntity.ok(response);    
	}
	
	@PostMapping("/deleteOrderReq")
	public ResponseEntity deleteReq(@RequestBody OrderRequestDto custOrderDtls) {
		     BillingEntityRes response  = orderService.deleteCustOrderDetails(custOrderDtls);		   
	       return ResponseEntity.ok(response);    
	}
	
	@PostMapping("/paidOrderReq")
	public ResponseEntity paidOrderReq(@RequestBody OrderRequestDto custOrderDtls) {
		     BillingEntityRes response  = orderService.paidOrderRequest(custOrderDtls);		   
	       return ResponseEntity.ok(response);    
	}
	
	@GetMapping("/getOrderReq")
	public List<OrderRequestDto> getOrderRequest(@RequestParam String email) {
		List<OrderRequestDto> orderList = orderService.getOrderDetails(email);
		return orderList;
	}
	
	@GetMapping("/getProcOrders")
	public List<OrderRequestDto> getProcessedOrders(@RequestParam String email) {
		List<OrderRequestDto> orderList = orderService.getProcessedOrders(email);
		return orderList;
	}
	
	@PostMapping("/deleteProcOrder")
	public ResponseEntity deleteProcReq(@RequestBody OrderRequestDto custOrderDtls) {
		     BillingEntityRes response  = orderService.deleteProcessOrderDetails(custOrderDtls);		   
	       return ResponseEntity.ok(response);    
	}
	
	

}
