package com.merchant.management.service;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.merchant.management.dto.MerchantDetailRes;
import com.merchant.management.entity.MerchantDetails;
import com.merchant.management.entity.Role;
import com.merchant.management.repository.MerchantRepository;
import com.merchant.management.repository.ShopCustomerRepo;
import com.merchant.management.security.JwtService;

@Service
public class MerchantServices {

	@Autowired
	private MerchantRepository merchantRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private AuthenticationManager authentication;
	@Autowired
	private JwtService jwtService;
	@Autowired
	private ShopCustomerRepo shopCustRepo;
	

	
	public ResponseEntity<MerchantDetailRes> saveMerchantDetails( MerchantDetails merchant) {
		MerchantDetailRes merchantRes = new MerchantDetailRes();
		int merchantCount = merchantRepository.getMerchantCount(merchant.getMerchantEmail());
		if(merchantCount!=0) {
			merchantRes.setResponse("success");
			merchantRes.setErrorMsg("customer already present as Owner");
			merchantRes.setErrorCode("1");
		}else {
			int custEmailCount = shopCustRepo.getCustMailCount(merchant.getMerchantEmail());
			if(custEmailCount != 0) {
				merchantRes.setResponse("success");
				merchantRes.setErrorMsg("Email already registered as Customer");
				merchantRes.setErrorCode("1");
			}else {
				String customerEmail = shopCustRepo.getCustPhoneCount(merchant.getmerchantPhoneNumber());
				if(customerEmail!=null) {
					merchantRes.setResponse("success");
					merchantRes.setErrorMsg("Phone number already registered as Customer with "+customerEmail);
					merchantRes.setErrorCode("1");
				}else {
					Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			        merchant.setMerchantSignUpTime(timestamp.toString());
			        merchant .setMerchantPassword(passwordEncoder.encode(merchant.getMerchantPassword()));
					merchant.setMerchantUserType("USER");
					merchant.setRole(Role.User);
					merchantRepository.save(merchant);
					merchantRes.setResponse("success");
					merchantRes.setErrorCode("0");
					merchantRes.setErrorMsg("Registered successfully, Please Login");
				}
				
			}
			
			
		}
		
		return ResponseEntity.ok(merchantRes);
	}
	
	public String getMerchantPswdByMail(String merchantMail) {
		String password = merchantRepository.findPasswordByEmail(merchantMail);
		return password;
	}

	public MerchantDetailRes authenticate(MerchantDetails merchant) {
		// TODO Auto-generated method stub
		MerchantDetailRes response = new MerchantDetailRes();
		String merchantDetails = merchantRepository.getMerchantDetails(merchant.getMerchantEmail());
		authentication.authenticate(
				new UsernamePasswordAuthenticationToken(merchant.getMerchantEmail(), merchant.getMerchantPassword())
				);
		var user = merchantRepository.findBymerchantEmail(merchant.getMerchantEmail()).orElseThrow();
		String jwtToken = jwtService.generateToken(user);
		response.setResponse("success");
		response.setToken(jwtToken);
		response.setUserName(merchantDetails);
		return response;
	}
	
	
public MerchantDetails getMerchantService(String email) {
		return merchantRepository.getMerchantProfileDetails(email);
	}

//	public MerchantDetails authMerchantPswdByMail(MerchantDetails merchant) {
//		
//		var user = merchantRepository.findBymerchantEmail(merchant.getMerchantEmail());
//		
//	
//		// TODO Auto-generated method stub
//		
//	}
}
