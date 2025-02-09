package com.slippery.lingua.service;

import com.slippery.lingua.models.UserPrincipal;
import com.slippery.lingua.models.Users;
import com.slippery.lingua.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {
    private final UserRepository repository;

    public MyUserDetailsService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users =repository.findByUsername(username);
        if(users ==null){
            throw new UsernameNotFoundException("User not found");
        }
        return new UserPrincipal(users);
    }
}
