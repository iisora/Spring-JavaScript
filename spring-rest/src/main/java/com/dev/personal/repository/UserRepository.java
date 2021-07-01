package com.dev.personal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.personal.model.MyUser;

public interface UserRepository extends JpaRepository<MyUser, Long> {
	MyUser findByUsername(String username);

	boolean existsByUsername(String username);
}
