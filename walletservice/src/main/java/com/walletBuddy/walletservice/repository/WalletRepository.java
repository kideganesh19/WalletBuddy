package com.walletBuddy.walletservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.walletBuddy.walletservice.models.entity.Wallet;

public interface WalletRepository extends JpaRepository<Wallet,String>{

}
