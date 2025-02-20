package com.slippery.lingua.service.impl;

import com.slippery.lingua.dto.UserDto;
import com.slippery.lingua.exceptions.UserAlreadyExists;
import com.slippery.lingua.models.Courses;
import com.slippery.lingua.models.Users;
import com.slippery.lingua.repository.CoursesRepository;
import com.slippery.lingua.repository.UserRepository;
import com.slippery.lingua.service.JwtTokenService;
import com.slippery.lingua.service.UsersService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserServiceImplementation implements UsersService {
    private final UserRepository repository;

    private final PasswordEncoder passwordEncoder =new BCryptPasswordEncoder(12);
    private final AuthenticationManager authenticationManager;
    private final CoursesRepository coursesRepository;
    private final AuthenticationTokenService authenticationTokenService;
    private final JwtTokenService jwtTokenService;

    public UserServiceImplementation(UserRepository repository, AuthenticationManager authenticationManager, CoursesRepository coursesRepository, AuthenticationTokenService authenticationTokenService, JwtTokenService jwtTokenService) {
        this.repository = repository;
        this.authenticationManager = authenticationManager;
        this.coursesRepository = coursesRepository;
        this.authenticationTokenService = authenticationTokenService;
        this.jwtTokenService = jwtTokenService;
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
        var jwtToken =jwtTokenService.generateJwtToken(userDetails.getUsername());
//        add logic to verify with token
//        send token to email

        userDetails.setPassword(passwordEncoder.encode(userDetails.getPassword()));
        userDetails.setCoursesEnrolled(new ArrayList<>());
        userDetails.setStreak(0L);
        repository.save(userDetails);
        response.setMessage("User registered successfully");
        response.setStatusCode(200);
        response.setAuthToken(token);
        response.setJwtToken(jwtToken);
        return response;
    }

    @Override
    public Users add(Users users) {
        users.setPassword(passwordEncoder.encode(users.getPassword()));
        repository.save(users);
        return users;
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
    public UserDto getAllUserEnrolledCourses(Long id) {
        UserDto response =new UserDto();
        Optional<Users> existingUser =repository.findById(id);
        if(existingUser.isEmpty()){
            response.setMessage("User not found!");
            response.setStatusCode(404);
            return response;
        }
        response.setCoursesList(existingUser.get().getCoursesEnrolled());
        response.setMessage("All courses for "+existingUser.get().getUsername());
        response.setStatusCode(200);

        return response;
    }

    @Override
    public UserDto enrollUserToCourse(Long UserId, Long courseId) {
        Optional<Users> existingUser =repository.findById(UserId);
        UserDto response =new UserDto();
        Optional<Courses> existingCourse =coursesRepository.findById(courseId);
        if(existingCourse.isEmpty()){
            response.setMessage("Course does not exist");
            response.setStatusCode(404);
            return response;
        }
        if(existingUser.isEmpty()){
            response.setMessage("User does not exist");
            response.setStatusCode(404);
            return response;
        }
        var membersEnrolledToCourse =existingCourse.get().getUsersEnrolled();
        var coursesByUser =existingUser.get().getCoursesEnrolled();

        membersEnrolledToCourse.add(existingUser.get());
        coursesByUser.add(existingCourse.get());

        coursesRepository.save(existingCourse.get());
        repository.save(existingUser.get());
        response.setMessage("User enrolled to "+ existingCourse.get().getName());
        response.setStatusCode(200);
        return response;
    }

    @Override
    public UserDto unEnrollUserFromCourse(Long UserId, Long courseId) {
        Optional<Courses> existingCourse =coursesRepository.findById(courseId);
        Optional<Users> existingUser =repository.findById(UserId);
        UserDto response =new UserDto();

        if(existingUser.isEmpty()){
            response.setMessage("User does not exist");
            response.setStatusCode(404);
            return response;
        }
        if(existingCourse.isEmpty()){
            response.setMessage("Course does not exist");
            response.setStatusCode(404);
            return response;
        }
        var membersEnrolledToCourse =existingCourse.get().getUsersEnrolled();
        var coursesByUser =existingUser.get().getCoursesEnrolled();
        if(!membersEnrolledToCourse.contains(existingUser.get())){
            response.setMessage("User is not enrolled in the course");
            response.setStatusCode(200);
            return response;
        }
        membersEnrolledToCourse.remove(existingUser.get());
        coursesByUser.remove(existingCourse.get());
        coursesRepository.save(existingCourse.get());
        repository.save(existingUser.get());
        response.setMessage("User was un-enrolled from "+existingCourse.get().getName());
        response.setStatusCode(200);
        return response;
    }

    @Override
    public UserDto update(Long id, Users userDetails) {
        return null;
    }
}
