package com.example.subjectproblem.menu.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import com.example.subjectproblem.cart.domain.Cart;

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

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "Menu")
public class MenuItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true)
	private String menuName;

	@Column(nullable = false)
	private Integer price;

	@Column(length = 1000, nullable = false)
	private String description;

	@OneToMany(mappedBy = "menuItem", cascade = CascadeType.ALL)
	private List<Cart> carts = new ArrayList<>();

	@CreationTimestamp
	@Column(updatable = false)  // Ensure this column is not updated after creation
	private LocalDateTime createdDate;

}




