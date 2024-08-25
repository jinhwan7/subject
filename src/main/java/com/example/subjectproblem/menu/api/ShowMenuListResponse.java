package com.example.subjectproblem.menu.api;

import com.example.subjectproblem.menu.domain.MenuItem;

public record ShowMenuListResponse(Long id, String name, int price) {

	// MenuItem 엔티티를 ShowMenuResponse DTO로 변환하는 메서드
	public static ShowMenuListResponse from(MenuItem menuItem) {
		return new ShowMenuListResponse(
			menuItem.getId(),
			menuItem.getMenuName(),
			menuItem.getPrice()
		);
	}
}
