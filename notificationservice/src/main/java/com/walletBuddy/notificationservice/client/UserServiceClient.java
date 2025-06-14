package com.walletBuddy.notificationservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.walletBuddy.notificationservice.Models.Entity.UserInfo;

@FeignClient(name = "userservice", url = "http://localhost:8080/s2s/")
public interface UserServiceClient {
	
	@GetMapping("/user/{userId}")
    UserInfo getUser(@PathVariable("userId") String userId);

}
