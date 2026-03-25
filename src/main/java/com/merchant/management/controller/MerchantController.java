package com.merchant.management.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
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
import com.merchant.management.dto.MerchantReg;
import com.merchant.management.entity.MerchantDetails;
import com.merchant.management.entity.OtpEntity;
import com.merchant.management.entity.ShopCustomerDetails;
import com.merchant.management.entity.CountryStateCity;
import com.merchant.management.entity.ImageSrcDetail;
import com.merchant.management.repository.MerchantRepository;
import com.merchant.management.repository.OtpRepository;
import com.merchant.management.repository.ShopCustomerRepo;
import com.merchant.management.security.AuthenticationRequest;
import com.merchant.management.security.JwtService;
import com.merchant.management.service.BillingService;
import com.merchant.management.service.EmailService;
import com.merchant.management.service.MerchantServices;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin
public class MerchantController {
	
@Autowired
private MerchantRepository merchantRepository;

@Autowired
private ShopCustomerRepo shopCustRepo;

@Autowired
private OtpRepository otpRepository;

@Autowired
private MerchantServices merchantServices;
@Autowired
private JwtService jwtServices;
@Autowired
private BillingService billingService;
@Autowired
private PasswordEncoder passwordEncoder;
@Autowired
private EmailService emailService;
@Autowired
private AuthenticationManager authenticationManager;


@PostMapping("/merchants")
public ResponseEntity<MerchantDetailRes> saveMerchant(@RequestBody MerchantReg merchant) {
	   MerchantDetailRes response  = new MerchantDetailRes();
       return  merchantServices.saveMerchantDetails(merchant);
       
}

@GetMapping("/getConStDtls")
public List<CountryStateCity> getComProdDtls(){
	List<CountryStateCity> conStDtls = billingService.getConStDtls();
	return conStDtls;
}


@GetMapping("/checkServer")
public ResponseEntity<MerchantDetailRes> checkServerStatus(){
	MerchantDetailRes response = new MerchantDetailRes();
	String password  = merchantServices.getMerchantPswdByMail("tchandru45@yahoo.com");
	response.setResponse("success");
	return ResponseEntity.ok(response);
}

//@PostMapping("/merchant-login")
//public ResponseEntity<MerchantDetailRes> merchantLogin(@RequestBody MerchantDetails merchant){
//	MerchantDetailRes response = new MerchantDetailRes();
//	ShopCustomerDetails shopCustomerDetails = new ShopCustomerDetails();
//	merchant.setMerchantEmail(merchant.getMerchantEmail());
//	merchant.setMerchantPassword(merchant.getMerchantPassword());
//	if(merchantRepository.getMerchantDetails(merchant.getMerchantEmail()) != null) {
//		authenticationManager.authenticate(
//				new UsernamePasswordAuthenticationToken(merchant.getMerchantEmail(), merchant.getMerchantPassword()));
//	}else {
//		shopCustomerDetails.setCustEmailId(merchant.getMerchantEmail());
//		shopCustomerDetails.setCustPassword(merchant.getMerchantPassword());
//		authenticationManager.authenticate(
//				new UsernamePasswordAuthenticationToken(shopCustomerDetails.getCustEmailId(), shopCustomerDetails.getCustPassword()));
//	}
//	
//	
////	String merchantEntrMail = merchant.getMerchantEmail();
////	String merchantEntrPass = merchant.getMerchantPassword();
//	//merchantServices.authMerchantPswdByMail(merchant);
//	Optional<MerchantDetails> userOpt = merchantRepository.findBymerchantEmail(merchant.getUsername());
//	
//	if(userOpt.isPresent()) {
//		MerchantDetails merchantDetails = userOpt.get();
//		String merchantName = merchantRepository.getMerchantDetails(merchant.getMerchantEmail());
//		String jwtToken = jwtServices.generateToken(merchantDetails);
//        response.setResponse("success");
//        response.setToken(jwtToken);
//        response.setUserName(merchantName);
//        response.setUserType("OWNER");
//	}
//
//	Optional<ShopCustomerDetails> customerOpt = shopCustRepo.findBycustEmailId(merchant.getMerchantEmail());
//	
//	 if (customerOpt.isPresent()) {
//		 ShopCustomerDetails customer = customerOpt.get();
//         String jwtsToken = jwtServices.generateToken(customer);
//         String customerName = shopCustRepo.getCustomerDetails(merchant.getMerchantEmail());
//         response.setResponse("success");
//         response.setToken(jwtsToken);
//         response.setUserName(customerName);
//         response.setUserType("CUST");
//	 }
//	return ResponseEntity.ok(response);
//
//}

@PostMapping("/merchant-login")
public ResponseEntity<MerchantDetailRes> merchantLogin(@RequestBody MerchantDetails merchant) {
    MerchantDetailRes response = new MerchantDetailRes();

    try {
        // Try merchant login
        Optional<MerchantDetails> merchantOpt = merchantRepository.findBymerchantEmail(merchant.getMerchantEmail());
        if (merchantOpt.isPresent()) {
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(merchant.getMerchantEmail(), merchant.getMerchantPassword()));

            String jwtToken = jwtServices.generateToken(merchantOpt.get());
            MerchantDetails merchantDet = merchantRepository.getMerchantProfileDetails(merchant.getMerchantEmail());

            response.setResponse("success");
            response.setToken(jwtToken);
            response.setUserName(merchantDet.getMerchantUserName());
            response.setCustRefId(merchantDet.getMerchantRefId());
            //response.set
            
            response.setUserType(String.valueOf(merchantOpt.get().getRole()));
            return ResponseEntity.ok(response);
        }

        // Neither found
        response.setResponse("fail");
        response.setUserType("NONE");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);

    } catch (AuthenticationException ex) {
        // Wrong password or failed authentication
        response.setResponse("fail");
        response.setUserType("NONE");
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
    }
}

