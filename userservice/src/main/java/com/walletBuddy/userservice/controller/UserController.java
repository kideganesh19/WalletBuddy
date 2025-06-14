package com.walletBuddy.userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.walletBuddy.userservice.models.entity.UserInfo;
import com.walletBuddy.userservice.models.request.CreateUserRequest;
import com.walletBuddy.userservice.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/v1/user")
public class UserController {
	
	public String USER_CREATED = "USER_CREATION";
	
	@Autowired
	UserService userService;
	
	@Autowired
	Gson gson;
	
	@Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserInfo> createUser(@RequestBody @Valid CreateUserRequest request){
        var userInfo = userService.createNewUser(request);
        String serial = gson.toJson(userInfo);
        kafkaTemplate.send(USER_CREATED, gson.toJson(userInfo));
        return new ResponseEntity<>(userInfo, HttpStatus.CREATED);
    }

}
