package com.example.subjectproblem.customer.api;
// import com.example.customer.domain.customer;


public record AuthResponse(String token, String errorMessage) {

	// 성공적인 응답 생성
	public static AuthResponse success(String token) {
		return new AuthResponse(token, null);
	}

	// 실패 응답 생성
	public static AuthResponse failure(String errorMessage) {
		return new AuthResponse(null, errorMessage);
	}
}