package com.example.subjectproblem.menu.api;

import com.example.subjectproblem.menu.domain.MenuItem;

public record FindOneMenuResponse(Long id, String name, String description, int price) {

	public static FindOneMenuResponse from(MenuItem menuItem) {
		return new FindOneMenuResponse(
			menuItem.getId(),
			menuItem.getMenuName(),
			menuItem.getDescription(),
			menuItem.getPrice()
		);
	}
}
