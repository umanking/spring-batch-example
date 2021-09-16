package com.example.springbatchexample;

import org.springframework.stereotype.Service;

@Service
public class CustomService {

    public void serviceMethod(String message) {
        System.out.println("message is..." + message);
    }

}
