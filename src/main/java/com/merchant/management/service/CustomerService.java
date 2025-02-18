package com.merchant.management.service;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.merchant.management.entity.CustomerDetails;
import com.merchant.management.entity.MerchantDetails;
import com.merchant.management.entity.Role;
import com.merchant.management.repository.CustomerRepository;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	public CustomerService(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}
 
	public CustomerDetails saveCustomerDetails( CustomerDetails customerDetails) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        customerDetails.setCustomerCreationTime(timestamp.toString());
        long nanoTime = System.nanoTime();
        long uniqueNumber = nanoTime % 10_000_000;
        customerDetails.setCustomerUniqueNo(String.valueOf(uniqueNumber));
        customerDetails.setCustomerType("B");
        String defaultPassword = "customer@123";
        customerDetails.setCustomerPassword(passwordEncoder.encode(defaultPassword));
		return customerRepository.save(customerDetails);
	}
	
	public CustomerDetails getCustomerDetailsByPhNo(String customerPhNo) {
		CustomerDetails customerDetails = customerRepository.getcustomerDetailsByPhNo(customerPhNo);
		return customerDetails;
	}
	
	
	public String getFileName() {

        String fileName ="D:\\POC_API_DEVELOPMENT\\DATAFILE152.txt" ;
   
        return fileName;
    }
}
