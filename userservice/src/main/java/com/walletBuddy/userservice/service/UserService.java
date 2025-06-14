package com.walletBuddy.userservice.service;

import java.util.Collections;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.walletBuddy.userservice.exception.DuplicateUserException;
import com.walletBuddy.userservice.exception.UserDoesNotExist;
import com.walletBuddy.userservice.models.entity.UserInfo;
import com.walletBuddy.userservice.models.request.CreateUserRequest;
import com.walletBuddy.userservice.repository.UserRepository;

@Service
public class UserService implements UserDetailsService{
	
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
    PasswordEncoder encoder;
	
	public UserInfo createNewUser(CreateUserRequest request){
		UserInfo userInfo = request.toUserInfo();
		Optional<UserInfo> user = userRepository.findByEmailId(userInfo.getEmailId());
		if(user.isPresent()) {
			throw new DuplicateUserException();
		}
		
		userInfo.setPassword(encoder.encode(userInfo.getPasswordRaw()));
		UserInfo userEntity = userRepository.save(userInfo);
		return userEntity;
	}
	
	public static UserInfo anonymous(){
		UserInfo userInfo = new UserInfo();
		userInfo.setName("ANONYMOUS");
		userInfo.setId(null);
		userInfo.setPassword("ANONYMOUS");
		userInfo.setRoles(Collections.singleton("ROLE_ANONYMOUS"));
		return userInfo;
    }
	
	public UserInfo getUserById(String id) {
		Optional<UserInfo> user = userRepository.findById(id);
		if(user.isEmpty()) {
			throw new UserDoesNotExist();
		}
		return user.get();
	}

	@Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserInfo> userOpt = userRepository.findByEmailId(username);
        return userOpt.orElseGet(UserService::anonymous);
    }
}
