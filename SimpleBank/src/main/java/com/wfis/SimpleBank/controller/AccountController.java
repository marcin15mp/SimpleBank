package com.wfis.SimpleBank.controller;

import com.wfis.SimpleBank.account.AccountService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("payments/transfer")
    public String doTransfer(@RequestParam int amount, @RequestParam String recipient, Authentication authentication) {
        accountService.doTransfer(authentication.getName(), recipient, amount);
        return "redirect:/przelewok";

    }

    @GetMapping("initialize")
    public String initializeDb() {
        accountService.initializeAccounts();
        return "redirect:/login";
    }

    @GetMapping("przelewok")
    public String przelewok() {
        return "przelewok";
    }

    @GetMapping("moreMoney")
    public String notenought() {
        return "notenought";

    }

    @GetMapping("/account/bonus")
    public String bonus() {
        accountService.bonusforusers();
        return "redirect:/account/manage";
    }
}