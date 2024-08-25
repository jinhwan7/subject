package com.example.subjectproblem.customer.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.subjectproblem.customer.domain.Customer;
import com.example.subjectproblem.customer.infra.CustomerRepository;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public boolean authenticate(Customer inputData) {
		Optional<Customer> foundUserOptional = customerRepository.findByCustomerId(inputData.getCustomerId());

		if (foundUserOptional.isPresent()) {
			Customer foundUser = foundUserOptional.get();
			return passwordEncoder.matches(inputData.getPassword(), foundUser.getPassword());
		}
		return false;
	}

}






