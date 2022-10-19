package com.wfis.SimpleBank.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class LoginController {

  @GetMapping("/login")
  public String login() {
    return "login";
  }


  @PostMapping("/login_success_handler")
  public String loginSuccessHandler() {
    //perform audit actions
    return "/";
  }

  @PostMapping("/login_failure_handler")
  public String loginFailureHandler() {
    //perform audit actions
    return "login";
  }
}
