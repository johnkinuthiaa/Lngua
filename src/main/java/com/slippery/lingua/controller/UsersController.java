package com.slippery.lingua.controller;

import com.slippery.lingua.dto.UserDto;
import com.slippery.lingua.models.Users;
import com.slippery.lingua.service.UsersService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UsersController {
    private final UsersService service;

    public UsersController(UsersService service) {
        this.service = service;
    }
    @PostMapping("/register")
    public UserDto register(@RequestBody Users users){
        return service.register(users);
    }
    @PostMapping("/enroll/course")
    public ResponseEntity<UserDto> enrollUserToCourse(@RequestParam Long UserId, @RequestParam Long courseId){
        return ResponseEntity.ok(service.enrollUserToCourse(UserId, courseId));
    }
    @PutMapping("/un-enroll/course")
    public ResponseEntity<UserDto> unEnrollUserFromCourse(@RequestParam Long UserId, @RequestParam Long courseId) {
        return ResponseEntity.ok(service.unEnrollUserFromCourse(UserId, courseId));
    }
}
