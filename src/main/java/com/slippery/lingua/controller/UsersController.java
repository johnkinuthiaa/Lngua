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
    public ResponseEntity<UserDto> register(@RequestBody Users users){
        return ResponseEntity.ok(service.register(users));
    }
    @PostMapping("/re")
    public Users add(@RequestBody Users users) {
        return service.add(users);
    }
    @PostMapping("/enroll/course")
    public ResponseEntity<UserDto> enrollUserToCourse(@RequestParam Long UserId, @RequestParam Long courseId){
        return ResponseEntity.ok(service.enrollUserToCourse(UserId, courseId));
    }
    @PutMapping("/un-enroll/course")
    public ResponseEntity<UserDto> unEnrollUserFromCourse(@RequestParam Long UserId, @RequestParam Long courseId) {
        return ResponseEntity.ok(service.unEnrollUserFromCourse(UserId, courseId));
    }
    @GetMapping("/enrolled/courses")
    public ResponseEntity<UserDto> getAllUserEnrolledCourses(@RequestParam Long id){
        return ResponseEntity.ok(service.getAllUserEnrolledCourses(id));
    }
    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody Users userDetails){
        return ResponseEntity.ok(service.login(userDetails));
    }

    @GetMapping("/{id}/get")
    public ResponseEntity<UserDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<UserDto> deleteById(@PathVariable Long id) {
        return ResponseEntity.ok(service.deleteById(id));
    }

    @PutMapping("/update")
    public ResponseEntity<UserDto> update(@RequestParam Long id,@RequestBody Users userDetails) {
        return ResponseEntity.ok(service.update(id, userDetails));
    }

}
