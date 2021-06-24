package com.example.lecspringsecurity.controller;

import com.example.lecspringsecurity.domain.Account;
import com.example.lecspringsecurity.service.AccountService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/account/{role}/{username}/{password}")
    @ResponseBody
    public Account createAccount(@ModelAttribute Account account) {
        return accountService.createNew(account);
    }

    @GetMapping("/signup")
    public String signupForm(Model model) {
        model.addAttribute("account", new Account());
        return "signup";
    }

    @PostMapping("/signup")
    public String processSignUp(@ModelAttribute Account account) {
        account.setRole("USER");
        accountService.createNew(account);
        return "redirect:/";
    }
}
