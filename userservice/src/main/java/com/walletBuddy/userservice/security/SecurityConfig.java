package com.walletBuddy.userservice.security;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.walletBuddy.userservice.service.UserService;

@Configuration
public class SecurityConfig {


    @Autowired
    BeanFactory beanFactory;

    @Bean
    PasswordEncoder passwordEncoderPrimary(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Primary
    DaoAuthenticationProvider daoAuthenticationProvider(){
        UserService userService = beanFactory.getBean(UserService.class);
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userService);
        provider.setPasswordEncoder(passwordEncoderPrimary());
        return provider;
    }

	@Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeHttpRequests()
                .anyRequest().permitAll()
                .and().formLogin();
        return http.build();
    }

}