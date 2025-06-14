package com.walletBuddy.walletservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.walletBuddy.walletservice.models.entity.Wallet;
import com.walletBuddy.walletservice.service.WalletService;

@RestController
@RequestMapping("/v1/wallet")
public class WalletController {
	
	@Autowired
	WalletService walletService;
	
	
	@GetMapping(value="/{walletId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Wallet> getWalletById(@PathVariable String walletId){
		
		var walletEntity = walletService.getWalletById(walletId);
		return new ResponseEntity<>(walletEntity,HttpStatus.OK);
		
	}
	

}
