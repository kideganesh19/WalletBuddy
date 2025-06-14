package com.walletBuddy.walletservice.listener;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.walletBuddy.walletservice.models.entity.Wallet;
import com.walletBuddy.walletservice.models.enums.WalletTransactionType;
import com.walletBuddy.walletservice.models.request.TransactionInfoRequest;
import com.walletBuddy.walletservice.models.request.WalletRequest;
import com.walletBuddy.walletservice.service.WalletService;
import com.walletBuddy.walletservice.service.WalletTransactionService;

@Component
public class OnTransactListener {
	
	public static final String TRANSACT = "TRANSACT";
	public static final String TRANSACTION_SUCCESS = "TRANSACTION_SUCCESS";
    public static final String TRANSACTION_FAILED = "TRANSACTION_FAILED";
	
	@Autowired
	Gson gson;
	
	@Autowired
	@Qualifier("gsonSerializer")
	Gson gsonExposed;
	
	@Autowired
	WalletTransactionService walletTransactionService;
	
	@Autowired
	WalletService walletService;
	
	@Autowired
	KafkaTemplate<String,String> kafkaTemplate;
	
	
	@KafkaListener(topics = TRANSACT, groupId = "wallet_group")
	public void onTransactionListener(ConsumerRecord<String,String> consumerRecord) {
		
		var request = gson.fromJson(consumerRecord.value(), WalletRequest.class);
		
		if(request.getAmount() <=0 ) {
			return;
		}
		
		String topic = null;
		try {
			walletTransactionService.createTxn(request);
			topic = TRANSACTION_SUCCESS;
		}catch(Exception ex) {
			topic = TRANSACTION_FAILED;
		}
		
		Wallet toWallet = walletService.getActiveWalletById(request.getToWallet());
		Wallet fromWallet = walletService.getActiveWalletById(request.getFromWallet());
		
		Wallet creditedWallet = (request.getOperationType() == WalletTransactionType.CREDIT)? toWallet : fromWallet;
		Wallet debitedWallet = (request.getOperationType() == WalletTransactionType.CREDIT)? fromWallet : toWallet;
		
		var transactionInfo = new TransactionInfoRequest();
		transactionInfo.setCreditToUserId(creditedWallet.getUserId());
		transactionInfo.setCreditToWalletId(creditedWallet.getId());
		transactionInfo.setAmountDebited(request.getAmount());
		transactionInfo.setDebitFromUserId(debitedWallet.getUserId());
		transactionInfo.setDebitFromWalletId(debitedWallet.getId());
		
		kafkaTemplate.send(topic,gson.toJson(transactionInfo));
	}

}
