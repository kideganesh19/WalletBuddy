package com.walletBuddy.walletservice.models.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import java.time.Instant;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.google.gson.annotations.Expose;
import com.walletBuddy.walletservice.models.enums.WalletStatus;

import jakarta.persistence.*;

@Entity
public class Wallet {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Expose
	String Id;
	
	@Expose
	String UserId;
	
	@Version
	@Expose
	Long version = 0L;
	
	
	@Enumerated(value = EnumType.STRING)
	@Expose
	WalletStatus status;
	
	@Expose
	double currentBalance;
	
	@CreationTimestamp
	Instant createdAt;
	
	@UpdateTimestamp
	Instant updatedAt;
	
	@PrePersist
    public void init(){
        status = WalletStatus.ACTIVE;
        if(version == null){
            version = 0L;
        }
    }

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getUserId() {
		return UserId;
	}

	public void setUserId(String userId) {
		UserId = userId;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public WalletStatus getStatus() {
		return status;
	}

	public void setStatus(WalletStatus status) {
		this.status = status;
	}

	public double getCurrentBalance() {
		return currentBalance;
	}

	public void setCurrentBalance(double currentBalance) {
		this.currentBalance = currentBalance;
	}

	public Instant getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Instant createdAt) {
		this.createdAt = createdAt;
	}

	public Instant getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Instant updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	

}
