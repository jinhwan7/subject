package com.example.subjectproblem.customer.service;

import com.example.subjectproblem.customer.api.AuthRequest;
import com.example.subjectproblem.customer.api.SignupResponse;
import com.example.subjectproblem.customer.domain.Customer;

public interface SignupService {
	/**
	 * 사용자를 회원가입 시킵니다.
	 *
	 * @param signupRequest 회원가입 요청 데이터를 담고 있는 DTO
	 * @return 회원가입 성공 시, 인증 토큰을 포함한 응답 객체
	 * @throws IllegalArgumentException 유효하지 않은 데이터가 전달된 경우
	 */
	SignupResponse signup(AuthRequest signupRequest);
}
