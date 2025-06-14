package com.walletBuddy.walletservice.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import com.walletBuddy.walletservice.models.entity.WalletTransaction;

public interface WalletTransactionRepository extends JpaRepository<WalletTransaction, String>{
	
	Page<WalletTransaction> findByToWalletIdOrFromWalletIdOrderByUpdatedAtDesc(String toWalletId,String fromWalletId, PageRequest pageRequest);

}
