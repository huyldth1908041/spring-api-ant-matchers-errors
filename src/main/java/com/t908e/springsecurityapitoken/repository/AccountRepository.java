package com.t908e.springsecurityapitoken.repository;

import com.t908e.springsecurityapitoken.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Integer> {
    Optional<Account> findAccountByUsername(String username);
}
