package com.walletBuddy.walletservice.models.request;

public class TransactionInfoRequest {
	
	String debitFromWalletId;
    String debitFromUserId;
    String creditToWalletId;
    String creditToUserId;
    Double amountDebited;
	public String getDebitFromWalletId() {
		return debitFromWalletId;
	}
	public void setDebitFromWalletId(String debitFromWalletId) {
		this.debitFromWalletId = debitFromWalletId;
	}
	public String getDebitFromUserId() {
		return debitFromUserId;
	}
	public void setDebitFromUserId(String debitFromUserId) {
		this.debitFromUserId = debitFromUserId;
	}
	public String getCreditToWalletId() {
		return creditToWalletId;
	}
	public void setCreditToWalletId(String creditToWalletId) {
		this.creditToWalletId = creditToWalletId;
	}
	public String getCreditToUserId() {
		return creditToUserId;
	}
	public void setCreditToUserId(String creditToUserId) {
		this.creditToUserId = creditToUserId;
	}
	public Double getAmountDebited() {
		return amountDebited;
	}
	public void setAmountDebited(Double amountDebited) {
		this.amountDebited = amountDebited;
	}
    
    
}
