package com.merchant.management.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.merchant.management.entity.MerchantDetails;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationServices {
      private MerchantDetails merchantDetails;

	private PasswordEncoder passwordEncoder;
//	public AuthenticationResponse register(RegisterRequest request) {
//		// TODO Auto-geMernerated method stub
//	    var merchant = MerchantDetails.
////	    		.merchantUserName(request.getFirstname())
////	    		.merchantEmail(request.getEmail())
////	    		.merchantPhoneNumber(request.getPhoneNo())
////	    		.merchantPassword(passwordEncoder.encode(request.getPassword())).build();
//		return null;
//	}

	public AuthenticationResponse authenticate(AuthenticationRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

}
