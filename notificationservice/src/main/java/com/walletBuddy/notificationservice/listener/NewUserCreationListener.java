package com.walletBuddy.notificationservice.listener;

import org.springframework.stereotype.Controller;

import com.google.gson.Gson;
import com.walletBuddy.notificationservice.Models.Entity.UserInfo;
import com.walletBuddy.notificationservice.service.NotificationService;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import java.util.Objects;

@Controller
public class NewUserCreationListener {
	
	public static final String USER_CREATED = "USER_CREATION";
	
	@Autowired
    Gson gson;


    @Autowired
    JavaMailSender javaMailSender;


    @Autowired
    NotificationService notificationService;

    @Value("${system.no.reply.email.id}")
    private String systemEmailId;
    
	@KafkaListener(topics = USER_CREATED, groupId = "notification_group")
    public void sendNewUserCreationEmail(ConsumerRecord<String, String> consumerRecord){
        if(Objects.isNull(consumerRecord.value()) || consumerRecord.value().isEmpty()){
            return;
        }

        String value = consumerRecord.value();
        UserInfo userInfo = gson.fromJson(value, UserInfo.class);

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(systemEmailId);
        mailMessage.setTo(userInfo.getEmailId());
        mailMessage.setText(String.format(" Hey %s ! thank you for registering with us !", userInfo.getName()));
        mailMessage.setSubject("New Account Created ");
        javaMailSender.send(mailMessage);
        notificationService.saveNewOnboardedUserMessage(userInfo.getId());
    }

}
