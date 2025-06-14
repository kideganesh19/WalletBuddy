package com.walletBuddy.rewardservice.listener;


import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.walletBuddy.rewardservice.enums.RewardTransactionType;
import com.walletBuddy.rewardservice.model.RewardTransaction;
import com.walletBuddy.rewardservice.model.WalletInfo;

@Component
public class OnNewWalletCreationListener {
	
	
	public static final String NEW_WALLET_CREATION_SUCCESSFUL = "NEW_WALLET_CREATION_SUCCESSFUL";

    public static final String TRANSACT = "TRANSACT";
	
	@Autowired
	Gson gson;
	
	@Value("${system.wallet.id}")
	String systemWalletId;

	@Value("${reward.referral}")
	Double referralRewardAmount;

	@Value("${reward.referee}")
	Double refereeRewardAmount;

	@Value("${reward.new.user}")
	Double newUserOnboardingRewardAmount;
	
	@Autowired
	KafkaTemplate<String,String> kafkaTemplate;
	
	
	@KafkaListener(topics = NEW_WALLET_CREATION_SUCCESSFUL, groupId = "reward_service_group")
	public void OnNewWalletCreation(ConsumerRecord<String,String> consumerRecord) {
		
		if(Objects.isNull(consumerRecord.value()) || consumerRecord.value().isEmpty()) {
			return;
		}
		
		
		WalletInfo walletInfo = gson.fromJson(consumerRecord.value(), WalletInfo.class);
		
		if(Objects.isNull(walletInfo) || Objects.isNull(walletInfo.getId())) {
			return;
		}
		
		
		List<RewardTransaction> transactionList = new LinkedList<>();
		
		transactionList.add(getRewardTransaction(walletInfo, Objects.isNull(walletInfo.getReferredUserWalletId())?
				newUserOnboardingRewardAmount : referralRewardAmount));
		
		if(Objects.nonNull(walletInfo.getReferredUserWalletId()) && !Objects.isNull(walletInfo.getReferredUserWalletId().isEmpty())) {
			transactionList.add(getRewardTransaction(walletInfo,refereeRewardAmount));
		}
		
		transactionList.forEach(transaction -> {
			var serial = gson.toJson(transaction);
			kafkaTemplate.send(TRANSACT,serial);
		});
		
	}
	
	public RewardTransaction getRewardTransaction(WalletInfo walletInfo, double amount) {
		RewardTransaction rewardTransaction = new RewardTransaction();
		rewardTransaction.setFromWallet(systemWalletId);
		rewardTransaction.setToWallet(walletInfo.getId());
		rewardTransaction.setOperationType(RewardTransactionType.CREDIT);
		rewardTransaction.setAmpunt(amount);
		rewardTransaction.setDescription("Successfully referred");
		return rewardTransaction;
	}

}
