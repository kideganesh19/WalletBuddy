package com.walletBuddy.userservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.walletBuddy.userservice.models.entity.UserInfo;

public interface UserRepository extends JpaRepository<UserInfo, String>{
	
	Optional<UserInfo> findByEmailId(String emailId);
	
	Optional<UserInfo> findById(String id);

}
