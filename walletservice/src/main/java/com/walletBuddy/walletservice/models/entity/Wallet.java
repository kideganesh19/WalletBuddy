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

}
