package com.t908e.springsecurityapitoken.controller;

import com.t908e.springsecurityapitoken.entity.Product;
import com.t908e.springsecurityapitoken.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;
    @RequestMapping(method = RequestMethod.GET)
    public List<Product> list() {
        return productRepository.findAll();
    }
}
