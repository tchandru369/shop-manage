package com.merchant.management.service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.merchant.management.entity.CustomerDetails;
import com.merchant.management.entity.MerchantDetails;
import com.merchant.management.entity.Role;
import com.merchant.management.entity.ShopCustomerDetails;
import com.merchant.management.repository.CustomerRepository;
import com.merchant.management.repository.ShopCustomerRepo;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private ShopCustomerRepo shopCustRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	public CustomerService(CustomerRepository customerRepository, ShopCustomerRepo shopCustRepo) {
		this.customerRepository = customerRepository;
		this.shopCustRepo = shopCustRepo;
	}
 
	public CustomerDetails saveCustomerDetails( CustomerDetails customerDetails) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        customerDetails.setCustomerCreationTime(timestamp.toString());
        long nanoTime = System.nanoTime();
        long uniqueNumber = nanoTime % 10_000_000;
        customerDetails.setCustomerUniqueNo(String.valueOf(uniqueNumber));
        customerDetails.setCustomerType("N");
        String defaultPassword = "customer@123";
        customerDetails.setCustomerPassword(passwordEncoder.encode(defaultPassword));
		return customerRepository.save(customerDetails);
	}
	
	public ShopCustomerDetails saveShopCustomer( ShopCustomerDetails customerDetails) {
		LocalDate currentDate = LocalDate.now();
	     DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	     String formattedDate = currentDate.format(formatter);
        customerDetails.setCustCreatedDate(formattedDate);
        long nanoTime = System.nanoTime();
        long uniqueNumber = nanoTime % 10_000_000;
        customerDetails.setCustLive("1");
        customerDetails.setCustBalanceFlg("1");
        String defaultPassword = "customer@123";
        System.out.println("Customer Country : "+customerDetails.getCustCountry());
        customerDetails.setCustPassword(passwordEncoder.encode(defaultPassword));
		return shopCustRepo.save(customerDetails);
	}
	
	public ShopCustomerDetails getCustomerDetailsByPhNo(String customerPhNo) {
		ShopCustomerDetails customerDetails = shopCustRepo.getShopCustDetailsByPhNo(customerPhNo);
		return customerDetails;
	}
	
	
	public String getFileName() {

        String fileName ="D:\\POC_API_DEVELOPMENT\\DATAFILE152.txt" ;
   
        return fileName;
    }
}
