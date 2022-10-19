package com.wfis.SimpleBank.controller;

import com.wfis.SimpleBank.account.AccountService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ManageController {

    private final AccountService accountService;

    public ManageController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/account/manage")
    public String manage(Authentication authentication, Model model) {
        int accountCount = accountService.accountCount();
        double averageAccountState = accountService.averageAmountStateAllAccount();
        model.addAttribute("ACCOUNT_COUNT", accountCount); // zlicza ilosc kont
        model.addAttribute("AVERAGE_COUNT_STATE", averageAccountState); // avarage count pokazuję średnią pieniędzy

        return "admin/manage";
    }

}
