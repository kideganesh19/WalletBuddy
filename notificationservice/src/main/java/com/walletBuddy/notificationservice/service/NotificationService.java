package com.walletBuddy.notificationservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.walletBuddy.notificationservice.Mdels.enums.MessageSenderService;
import com.walletBuddy.notificationservice.Models.Entity.UserMessage;
import com.walletBuddy.notificationservice.repository.NotificationRepository;

public class NotificationService {
	
	
	@Autowired
	NotificationRepository notificationRepository;
	
	
	public UserMessage saveNewOnboardedUserMessage(String userId){
        UserMessage userMessage = new UserMessage();
        userMessage.setUserId(userId);
        userMessage.setMessage("User created");
        userMessage.setService(MessageSenderService.USER_SERVICE);
        return notificationRepository.save(userMessage);
    }
	
	public Page<UserMessage> fetchMessages(String userId, Integer pageNumber){
		PageRequest pageRequest = PageRequest.of(pageNumber, 5);
		return notificationRepository.findByUserIdOrderByUpdatedAtDesc(userId,pageRequest);
	}

}
