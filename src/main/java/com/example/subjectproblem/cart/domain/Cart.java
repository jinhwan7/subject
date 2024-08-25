package com.example.subjectproblem.cart.domain;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.example.subjectproblem.customer.domain.Customer;
import com.example.subjectproblem.menu.domain.MenuItem;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "CartItems")
public class Cart {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	// MenuItem과의 N:1 관계 설정
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "menu_id", nullable = false) // 외래 키 설정
	private MenuItem menuItem;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customer_id", nullable = false) // 외래 키 설정
	private Customer customer;

	@Column(nullable = false)
	private Integer quantity;

	@Column(nullable = false)
	private Integer tableNum;

	@CreationTimestamp
	@Column(updatable = false)  // Ensure this column is not updated after creation
	private LocalDateTime createdDate;
}
