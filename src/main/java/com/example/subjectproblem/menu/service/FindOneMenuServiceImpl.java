package com.example.subjectproblem.menu.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.subjectproblem.menu.api.FindOneMenuResponse;
import com.example.subjectproblem.menu.domain.MenuItem;
import com.example.subjectproblem.menu.infra.MenuRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class FindOneMenuServiceImpl implements FindOneMenuService {
	@Autowired
	private MenuRepository menuRepository;

	@Override
	public FindOneMenuResponse findOneMenu(Long id) {

		Optional<MenuItem> menu = menuRepository.findById(id);

		if (menu.isPresent()) {
			return FindOneMenuResponse.from(menu.get());
		} else {
			throw new EntityNotFoundException("Menu with id " + id + " not found");
		}

	}
}
