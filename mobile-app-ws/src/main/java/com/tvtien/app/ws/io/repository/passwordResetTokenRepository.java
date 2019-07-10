package com.tvtien.app.ws.io.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tvtien.app.ws.io.entity.PasswordResetTokenEntity;
import com.tvtien.app.ws.io.entity.UserEntity;

@Repository
public interface passwordResetTokenRepository extends JpaRepository<PasswordResetTokenEntity, Serializable>{
	PasswordResetTokenEntity findByToken(String token);
}
