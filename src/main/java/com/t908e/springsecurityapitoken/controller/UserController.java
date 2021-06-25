package com.t908e.springsecurityapitoken.controller;

import com.t908e.springsecurityapitoken.dto.AccountDTO;
import com.t908e.springsecurityapitoken.entity.Account;
import com.t908e.springsecurityapitoken.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController
@RequestMapping(value="/api/user")
@CrossOrigin
public class UserController {
    @Autowired
    private AccountService accountService;
    @RequestMapping(value = "/information", method = RequestMethod.GET)
    public AccountDTO getInformation(@RequestHeader("Authorization") String token) {
        Account byToken = accountService.findByToken(token.replace("Bearer", "").trim());
        return new AccountDTO(byToken);
    }
}
