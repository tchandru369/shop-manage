package com.merchant.management.security;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/api/v2/auth")
@RequiredArgsConstructor
public class AuthenticationController {
	 
	private final AuthenticationServices authenticationService = new AuthenticationServices();

	
//	@PostMapping("/register")
//	public ResponseEntity<AuthenticationResponse> register(
//			@RequestBody RegisterRequest request
//			){
//		return ResponseEntity.ok(authenticationService.register(request));
//	}
	
	@PostMapping("/authenticate")
	public ResponseEntity<AuthenticationResponse> authenticate(
			@RequestBody AuthenticationRequest request
			){
		return ResponseEntity.ok(authenticationService.authenticate(request));

	}
}
