package com.example.subjectproblem.cart.api;

public record UpdateQuantityRequest(Long cartId, Long menuId, Integer quantity) {
	public UpdateQuantityRequest {
		if (cartId == null) {
			throw new IllegalArgumentException("customerId is required");
		}
		if (menuId == null) {
			throw new IllegalArgumentException("menuId is required");
		}
		if (quantity == null) {
			throw new IllegalArgumentException("quantity is required");
		}

	}
}
