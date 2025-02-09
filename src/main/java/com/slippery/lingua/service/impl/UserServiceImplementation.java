package com.slippery.lingua.service.impl;

import com.slippery.lingua.dto.UserDto;
import com.slippery.lingua.exceptions.UserAlreadyExists;
import com.slippery.lingua.models.Users;
import com.slippery.lingua.repository.UserRepository;
import com.slippery.lingua.service.UsersService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImplementation implements UsersService {
    private final UserRepository repository;
    private final AuthenticationTokenService authenticationTokenService;
    private final PasswordEncoder passwordEncoder =new BCryptPasswordEncoder(12);
    private final AuthenticationManager authenticationManager;

    public UserServiceImplementation(UserRepository repository, AuthenticationTokenService authenticationTokenService, AuthenticationManager authenticationManager) {
        this.repository = repository;
        this.authenticationTokenService = authenticationTokenService;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public UserDto register(Users userDetails) {
        Users existingByUsername =repository.findByUsername(userDetails.getUsername());
        Users existingByEmail =repository.findByEmail(userDetails.getEmail());
        UserDto response =new UserDto();
        if(existingByEmail !=null ||existingByUsername !=null){
            throw new UserAlreadyExists("User with the login details already exists");
        }
        int token =authenticationTokenService.generateVerificationToken();
//        add logic to verify with token
//        send token to email

        userDetails.setPassword(passwordEncoder.encode(userDetails.getPassword()));
        userDetails.setStreak(0L);
        repository.save(userDetails);
        response.setMessage("User registered successfully");
        return response;
    }

    @Override
    public UserDto login(Users userDetails) {
        Users existingByUsername =repository.findByUsername(userDetails.getUsername());
        Users existingByEmail =repository.findByEmail(userDetails.getEmail());
        UserDto response =new UserDto();
        if(existingByUsername ==null){
            response.setMessage("User not found");
            return response;
        }
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                userDetails.getUsername(),
                userDetails.getPassword()
        ));
        if(authentication.isAuthenticated()){
//            add jwt token
            response.setMessage("User logged in successfully ");
            response.setStatusCode(200);
            return response;
        }
        response.setMessage("User not logged in successfully ");
        response.setStatusCode(401);
        return response;
    }

    @Override
    public UserDto findById(Long id) {
        UserDto response =new UserDto();
        Optional<Users> existingUser =repository.findById(id);
        if(existingUser.isEmpty()){
            response.setMessage("User not found!");
            response.setStatusCode(404);
            return response;
        }
        response.setStatusCode(200);
        response.setMessage("User with id "+id);
        response.setUser(existingUser.get());
        return response;
    }

    @Override
    public UserDto deleteById(Long id) {
        UserDto response =new UserDto();
        Optional<Users> existingUser =repository.findById(id);
        if(existingUser.isEmpty()){
            response.setMessage("User not found!");
            response.setStatusCode(404);
            return response;
        }
        repository.deleteById(id);
        response.setMessage("User deleted successfully");
        response.setStatusCode(200);

        return response;
    }

    @Override
    public UserDto update(Long id, Users userDetails) {
        return null;
    }
}
