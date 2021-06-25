package com.t908e.springsecurityapitoken.controller;

import com.t908e.springsecurityapitoken.dto.CredentialDTO;
import com.t908e.springsecurityapitoken.dto.LoginDTO;
import com.t908e.springsecurityapitoken.entity.Account;
import com.t908e.springsecurityapitoken.entity.Credential;
import com.t908e.springsecurityapitoken.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(value="/api")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public CredentialDTO login(@RequestBody LoginDTO loginDTO) {
        //TODO: validate dto , return bad request if not valid
        CredentialDTO credential = accountService.login(loginDTO);
        if (credential == null) {
            return null;
        }
        return credential;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@RequestBody LoginDTO loginDTO) {
        Account register = accountService.register(loginDTO);
        if (register == null) {
            return "failed";
        }
        return "ok";
    }
}
