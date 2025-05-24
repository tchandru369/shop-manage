package com.merchant.management.service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.merchant.management.entity.CustomerDetails;
import com.merchant.management.entity.CustomerDetailsRes;
import com.merchant.management.entity.MerchantDetails;
import com.merchant.management.entity.Role;
import com.merchant.management.entity.ShopCustomerDetails;
import com.merchant.management.repository.CustomerRepository;
import com.merchant.management.repository.MerchantRepository;
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
	private MerchantRepository merchantRepository;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	public CustomerService(CustomerRepository customerRepository, ShopCustomerRepo shopCustRepo) {
		this.customerRepository = customerRepository;
		this.shopCustRepo = shopCustRepo;
	}
 
//	public ResponseEntity saveCustomerDetails( CustomerDetails customerDetails) {
//        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
//        MerchantDetails merchantDetails = new MerchantDetails();
//        
//        customerDetails.setCustomerCreationTime(timestamp.toString());
//        long nanoTime = System.nanoTime();
//        long uniqueNumber = nanoTime % 10_000_000;
//        customerDetails.setCustomerUniqueNo(String.valueOf(uniqueNumber));
//        customerDetails.setCustomerType("N");
//        String defaultPassword = "customer@123";
//        customerDetails.setCustomerPassword(passwordEncoder.encode(defaultPassword));
//        merchantDetails.setMerchantAddress(customerDetails.getCustomerAddress());
//        merchantDetails.setMerchantEmail(customerDetails.getCustomerEmail());
//        merchantDetails.setMerchantPassword(passwordEncoder.encode(defaultPassword));
//        merchantDetails.setmerchantPhoneNumber(customerDetails.getCustomerPhoneNo());
//        merchantDetails.setMerchantSignUpTime(timestamp.toString());
//        merchantDetails.setmerchantUserName(customerDetails.getCustomerName());
//        merchantDetails.setMerchantUserType("CUST");
//        merchantDetails.setRole(Role.Cust);
//        merchantRepository.save(merchantDetails);
//        
//		return customerRepository.save(customerDetails);
//	}
	
	public ResponseEntity<CustomerDetailsRes> saveShopCustomer( ShopCustomerDetails customerDetails) {
		 CustomerDetailsRes custRes = new CustomerDetailsRes();
		int merchantCount = merchantRepository.getMerchantCount(customerDetails.getCustEmailId());
		if(merchantCount!=0) {
			custRes.setResponse("success");
			custRes.setErrorMsg("customer already present as Owner");
			custRes.setErrorCode("1");
		}else {
			int custEmailCount = shopCustRepo.getCustMailCount(customerDetails.getCustEmailId());
			if(custEmailCount!=0) {
				custRes.setResponse("success");
				custRes.setErrorMsg("Email already registered as Customer");
				custRes.setErrorCode("1");
			}else {
				String custPhEmail = shopCustRepo.getCustPhoneCount(customerDetails.getCustPhoneNo());
				if(custPhEmail!=null) {
					custRes.setResponse("success");
					custRes.setErrorMsg("Phone already registered with "+custPhEmail);
					custRes.setErrorCode("1");
				}else {
					Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			        MerchantDetails merchantDetails = new MerchantDetails();
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
			        merchantDetails.setMerchantAddress(customerDetails.getCustAddress());
			        merchantDetails.setMerchantEmail(customerDetails.getCustEmailId());
			        merchantDetails.setMerchantPassword(passwordEncoder.encode(defaultPassword));
			        merchantDetails.setmerchantPhoneNumber(customerDetails.getCustPhoneNo());
			        merchantDetails.setMerchantSignUpTime(timestamp.toString());
			        merchantDetails.setmerchantUserName(customerDetails.getCustName());
			        merchantDetails.setMerchantUserType("CUST");
			        merchantDetails.setRole(Role.Cust);
			        merchantRepository.save(merchantDetails);
			        shopCustRepo.save(customerDetails);
			        emailService.sendCustomerEmail(customerDetails.getCustEmailId(), customerDetails.getCustName(),customerDetails.getCustOwmerDetails(), defaultPassword);
			        custRes.setErrorCode("0");
			        custRes.setResponse("success");
			        custRes.setErrorMsg("success");
				}
				
			}
			
		}
		 
		return ResponseEntity.ok(custRes);
	}
	
	public ShopCustomerDetails getCustomerDetailsByPhNo(String customerPhNo) {
		ShopCustomerDetails customerDetails = shopCustRepo.getShopCustDetailsByPhNo(customerPhNo);
		return customerDetails;
	}
	
	
	public String getFileName() {

        String fileName ="D:\\POC_API_DEVELOPMENT\\DATAFILE152.txt";
   
        return fileName;
    }
}
