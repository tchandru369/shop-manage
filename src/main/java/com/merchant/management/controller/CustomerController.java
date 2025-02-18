package com.merchant.management.controller;

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

import com.merchant.management.entity.CustomerDetails;
import com.merchant.management.entity.CustomerDetailsRes;
import com.merchant.management.repository.CustomerRepository;
import com.merchant.management.service.CustomerService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/services/v1/customer")
@CrossOrigin
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	 
	@Autowired 
	private CustomerRepository customerRepository;
	
	@PostMapping("/saveCustomer")
	public ResponseEntity<CustomerDetailsRes> saveMerchant(@RequestBody CustomerDetails customerDetails) {
		   CustomerDetailsRes response  = new CustomerDetailsRes();
	       customerService.saveCustomerDetails(customerDetails);
	       response.setResponse("success");
	       return ResponseEntity.ok(response);
	       
	}
	
	
	@GetMapping("/getCustomer")
	public ResponseEntity getCustomerForBilling(@RequestParam String custPhNo){
		CustomerDetailsRes customerDetailsRes = new CustomerDetailsRes();
		CustomerDetails custDetails = customerService.getCustomerDetailsByPhNo(custPhNo);
		if(custDetails == null) {
			customerDetailsRes.setErrorCode("ERR001");
			customerDetailsRes.setErrorMsg("Customer Not Found");
			customerDetailsRes.setResponse("failure");
			return ResponseEntity.ok(customerDetailsRes);
		}else {
			return ResponseEntity.ok(custDetails);
		}
		
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


