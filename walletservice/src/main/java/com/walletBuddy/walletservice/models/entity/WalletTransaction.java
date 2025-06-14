package com.walletBuddy.walletservice.models.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;

import org.apache.logging.log4j.CloseableThreadContext.Instance;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.walletBuddy.walletservice.models.enums.WalletTransactionType;

import jakarta.persistence.*;

@Entity
public class WalletTransaction {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.UUID)
	String Id;
	
	
	@ManyToOne
	Wallet fromWallet;
	
	@ManyToOne
	Wallet toWallet;
	
	Double transactionAmout;
	
	@Enumerated(value = EnumType.STRING)
	WalletTransactionType operationType;
	
	
	String description;
	
	@CreationTimestamp
	Instance createdAt;
	
	@UpdateTimestamp
	Instance updatedAt;

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public Wallet getFromWallet() {
		return fromWallet;
	}

	public void setFromWallet(Wallet fromWallet) {
		this.fromWallet = fromWallet;
	}

	public Wallet getToWallet() {
		return toWallet;
	}

	public void setToWallet(Wallet toWallet) {
		this.toWallet = toWallet;
	}

	public Double getTransactionAmout() {
		return transactionAmout;
	}

	public void setTransactionAmout(Double transactionAmout) {
		this.transactionAmout = transactionAmout;
	}

	public WalletTransactionType getOperationType() {
		return operationType;
	}

	public void setOperationType(WalletTransactionType operationType) {
		this.operationType = operationType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Instance getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Instance createdAt) {
		this.createdAt = createdAt;
	}

	public Instance getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Instance updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	

}
