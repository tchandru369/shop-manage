package com.merchant.management.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.merchant.management.dto.CustDetailSummary;
import com.merchant.management.dto.CustOverAllPymtStatusRes;
import com.merchant.management.dto.CustProdPriceCount;
import com.merchant.management.dto.CustomerGraphEntityRes;
import com.merchant.management.dto.OrderRequestDto;
import com.merchant.management.dto.UserCustBalDto;
import com.merchant.management.dto.UserCustDetailsRes;
import com.merchant.management.dto.UserCustLastTransaction;
import com.merchant.management.entity.BillingEntityRes;
import com.merchant.management.entity.CustomerDetails;
import com.merchant.management.entity.CustomerDetailsRes;
import com.merchant.management.entity.ShopCustomerDetails;
import com.merchant.management.repository.CustomerRepository;
import com.merchant.management.service.CustomerService;
import com.merchant.management.service.OrderService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/services/v1/customer")
@CrossOrigin
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	 
	@Autowired 
	private CustomerRepository customerRepository;
	
	@Autowired
	private OrderService orderService;
	
//	@PostMapping("/saveCustomer")
//	public ResponseEntity<CustomerDetailsRes> saveMerchant(@RequestBody CustomerDetails customerDetails) {
//		   CustomerDetailsRes response  = new CustomerDetailsRes();
//	       customerService.
//	       response.setResponse("success");
//	       return saveCustomerDetails(customerDetails);
//	       
//	}
	
	@PostMapping("/saveShopCust")
	public ResponseEntity<CustomerDetailsRes> saveShopCustomer(@RequestBody ShopCustomerDetails customerDetails) {
		   CustomerDetailsRes response  = new CustomerDetailsRes();
	      return customerService.saveShopCustomer(customerDetails);
	       //return ResponseEntity.ok(response);
	       
	}
	
	@PostMapping("/cust/placeOrder")
	public ResponseEntity paidOrderReq(@RequestBody OrderRequestDto custOrderDtls) {
		     BillingEntityRes response  = orderService.customerPlaceOrder(custOrderDtls);		   
	       return ResponseEntity.ok(response);    
	}
	
	@PostMapping("/owner/aprvPlaceOrder")
	public ResponseEntity approveCustPlaceOrder(@RequestBody OrderRequestDto custOrderDtls) {
		     BillingEntityRes response  = orderService.approveCustPlacedOrder(custOrderDtls);		   
	       return ResponseEntity.ok(response);    
	}
	
	@PostMapping("/owner/rejctPlaceOrder")
	public ResponseEntity rejectCustPlaceOrder(@RequestBody OrderRequestDto custOrderDtls) {
		     BillingEntityRes response  = orderService.rejectCustPlacedOrder(custOrderDtls);		   
	       return ResponseEntity.ok(response);    
	}
	
	
	@GetMapping("/getCustomer")
	public ResponseEntity getCustomerForBilling(@RequestParam String custPhNo){
		CustomerDetailsRes customerDetailsRes = new CustomerDetailsRes();
		ShopCustomerDetails custDetails = customerService.getCustomerDetailsByPhNo(custPhNo);
		if(custDetails == null) {
			customerDetailsRes.setErrorCode("ERR001");
			customerDetailsRes.setErrorMsg("Customer Not Found");
			customerDetailsRes.setResponse("failure");
			return ResponseEntity.ok(customerDetailsRes);
		}else {
			return ResponseEntity.ok(custDetails);
		}
		
	}
	
	@GetMapping("/owner/getCustomer")
	public List<UserCustDetailsRes> getCustomerDtlsByUsrEmail(@RequestParam String email){
		CustomerDetailsRes customerDetailsRes = new CustomerDetailsRes();
		List<UserCustDetailsRes> custDetails = customerService.getCustomerDetailsByOwnerE(email);
		return custDetails;	
	}
	
	@GetMapping("/cust/getGraph")
	public List<CustomerGraphEntityRes> getCustGraphEntity(@RequestParam String custEmail){
		return customerService.getCustGraphEntity(custEmail);
	}
	
	@GetMapping("/cust/getProdCount")
	public List<CustProdPriceCount> getCustProductCount(@RequestParam String custEmail){
		return customerService.getCustProdCount(custEmail);
	}
	
	@GetMapping("/owner/getCustSummary")
	public ResponseEntity getDetailsByCustUserE(@RequestParam String custEmail,@RequestParam String ownerEmail){
		CustomerDetailsRes customerDetailsRes = new CustomerDetailsRes();
		CustDetailSummary custSummary = customerService.getCustDetailSummary(ownerEmail,custEmail);
		if(custSummary == null) {
			customerDetailsRes.setErrorCode("ERR001");
			customerDetailsRes.setErrorMsg("Customer Not Found");
			customerDetailsRes.setResponse("failure");
			return ResponseEntity.ok(customerDetailsRes);
		}else {
			return ResponseEntity.ok(custSummary);	
		}
	}
	
	@GetMapping("/cust/getCustomer")
	public ResponseEntity getCustomerForPlaceOrder(@RequestParam String custEmail){
		CustomerDetailsRes customerDetailsRes = new CustomerDetailsRes();
		System.out.println("Get Customer Details First Summary");
		ShopCustomerDetails custDetails = customerService.getCustDtlsPhEmail(custEmail);
		if(custDetails == null) {
			customerDetailsRes.setErrorCode("ERR001");
			customerDetailsRes.setErrorMsg("Customer Not Found");
			customerDetailsRes.setResponse("failure");
			return ResponseEntity.ok(customerDetailsRes);
		}else {
			System.out.println("Get Customer Details End Summary");
			return ResponseEntity.ok(custDetails);
		}
		
		
	}
	
	@GetMapping("/owner/custOrderPlaced")
	public List<OrderRequestDto> getOrderRequest(@RequestParam String email) {
		List<OrderRequestDto> orderList = orderService.getCustPlacedOrderForOwner(email);
		return orderList;
	}
	
	@GetMapping("/cust/custOrderApproved")
	public List<OrderRequestDto> getCustOrderRequestApproved(@RequestParam String email,@RequestParam String ownerEmail,@RequestParam String date) {
		List<OrderRequestDto> orderList = orderService.getCustOrderRequestApproved(email,ownerEmail,date);
		return orderList;
	}
	
	@GetMapping("/cust/custOrderRejected")
	public List<OrderRequestDto> getCustOrderRequestRejected(@RequestParam String email,@RequestParam String ownerEmail,@RequestParam String date) {
		List<OrderRequestDto> orderList = orderService.getCustOrderRequestApproved(email,ownerEmail,date);
		return orderList;
	}
	
	@GetMapping("/cust/getcustLastTrans")
	public List<CustOverAllPymtStatusRes> getCustLastTransaction(@RequestParam String custEmail,@RequestParam String ownerEmail) {
		List<CustOverAllPymtStatusRes> custPymtList = customerService.getCustOverAllStatusList(custEmail,ownerEmail);
		return custPymtList;
	}
	
	@GetMapping("/cust/getcustLastOrderDts")
	public List<OrderRequestDto> getCustLastOrderDetails(@RequestParam String custEmail,@RequestParam String ownerEmail) {
		List<OrderRequestDto> custPymtList = customerService.getLastOrderStatus(custEmail,ownerEmail);
		return custPymtList;
	}
	
	
	@GetMapping("/downloadZip/{customerId}")
    public void downloadFile(@PathVariable("customerId") String customerId, // Path parameter
            @RequestHeader("BD-TraceId") String authHeader,HttpServletResponse response) {
        
		
		if (authHeader == null || !authHeader.startsWith("HMAC ")) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
		
		System.out.println(authHeader);
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment;filename=download.zip");
        response.setStatus(HttpServletResponse.SC_OK);

        String fileName = customerService.getFileName();

        System.out.println("############# file size ###########");

        try (ZipOutputStream zippedOut = new ZipOutputStream(response.getOutputStream())) {
           
                FileSystemResource resource = new FileSystemResource(fileName);

                ZipEntry e = new ZipEntry(resource.getFilename());
                // Configure the zip entry, the properties of the file
                e.setSize(resource.contentLength());
                e.setTime(System.currentTimeMillis());
                // etc.
                zippedOut.putNextEntry(e);
                // And the content of the resource:
                StreamUtils.copy(resource.getInputStream(), zippedOut);
                zippedOut.closeEntry();
            
            zippedOut.finish();
        } catch (Exception e) {
            // Exception handling goes here
        }
    }
}


