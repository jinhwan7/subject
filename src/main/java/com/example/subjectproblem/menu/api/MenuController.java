package com.example.subjectproblem.menu.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.subjectproblem.menu.service.FindOneMenuService;
import com.example.subjectproblem.menu.service.ShowMenuListService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/menu")
@RequiredArgsConstructor
public class MenuController {

	@Autowired
	private ShowMenuListService showMenuListService;

	@Autowired
	private FindOneMenuService findOneMenuService;

	@PreAuthorize("isAuthenticated()")
	@GetMapping("/menuList")
	public ResponseEntity<List<ShowMenuListResponse>> showAllMenuList() {
		List<ShowMenuListResponse> menuList = showMenuListService.showMenuList();
		return ResponseEntity.ok(menuList);
	}

	@PreAuthorize("isAuthenticated()")
	@GetMapping("/menuDetail/{id}")
	public ResponseEntity<FindOneMenuResponse> findOneMenu(@PathVariable Long id) {
		FindOneMenuResponse menuDetail = findOneMenuService.findOneMenu(id);
		return ResponseEntity.ok(menuDetail);
	}
}
