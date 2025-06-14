package com.walletBuddy.userservice.models.request;

import java.util.Set;

import com.walletBuddy.userservice.models.entity.UserInfo;
import com.walletBuddy.userservice.models.enums.UserType;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class CreateUserRequest {
	
	@NotBlank
	String name;
	@Positive
	Long phoneNumber;
	@Email
	String emailId;
	@NotNull
	UserType userType;
	@NotNull
	String passwordRaw;

	@NotNull
	Set<String> roles;
	
	public UserInfo toUserInfo() {
		UserInfo userInfo = new UserInfo();
		userInfo.setName(name);
		userInfo.setEmailId(emailId);
		userInfo.setPhoneNumber(phoneNumber);
		userInfo.setUserType(userType);
		userInfo.setPasswordRaw(passwordRaw);
		userInfo.setRoles(roles);
		return userInfo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(Long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}
	
	public String getPasswordRaw() {
		return passwordRaw;
	}

	public void setPasswordRaw(String passwordRaw) {
		this.passwordRaw = passwordRaw;
	}

	public Set<String> getRoles() {
		return roles;
	}

	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}

	public CreateUserRequest(@NotBlank String name, @Positive Long phoneNumber, @Email String emailId,
			@NotNull UserType userType) {
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.emailId = emailId;
		this.userType = userType;
	}
	
	public CreateUserRequest() {
	}
	
	

}