@PostMapping("/authenticate")
public ResponseEntity<MerchantDetailRes> authenticate(@RequestBody MerchantDetails merchant){
	return ResponseEntity.ok(merchantServices.authenticate(merchant));
}

@PostMapping("/forgot-password")
public ResponseEntity<MerchantDetailRes> forgotPassword(@RequestParam String email) {

	MerchantDetailRes merchantRes = new MerchantDetailRes();
    String merchantValEmail = merchantRepository.findUserByEmail(email);
    
    if(merchantValEmail == null){
    	merchantRes.setErrorCode("1");
        merchantRes.setErrorMsg("User Not found!!!");
        merchantRes.setResponse("failure");
    }else {
    	String otp = generateOtp();
        OtpEntity entity = new OtpEntity();
        entity.setEmail(email);
        entity.setOtp(otp);
        entity.setExpiryTime(LocalDateTime.now().plusMinutes(5));
        otpRepository.save(entity);
        emailService.sendOtpEmail(email, otp);
        merchantRes.setErrorCode("0");
        merchantRes.setErrorMsg("OTP sent successfully");
        merchantRes.setResponse("success");
    }
    

    return ResponseEntity.ok(merchantRes);
}

@PostMapping("/verify-otp")
public ResponseEntity<MerchantDetailRes> verifyOtp(@RequestParam String email,
                                   @RequestParam String otp) {
	MerchantDetailRes merchantRes = new MerchantDetailRes();

    OtpEntity entity = otpRepository.findById(email).orElseThrow();

    if (entity.getExpiryTime().isBefore(LocalDateTime.now())) {
    	merchantRes.setErrorCode("1");
    	merchantRes.setErrorMsg("OTP expired");
    	merchantRes.setResponse("failure");
        return ResponseEntity.badRequest().body(merchantRes);
    }

    if (!entity.getOtp().equals(otp)) {
    	merchantRes.setErrorCode("1");
    	merchantRes.setErrorMsg("Invalid OTP");
    	merchantRes.setResponse("failure");
        return ResponseEntity.badRequest().body(merchantRes);
    }
    
    merchantRes.setErrorCode("0");
	merchantRes.setErrorMsg("OTP verified");
	merchantRes.setResponse("success");

    return ResponseEntity.ok(merchantRes);
}

@PostMapping("/reset-password")
public ResponseEntity<MerchantDetailRes> resetPassword(@RequestParam String email,
                                        @RequestParam String newPass) {
	
	MerchantDetailRes merchantRes = new MerchantDetailRes();
	
   String password = passwordEncoder.encode(newPass);
    merchantRepository.updateUserPasswordByEmail(password, email);

    otpRepository.deleteById(email); // cleanup
    merchantRes.setErrorCode("0");
	merchantRes.setErrorMsg("Password updated successfully");
	merchantRes.setResponse("success");

    return ResponseEntity.ok(merchantRes);
}

public String generateOtp() {
    return String.valueOf((int)(Math.random() * 900000) + 100000); // 6 digit
}


 

}
