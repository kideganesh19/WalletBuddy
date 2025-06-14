package com.walletBuddy.walletservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.walletBuddy.walletservice.models.entity.WalletTransaction;

public interface WalletTransactionRepository extends JpaRepository<WalletTransaction, String>{
	
	

}
