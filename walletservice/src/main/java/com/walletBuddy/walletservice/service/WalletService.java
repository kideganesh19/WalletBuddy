package com.walletBuddy.walletservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.walletBuddy.walletservice.exception.WalletNotFoundException;
import com.walletBuddy.walletservice.models.entity.Wallet;
import com.walletBuddy.walletservice.repository.WalletRepository;

@Service
public class WalletService {
	
	@Autowired
	WalletRepository walletRepository;
	
	
	public Wallet getWalletById(String walletId) {
		
		return walletRepository.findById(walletId).orElseThrow(WalletNotFoundException::new);
		
	}
	

}
