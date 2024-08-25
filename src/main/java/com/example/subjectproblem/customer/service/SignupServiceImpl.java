package com.example.subjectproblem.customer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.example.subjectproblem.customer.api.AuthRequest;
import com.example.subjectproblem.customer.api.SignupResponse;
import com.example.subjectproblem.customer.domain.Customer;
import com.example.subjectproblem.customer.infra.CustomerRepository;

import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class SignupServiceImpl implements SignupService {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public SignupResponse signup(AuthRequest authRequest) {
		try {

			String encodedPassword = passwordEncoder.encode(authRequest.password());
			Customer customer = Customer.from(authRequest.customerId(), encodedPassword);

			customerRepository.save(customer);

			return SignupResponse.success("회원가입 성공");
		} catch (DataIntegrityViolationException e) {
			throw new IllegalArgumentException("User already exists or invalid data", e);
		} catch (Exception e) {
			throw new RuntimeException("Internal Server Error", e);
		}

	}

}
