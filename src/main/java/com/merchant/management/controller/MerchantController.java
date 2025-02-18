package com.merchant.management.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.merchant.management.dto.MerchantDetailRes;
import com.merchant.management.entity.MerchantDetails;
import com.merchant.management.entity.ImageSrcDetail;
import com.merchant.management.repository.MerchantRepository;
import com.merchant.management.security.AuthenticationRequest;
import com.merchant.management.security.JwtService;
import com.merchant.management.service.MerchantServices;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin
public class MerchantController {
	
@Autowired
private MerchantRepository merchantRepository;
@Autowired
private MerchantServices merchantServices;
@Autowired
private JwtService jwtServices;
@Autowired
private AuthenticationManager authenticationManager;


@PostMapping("/merchants")
public ResponseEntity<MerchantDetailRes> saveMerchant(@RequestBody MerchantDetails merchant) {
	   MerchantDetailRes response  = new MerchantDetailRes();
       merchantServices.saveMerchantDetails(merchant);
       String jwtToken = jwtServices.generateToken(merchant);
       response.setResponse("success");
       response.setToken(jwtToken);
       return ResponseEntity.ok(response);
       
}


@GetMapping("/checkServer")
public ResponseEntity<MerchantDetailRes> checkServerStatus(){
	MerchantDetailRes response = new MerchantDetailRes();
	String password  = merchantServices.getMerchantPswdByMail("tchandru45@yahoo.com");
	response.setResponse("success");
	return ResponseEntity.ok(response);
}

@PostMapping("/merchant-login")
public ResponseEntity<MerchantDetailRes> merchantLogin(@RequestBody MerchantDetails merchant){
	MerchantDetailRes response = new MerchantDetailRes();
	merchant.setMerchantEmail(merchant.getMerchantEmail());
	merchant.setMerchantPassword(merchant.getMerchantPassword());
	authenticationManager.authenticate(
			new UsernamePasswordAuthenticationToken(merchant.getMerchantEmail(), merchant.getMerchantPassword())
			
			);
	
//	String merchantEntrMail = merchant.getMerchantEmail();
//	String merchantEntrPass = merchant.getMerchantPassword();
	//merchantServices.authMerchantPswdByMail(merchant);
	var user = merchantRepository.findBymerchantEmail(merchant.getUsername()).orElseThrow();
	String merchantDetails = merchantRepository.getMerchantDetails(merchant.getMerchantEmail());
	String merchantPhoto = merchantRepository.getMerchantPhototDetails(merchant.getMerchantPhoto());
    String jwtToken = jwtServices.generateToken(user);
    response.setResponse("success");
    response.setToken(jwtToken);
    response.setUserName(merchantDetails);
    response.setUserPhoto(merchantPhoto);
//	if(merchantEntrPass.equals(merchantServices.getMerchantPswdByMail(merchantEntrMail))) {
//	       String jwtToken = jwtServices.generateToken(merchant);
//	       response.setToken(jwtToken);
//		response .setResponse("success");
//	}else {
//		response.setResponse("failure");
//	}
	return ResponseEntity.ok(response);
}

@PostMapping("/authenticate")
public ResponseEntity<MerchantDetailRes> authenticate(@RequestBody MerchantDetails merchant){
	return ResponseEntity.ok(merchantServices.authenticate(merchant));
}


 

}
