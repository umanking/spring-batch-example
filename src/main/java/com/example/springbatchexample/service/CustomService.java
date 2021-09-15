package com.example.springbatchexample.service;

import org.springframework.stereotype.Service;

@Service
public class CustomService {

    public void serviceMethod(String message) {
        System.out.println("message is..." + message);
    }

}
