package com.slippery.lingua.service.impl;

import com.slippery.lingua.dto.UserDto;
import com.slippery.lingua.exceptions.UserAlreadyExists;
import com.slippery.lingua.models.Users;
import com.slippery.lingua.repository.UserRepository;
import com.slippery.lingua.service.UsersService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImplementation implements UsersService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder =new BCryptPasswordEncoder(12);

    public UserServiceImplementation(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDto register(Users userDetails) {
        Users existingByUsername =repository.findByUsername(userDetails.getUsername());
        Users existingByEmail =repository.findByEmail(userDetails.getEmail());
        UserDto response =new UserDto();
        if(existingByEmail !=null ||existingByUsername !=null){
            throw new UserAlreadyExists("User with the login details already exists");
        }
        userDetails.setPassword(passwordEncoder.encode(userDetails.getPassword()));
        userDetails.setStreak(0L);
        repository.save(userDetails);
        response.setMessage("User registered successfully");
        return response;
    }

    @Override
    public UserDto login(Users userDetails) {
        return null;
    }

    @Override
    public UserDto findById(Long id) {
        return null;
    }

    @Override
    public UserDto deleteById(Long id) {
        return null;
    }

    @Override
    public UserDto update(Long id, Users userDetails) {
        return null;
    }
}
