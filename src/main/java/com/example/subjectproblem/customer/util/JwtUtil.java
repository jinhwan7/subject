package com.example.subjectproblem.customer.util;

import io.github.cdimascio.dotenv.Dotenv;
import io.jsonwebtoken.Jwts;

import io.jsonwebtoken.security.Keys;

import org.springframework.stereotype.Component;

import java.util.Date;

import javax.crypto.SecretKey;

@Component
public class JwtUtil {
	private final SecretKey secretKey;

	public JwtUtil() {
		// 문자열 비밀 키를 바이트 배열로 변환하여 SecretKey 생성

		Dotenv dotenv = Dotenv.configure().filename(".env.properties").load();
		String secret = dotenv.get("JWT_SECRET");
		this.secretKey = Keys.hmacShaKeyFor(secret.getBytes());
	}

	public String generateToken(String username) {
		// 10 hours
		long expirationTime = 1000 * 60 * 60 * 10;
		return Jwts.builder()
			.subject(username)
			.issuedAt(new Date(System.currentTimeMillis()))
			.expiration(new Date(System.currentTimeMillis() + expirationTime))
			.signWith(secretKey)
			.compact();
	}

}



