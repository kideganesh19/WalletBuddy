package com.walletBuddy.notificationservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.walletBuddy.notificationservice.Models.Entity.UserMessage;
import com.walletBuddy.notificationservice.service.NotificationService;

@RestController
@RequestMapping("/v1/notification")
public class NotificationController {
	
	
	@Autowired
	NotificationService notificationService;
	
	
	public ResponseEntity<Page<UserMessage>> listUserMessage(@PathVariable String UserId, @RequestParam("pageNumber") Integer pageNumber){
		var userMessageEntity = notificationService.fetchMessages(UserId,pageNumber);
		return new ResponseEntity(userMessageEntity,HttpStatus.OK);
	}

}
