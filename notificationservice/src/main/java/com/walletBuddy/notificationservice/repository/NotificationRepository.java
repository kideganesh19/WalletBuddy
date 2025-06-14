package com.walletBuddy.notificationservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.walletBuddy.notificationservice.Models.Entity.UserMessage;

public interface NotificationRepository extends JpaRepository<UserMessage,String>{

}
