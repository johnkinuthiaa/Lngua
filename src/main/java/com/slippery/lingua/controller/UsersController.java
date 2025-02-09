package com.slippery.lingua.controller;

import com.slippery.lingua.dto.UserDto;
import com.slippery.lingua.models.Users;
import com.slippery.lingua.service.UsersService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class UsersController {
    private final UsersService service;

    public UsersController(UsersService service) {
        this.service = service;
    }
    @PostMapping("/register")
    public UserDto register(Users users){
        return service.register(users);
    }
}
