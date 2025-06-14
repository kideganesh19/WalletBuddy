package com.walletBuddy.rewardservice.model;

import com.walletBuddy.rewardservice.enums.RewardTransactionType;

public class RewardTransaction {
	
	String fromWallet;
	String toWallet;
	RewardTransactionType operationType;
	String description;
	double ampunt;
	public String getFromWallet() {
		return fromWallet;
	}
	public void setFromWallet(String fromWallet) {
		this.fromWallet = fromWallet;
	}
	public String getToWallet() {
		return toWallet;
	}
	public void setToWallet(String toWallet) {
		this.toWallet = toWallet;
	}
	public RewardTransactionType getOperationType() {
		return operationType;
	}
	public void setOperationType(RewardTransactionType operationType) {
		this.operationType = operationType;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getAmpunt() {
		return ampunt;
	}
	public void setAmpunt(double ampunt) {
		this.ampunt = ampunt;
	}
	
	

}
