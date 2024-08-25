package com.example.subjectproblem.customer.api;

public record AuthRequest(String customerId, String password) {
	// 생성자에서 유효성 검사 추가
	public AuthRequest {
		if (customerId == null || customerId.isBlank()) {
			throw new IllegalArgumentException("customerId is required");
		}
		if (password == null || password.isBlank()) {
			throw new IllegalArgumentException("password is required");
		}
	}
}



