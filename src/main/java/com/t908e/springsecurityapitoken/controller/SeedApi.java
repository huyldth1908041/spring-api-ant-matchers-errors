package com.t908e.springsecurityapitoken.controller;

import com.t908e.springsecurityapitoken.dto.LoginDTO;
import com.t908e.springsecurityapitoken.entity.Product;
import com.t908e.springsecurityapitoken.repository.ProductRepository;
import com.t908e.springsecurityapitoken.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/generate/seed")
@CrossOrigin
public class SeedApi {
    @Autowired
    private AccountService accountService;

    @Autowired
    private ProductRepository productRepository;

    @RequestMapping(method = RequestMethod.GET)
    public boolean seedAccount() {
        accountService.register(new LoginDTO("admin", "admin", "Huy"));
        accountService.register(new LoginDTO("user", "user", "Huong"));
        productRepository.save(new Product("bánh mì", 10000));
        productRepository.save(new Product("cơm", 1000));
        productRepository.save(new Product("cháo", 2000));
        productRepository.save(new Product("gà", 4000));
        productRepository.save(new Product("bò", 50000));
        productRepository.save(new Product("lợn", 40000));
        productRepository.save(new Product("giày", 40000));
        productRepository.save(new Product("dép", 40000));
        productRepository.save(new Product("chó", 7000));
        productRepository.save(new Product("mèo", 8000));
        return true;
    }
}
