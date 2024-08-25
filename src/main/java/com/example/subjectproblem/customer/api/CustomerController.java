package com.example.subjectproblem.customer.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.subjectproblem.customer.domain.Customer;
import com.example.subjectproblem.customer.service.AuthenticationService;
import com.example.subjectproblem.customer.service.SignupService;
import com.example.subjectproblem.customer.util.JwtUtil;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class CustomerController {

	@Autowired
	private AuthenticationService authenticationService;

	@Autowired
	private SignupService signupService;

	@Autowired
	private JwtUtil jwtUtil;

	@PostMapping("/login")
	public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest) {

		Customer customer = Customer.from(authRequest);
		System.out.println(authRequest);
		if (authenticationService.authenticate(customer)) {
			String token = jwtUtil.generateToken(customer.getCustomerId());
			return ResponseEntity.ok(AuthResponse.success(token));
		} else {
			return ResponseEntity.badRequest().body(AuthResponse.failure("Invalid credentials"));
		}
	}

	@PostMapping("/signup")
	public ResponseEntity<?> signup(@RequestBody AuthRequest signupRequest) {
		SignupResponse response = signupService.signup(signupRequest);
		if (response.errorMessage() == null) {
			return ResponseEntity.ok(response);
		} else {
			return ResponseEntity.badRequest().body(response);
		}
	}
}
