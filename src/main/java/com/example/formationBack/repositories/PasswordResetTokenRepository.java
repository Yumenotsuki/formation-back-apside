package com.example.formationBack.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.formationBack.models.PasswordResetToken;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, String>{
	PasswordResetToken findByResetToken(String resetToken);

	PasswordResetToken findByUserId(Integer id);

}
