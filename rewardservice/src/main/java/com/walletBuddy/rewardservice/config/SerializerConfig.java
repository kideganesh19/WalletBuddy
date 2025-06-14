package com.walletBuddy.rewardservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Configuration
public class SerializerConfig {
	
	@Bean
	@Primary
	public Gson gson() {
		return new Gson();
	}
	
	@Bean(value = "gsonSerializer")
    public Gson gsonSerializer(){
        return new GsonBuilder().setPrettyPrinting()
                .excludeFieldsWithoutExposeAnnotation()
                .create();
    }
}
