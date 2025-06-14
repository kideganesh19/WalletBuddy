package com.walletBuddy.notificationservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import com.walletBuddy.notificationservice.Models.Entity.UserMessage;

public interface NotificationRepository extends JpaRepository<UserMessage,String>{

	
	Page<UserMessage> findByUserIdOrderByUpdatedAtDesc(String userId, PageRequest pageRequest);
}
