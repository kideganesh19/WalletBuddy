package com.walletBuddy.walletservice.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.walletBuddy.walletservice.exception.WalletAlreadyExist;
import com.walletBuddy.walletservice.exception.WalletNotFoundException;
import com.walletBuddy.walletservice.models.entity.Wallet;
import com.walletBuddy.walletservice.models.enums.WalletStatus;
import com.walletBuddy.walletservice.repository.WalletRepository;

@Service
public class WalletService {
	
	@Autowired
	WalletRepository walletRepository;
	
	
	public Wallet getWalletById(String walletId) {
		
		return walletRepository.findById(walletId).orElseThrow(WalletNotFoundException::new);
		
	}
	
	
	public Wallet getActiveWalletById(String walledId) {
		return walletRepository.findByIdAndStatus(walledId, WalletStatus.ACTIVE).orElseThrow(WalletNotFoundException::new);
	}
	
	public Wallet saveOrUpdate(Wallet wallet) {
		return walletRepository.save(wallet);
	}
	
	public Wallet createNewWallet(String id) {
		Optional<Wallet> wallet = walletRepository.findByUserId(id);
		if(wallet.isPresent()) {
			throw new WalletAlreadyExist();
		}
		Wallet newWallet = new Wallet();
		newWallet.setId(id);
		newWallet.setCurrentBalance(0);
		newWallet.setStatus(WalletStatus.ACTIVE);
		
		return walletRepository.save(newWallet);
	}
	

}
