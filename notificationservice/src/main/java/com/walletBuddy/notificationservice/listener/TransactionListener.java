package com.walletBuddy.notificationservice.listener;

import java.util.Objects;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.walletBuddy.notificationservice.Models.Entity.TransactionInformation;
import com.walletBuddy.notificationservice.client.UserServiceClient;

@Component
public class TransactionListener {
	
	public static final String USER_CREATED = "USER_CREATION";

    public static final String TRANSACTION_SUCCESS = "TRANSACTION_SUCCESS";
    public static final String TRANSACTION_FAILED = "TRANSACTION_FAILED";
	
	@Autowired
	Gson gson;
	
	@Autowired
    JavaMailSender javaMailSender;

    @Value("${system.no.reply.email.id}")
    private String systemEmailId;

    @Value("${system.wallet.id}")
    private String systemWalletId;

    @Value("${system.user.id}")
    private String systemUserId;

    @Autowired
    UserServiceClient userServiceClient;
    
    @KafkaListener(topics = TRANSACTION_SUCCESS, groupId = "notification_group")
    public void onSuccessFulTransactionListener(ConsumerRecord<String , String> kafkaMessageRecord){
    	
    	
        TransactionInformation transactionInformation = gson.fromJson(kafkaMessageRecord.value(), TransactionInformation.class);

        sendMessageTo(transactionInformation.getCreditToUserId(),
                transactionInformation.getCreditToWalletId(), "credited",
                transactionInformation.getAmountDebited());

        sendMessageTo(transactionInformation.getDebitFromUserId(),
                transactionInformation.getDebitFromWalletId(), "debited",
                transactionInformation.getAmountDebited());

    }



    private void sendMessageTo(String userId , String walletId, String operation, Double amount){
        if(Objects.nonNull(systemUserId) && systemUserId.equalsIgnoreCase(userId)
            || Objects.nonNull(systemWalletId) && systemWalletId.equalsIgnoreCase(walletId)){
            return;
        }

        var user = userServiceClient.getUser(userId);
        sendTransactionSuccessMessage(operation, user.getName(), amount, user.getEmailId());
    }

    private void sendTransactionSuccessMessage(String operation, String userName,
                                               Double amount, String userEmailId){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setSubject(String.format(" Wallet successfully %s", operation));
        simpleMailMessage.setText(String.format(" Hi %s, Your wallet has been successfully %s by %s",
                userName, operation, amount));
        simpleMailMessage.setFrom(systemEmailId);
        simpleMailMessage.setTo(userEmailId);
        javaMailSender.send(simpleMailMessage);
    }

}
