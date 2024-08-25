package com.example.subjectproblem.customer.util;

import io.github.cdimascio.dotenv.Dotenv;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;

import io.jsonwebtoken.security.Keys;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

import javax.crypto.SecretKey;

@Component
public class JwtUtil {
	@Value("${JWT_SECRET}")
	private String jwtSecret;

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

	public Long extractCustomerIdFromToken() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null && authentication.getCredentials() instanceof String) {
			String token = (String)authentication.getCredentials();
			Claims claims = parseToken(token);

			// JWT claim에서 customerId를 추출
			// "sub" (subject) claim을 customerId로 사용한다고 가정
			String customerIdStr = claims.getSubject();

			return Long.parseLong(customerIdStr);
		}
		throw new IllegalStateException("CustomerId not found in JWT token");
	}

	private Claims parseToken(String token) {
		Key key = Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
		return Jwts.parser()
			.setSigningKey(key)
			.build()
			.parseClaimsJws(token)
			.getBody();
	}

}



