package com.t908e.springsecurityapitoken.config;

import com.t908e.springsecurityapitoken.entity.Account;
import com.t908e.springsecurityapitoken.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {
    @Autowired
    private AccountService accountService;

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {

    }
    //check token is correct or not then return the current user
    @Override
    protected UserDetails retrieveUser(String s, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {
        //get token in request
        Object credentials = usernamePasswordAuthenticationToken.getCredentials();
        if(credentials == null) {
            throw new UsernameNotFoundException("Credential not found!");
        }
        String accessToken = String.valueOf(credentials);
        Account account = accountService.findByToken(accessToken);
        if(account == null) {
            return null;
        }
        UserDetails userDetails = User.builder()
                .username(account.getUsername())
                .password(account.getPasswordHash())
                .roles(account.getRoleName())
                .build();   
        System.out.println(userDetails.getUsername());
        return userDetails;

    }
}
