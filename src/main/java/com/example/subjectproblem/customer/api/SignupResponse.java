package com.example.subjectproblem.customer.api;

public record SignupResponse(String result, String errorMessage) {

	// 성공적인 응답 생성
	public static SignupResponse success(String result) {
		return new SignupResponse(result, null);
	}

}