package com.example.subjectproblem.menu.infra;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.example.subjectproblem.menu.domain.MenuItem;

@Repository
public interface MenuRepository extends JpaRepository<MenuItem, Long>, QuerydslPredicateExecutor<MenuItem> {

}





