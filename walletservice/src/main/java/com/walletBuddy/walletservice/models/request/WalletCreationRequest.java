package com.walletBuddy.walletservice.models.request;

public class WalletCreationRequest {
	
	String id;
    String userId;
    String referredUserWalletId;
    String referredUserId;
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getReferredUserWalletId() {
		return referredUserWalletId;
	}
	public void setReferredUserWalletId(String referredUserWalletId) {
		this.referredUserWalletId = referredUserWalletId;
	}
	public String getReferredUserId() {
		return referredUserId;
	}
	public void setReferredUserId(String referredUserId) {
		this.referredUserId = referredUserId;
	}
    
}
