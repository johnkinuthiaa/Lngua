package com.slippery.lingua.service.impl;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class AuthenticationTokenService {
    public int generateVerificationToken() {
        Random random =new Random();
//        generates 6 integers
        return random.nextInt(1000000);

    }
}
