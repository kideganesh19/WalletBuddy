package com.walletBuddy.userservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.walletBuddy.userservice.models.entity.UserInfo;
import com.walletBuddy.userservice.models.request.CreateUserRequest;
import com.walletBuddy.userservice.repository.UserRepository;

@Service
public class UserService {
	
	
	@Autowired
	UserRepository userRepository;
	
	public UserInfo createNewUser(CreateUserRequest request){
		UserInfo userEntity = userRepository.save(request.toUserInfo());
		return userEntity;
	}
}
