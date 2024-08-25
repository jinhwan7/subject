package com.example.subjectproblem.customer.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.subjectproblem.customer.domain.Customer;
import com.example.subjectproblem.customer.service.AuthenticationService;
import com.example.subjectproblem.customer.service.SignupService;
import com.example.subjectproblem.customer.util.JwtUtil;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication", description = "Authentication and registration APIs")
public class CustomerController {

	@Autowired
	private AuthenticationService authenticationService;

	@Autowired
	private SignupService signupService;

	@Autowired
	private JwtUtil jwtUtil;

	@Operation(summary = "Login", description = "Authenticate a user and return a JWT token")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Successful authentication",
			content = {@Content(mediaType = "application/json",
				schema = @Schema(implementation = AuthResponse.class))}),
		@ApiResponse(responseCode = "400", description = "Invalid credentials",
			content = @Content)
	})
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
