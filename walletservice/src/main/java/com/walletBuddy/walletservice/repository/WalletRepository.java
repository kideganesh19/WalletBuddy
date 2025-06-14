package com.walletBuddy.walletservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.walletBuddy.walletservice.models.entity.Wallet;
import com.walletBuddy.walletservice.models.enums.WalletStatus;

public interface WalletRepository extends JpaRepository<Wallet,String>{
	
	Optional<Wallet> findByIdAndStatus(String walletId, WalletStatus walletStatus);
	
	Optional<Wallet> findByUserId(String userId);

}
