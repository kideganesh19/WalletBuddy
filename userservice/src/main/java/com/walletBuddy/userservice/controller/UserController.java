package com.walletBuddy.userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.walletBuddy.userservice.models.entity.UserInfo;
import com.walletBuddy.userservice.models.request.CreateUserRequest;
import com.walletBuddy.userservice.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/v1/user")
public class UserController {
	
	@Autowired
	UserService userService;
	
	public ResponseEntity<UserInfo> createUser(@Valid @RequestBody CreateUserRequest request){
		
		UserInfo userInfo = userService.createNewUser(request);
		
		return new ResponseEntity<>(userInfo, HttpStatus.CREATED);	
	}

}
