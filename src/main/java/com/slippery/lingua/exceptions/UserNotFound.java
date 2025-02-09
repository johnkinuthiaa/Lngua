package com.slippery.lingua.exceptions;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserNotFound extends UsernameNotFoundException {
    public UserNotFound(String message) {
        super(message);
    }
}
