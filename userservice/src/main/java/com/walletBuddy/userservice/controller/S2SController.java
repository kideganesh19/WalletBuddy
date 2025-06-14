package com.walletBuddy.userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.walletBuddy.userservice.service.UserService;
import com.walletBuddy.userservice.models.entity.UserInfo;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/v1/s2s")
public class S2SController {

	@Autowired
	UserService userService;
	
	
	@GetMapping(value = "/user/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserInfo> getUser(@PathVariable String id){
		var userInfo = userService.getUserById(id);
		return new ResponseEntity<>(userInfo, HttpStatus.OK);
	}
}
