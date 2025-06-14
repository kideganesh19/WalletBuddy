package com.walletBuddy.walletservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.walletBuddy.walletservice.exception.InsufficientBalanceException;
import com.walletBuddy.walletservice.models.entity.Wallet;
import com.walletBuddy.walletservice.models.entity.WalletTransaction;
import com.walletBuddy.walletservice.models.enums.WalletTransactionType;
import com.walletBuddy.walletservice.models.request.WalletRequest;
import com.walletBuddy.walletservice.repository.WalletTransactionRepository;

import jakarta.transaction.Transactional;

@Service
public class WalletTransactionService {
	
	
	@Autowired
	WalletTransactionRepository walletTransactionRepository;
	
	@Autowired
	WalletService walletService;
	
	@Value("${system.wallet.id}")
    String systemWalletId;
	
	
	@Transactional
	public WalletTransaction createTxn(WalletRequest request) {
		
		Wallet fromWallet = walletService.getActiveWalletById(request.getFromWallet());
		Wallet toWallet = walletService.getActiveWalletById(request.getToWallet());
		
		WalletTransactionType walletTransactionType = request.getOperationType();
		
		
		Wallet debitWallet = (walletTransactionType == WalletTransactionType.CREDIT)? fromWallet : toWallet;
		Wallet creditWallet = (walletTransactionType == WalletTransactionType.CREDIT) ? toWallet : fromWallet;
		
		if(!debitWallet.getId().equalsIgnoreCase(systemWalletId) && debitWallet.getCurrentBalance() - request.getAmount() <0 ) {
			throw new InsufficientBalanceException();
		}
		
		debitWallet.setCurrentBalance(debitWallet.getCurrentBalance() - request.getAmount());
		creditWallet.setCurrentBalance(creditWallet.getCurrentBalance() + request.getAmount());
		
		WalletTransaction walletTransaction = new WalletTransaction();
		walletTransaction.setFromWallet(fromWallet);
		walletTransaction.setToWallet(toWallet);
		walletTransaction.setDescription(request.getDescription());
		walletTransaction.setTransactionAmout(request.getAmount());
		walletTransaction.setOperationType(request.getOperationType());
		
		walletService.saveOrUpdate(debitWallet);
		walletService.saveOrUpdate(creditWallet);
		
		return walletTransactionRepository.save(walletTransaction);
	}
	
	
	public Page<WalletTransaction> getTxnById(String walletId, Integer pageNumber){
		
		PageRequest pageRequest = PageRequest.of(pageNumber, 8);
		return walletTransactionRepository.findByToWalletIdOrFromWalletIdOrderByUpdatedAtDesc(walletId, walletId, pageRequest);
	}

}
