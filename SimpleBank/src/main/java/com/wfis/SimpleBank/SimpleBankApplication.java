package com.wfis.SimpleBank;

import com.wfis.SimpleBank.controller.AccountController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class SimpleBankApplication {

	@Autowired
	AccountController accountController;

	public static void main(String[] args) {
		SpringApplication.run(SimpleBankApplication.class, args);
	}

	@PostConstruct
	public void init() {
		accountController.initializeDb();
	}
}
