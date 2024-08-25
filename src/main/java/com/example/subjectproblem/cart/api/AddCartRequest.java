package com.example.subjectproblem.cart.api;

public record AddCartRequest(Long customerId, Long menuId, Integer quantity, Integer tableNum) {
	public AddCartRequest {
		if (customerId == null) {
			throw new IllegalArgumentException("customerId is required");
		}
		if (menuId == null) {
			throw new IllegalArgumentException("menuId is required");
		}
		if (quantity == null) {
			throw new IllegalArgumentException("quantity is required");
		}
		if (tableNum == null) {
			throw new IllegalArgumentException("tableNum is required");
		}
	}
}



