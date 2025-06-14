package com.walletBuddy.walletservice.models.entity;

import com.google.gson.annotations.Expose;


public class UserInfo{

    String id;

    @Expose
    String name;

    @Expose
    Long phoneNumber;
    
    @Expose
    String emailId;
    
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
	
}