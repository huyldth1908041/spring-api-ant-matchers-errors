package com.t908e.springsecurityapitoken.dto;

import com.t908e.springsecurityapitoken.entity.Account;

public class AccountDTO {
    private String username;
    private String fullName;

    public AccountDTO(Account account) {
        this.username = account.getUsername();
        this.fullName = account.getFullName();
    }
}
