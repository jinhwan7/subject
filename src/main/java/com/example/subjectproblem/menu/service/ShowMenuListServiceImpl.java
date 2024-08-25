package com.example.subjectproblem.menu.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.example.subjectproblem.menu.api.ShowMenuListResponse;

import com.example.subjectproblem.menu.domain.MenuItem;
import com.example.subjectproblem.menu.infra.MenuRepository;

@Service
public class ShowMenuListServiceImpl implements ShowMenuListService {

	@Autowired
	private MenuRepository menuRepository;

	@Override
	public List<ShowMenuListResponse> showMenuList() {
		try {
			List<MenuItem> menuList = menuRepository.findAll();

			return menuList.stream()
				.map(ShowMenuListResponse::from)
				.collect(Collectors.toList());
		} catch (Exception e) {
			throw new RuntimeException("Internal Server Error", e);
		}

	}
}
