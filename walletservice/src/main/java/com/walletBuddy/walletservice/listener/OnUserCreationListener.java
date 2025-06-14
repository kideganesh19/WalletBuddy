package com.walletBuddy.walletservice.listener;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.walletBuddy.walletservice.models.entity.UserInfo;
import com.walletBuddy.walletservice.models.entity.Wallet;
import com.walletBuddy.walletservice.models.request.WalletCreationRequest;
import com.walletBuddy.walletservice.service.WalletService;

@Component
public class OnUserCreationListener {
	
	public final String USER_CREATED = "USER_CREATION";
	public final String NEW_WALLET_CREATION_SUCCESSFUL = "NEW_WALLET_CREATION_SUCCESSFUL";
	
	@Autowired
	Gson gson;
	
	@Autowired
	@Qualifier("gsonSerializer")
	Gson gsonwithExpose;
	
	
	@Autowired
	WalletService walletService;
	
	@Autowired
    KafkaTemplate<String , String> kafkaTemplate;
	
	
	@KafkaListener(topics=USER_CREATED, groupId="wallet_group")
	public void newUserCreationListener(@Payload String userMessage) {
		if(Objects.isNull(userMessage) || userMessage.isEmpty() || userMessage.isBlank()) {
			return;
		}
		
		UserInfo userInfo = gson.fromJson(userMessage, UserInfo.class);
		if(Objects.isNull(userInfo) || Objects.isNull(userInfo.getId())) {
			return;
		}
		
		Wallet newWallet = walletService.createNewWallet(userInfo.getId());
		
		var newWalletCreation = new WalletCreationRequest();
		
		newWalletCreation.setId(newWallet.getId());
		newWalletCreation.setUserId(newWallet.getUserId());
		newWalletCreation.setReferredUserId("");
		newWalletCreation.setReferredUserWalletId("");
		
		String serial = gson.toJson(newWalletCreation);
		
		kafkaTemplate.send(NEW_WALLET_CREATION_SUCCESSFUL,serial);
	}

}
