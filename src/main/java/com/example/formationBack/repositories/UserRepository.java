package com.example.formationBack.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.formationBack.models.User;


public interface UserRepository extends JpaRepository<User, Integer> {
	User findByUsername(String username);
	User findByEmail(String email);
	List<User> findById(int id);
	Boolean existsByUsername(String username);
	Boolean existsByEmail(String email);

}
