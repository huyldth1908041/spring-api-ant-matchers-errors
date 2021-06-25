package com.t908e.springsecurityapitoken.repository;

import com.t908e.springsecurityapitoken.entity.Credential;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CredentialRepository extends JpaRepository<Credential, String> {
}
