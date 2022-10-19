package com.wfis.SimpleBank.controller;

import com.wfis.SimpleBank.account.AccountService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

  private final AccountService accountService;

  public HomeController(AccountService accountService) {
    this.accountService = accountService;
  }

  @GetMapping("/home")
  public String home(Authentication authentication, Model model) {
    boolean isAdmin = authentication.getAuthorities().stream()
            .map(it -> it.getAuthority())
            .anyMatch(it -> "ROLE_ADMIN".equals(it));

    int userAmount = accountService.findUserAmount(authentication.getName());
    model.addAttribute("IS_ADMIN", isAdmin);
    // Przypisanie wartości do atrybutów
    model.addAttribute("userName", authentication.getName());
    model.addAttribute("userAmount", userAmount);

    return "home";
  }

  @GetMapping("/")
  public String homePage() {
    return "redirect:home";
  }
}
