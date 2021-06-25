package com.t908e.springsecurityapitoken.service;

import com.t908e.springsecurityapitoken.dto.CredentialDTO;
import com.t908e.springsecurityapitoken.dto.LoginDTO;
import com.t908e.springsecurityapitoken.entity.Account;
import com.t908e.springsecurityapitoken.entity.Credential;
import com.t908e.springsecurityapitoken.repository.AccountRepository;
import com.t908e.springsecurityapitoken.repository.CredentialRepository;
import com.t908e.springsecurityapitoken.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CredentialRepository credentialRepository;
    public Account findByToken(String accessToken) {
        Optional<Credential> credentialOptional = credentialRepository.findById(accessToken);
        if(credentialOptional.isPresent()) {
            Credential credential = credentialOptional.get();
            if(credential.isExpired()) {
                return null;
            }
            return accountRepository.findById(credential.getAccountId()).orElse(null);
        }
        return null;
    }

    public Account register(LoginDTO loginDTO) {
        Account account = new Account();
        account.setRole(1);
        account.setUsername(loginDTO.getUsername());
        account.setPasswordHash(passwordEncoder.encode(loginDTO.getPassword()));
        account.setFullName("full name");
        account.setStatus(1);
        account.setCreatedAt(new Date());
        account.setUpdatedAt(new Date());
        account.setFullName(loginDTO.getFullName());
        return accountRepository.save(account);
    }

    public CredentialDTO login(LoginDTO loginDTO) {
        Optional<Account> accountOptional = accountRepository.findAccountByUsername(loginDTO.getUsername());
        if(!accountOptional.isPresent()) {
            return null;
        }
        Account account = accountOptional.get();
        if(!passwordEncoder.matches(loginDTO.getPassword(), account.getPasswordHash())) {
            //login failed wrong pwd
            return null;
        }
        //create credential
        Credential credential = new Credential();
        credential.setAccessToken(UUID.randomUUID().toString());
        credential.setRefreshToken(UUID.randomUUID().toString());
        credential.setAccount(account);
        credential.setCreatedAt(new Date());
        //exprire after 5 days
        credential.setExpiredAt(TimeUtil.addDaysToCurrentTime(5));
        Credential saved = credentialRepository.save(credential);
        return new CredentialDTO(saved);

    }
}
