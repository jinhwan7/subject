package com.example.subjectproblem.cart.api;

import com.example.subjectproblem.cart.domain.Cart;
import com.example.subjectproblem.customer.domain.Customer;
import com.example.subjectproblem.menu.domain.MenuItem;

public record GetCartResponse(Long id, MenuItem menuItem, int quantity, int tableNumber, Customer customer) {

	public static GetCartResponse from(Cart cartItem) {
		return new GetCartResponse(
			cartItem.getId(),
			cartItem.getMenuItem(),
			cartItem.getQuantity(),
			cartItem.getTableNum(),
			cartItem.getCustomer()
		);
	}

}