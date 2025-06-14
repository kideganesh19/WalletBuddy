package com.walletBuddy.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.walletBuddy.userservice.models.entity.UserInfo;

public interface UserRepository extends JpaRepository<UserInfo, String>{

}
