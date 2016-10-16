package io.firesoft.repository;

import io.firesoft.model.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer>{

	User findByUsername(String name);
	
}
