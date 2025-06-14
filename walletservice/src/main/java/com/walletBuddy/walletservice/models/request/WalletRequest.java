package com.walletBuddy.walletservice.models.request;

import com.walletBuddy.walletservice.models.enums.WalletTransactionType;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class WalletRequest {
	
	
	@NotNull
    String fromWallet;
    @NotNull
    String toWallet;
    @NotNull
    WalletTransactionType operationType;
    String description;
    @Positive
    double amount;
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
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}

    
}
