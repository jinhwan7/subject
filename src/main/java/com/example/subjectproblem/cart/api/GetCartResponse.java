package com.example.subjectproblem.cart.api;

import com.example.subjectproblem.cart.domain.Cart;


public record GetCartResponse(String token, String errorMessage) {

	public static GetCartResponse from(Cart cartItem) {
		return new GetCartResponse(
			cartItem.getId(),
			cartItem.getMenuName(),
			cartItem.getPrice()
		);
	}

}