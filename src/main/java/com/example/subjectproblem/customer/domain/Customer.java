package com.example.subjectproblem.customer.domain;

import java.util.ArrayList;
import java.util.List;

import com.example.subjectproblem.cart.domain.Cart;
import com.example.subjectproblem.customer.api.AuthRequest;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "Customers")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true)
	private String customerId;

	@Column(length = 1000)
	private String password;

	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
	private List<Cart> carts = new ArrayList<>();

	// 정적 팩토리 메서드
	public static Customer from(AuthRequest signupRequest) {
		Customer customer = new Customer();
		customer.customerId = signupRequest.customerId();
		customer.password = signupRequest.password();
		return customer;
	}

	public static Customer from(String customerId, String password) {
		Customer customer = new Customer();
		customer.customerId = customerId;
		customer.password = password;
		return customer;
	}

}



