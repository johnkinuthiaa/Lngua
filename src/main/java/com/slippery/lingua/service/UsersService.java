package com.slippery.lingua.service;

import com.slippery.lingua.dto.UserDto;
import com.slippery.lingua.models.Users;

public interface UsersService {
    UserDto register(Users userDetails);
    Users add(Users users);
    UserDto login(Users userDetails);
    UserDto findById(Long id);
    UserDto deleteById(Long id);
    UserDto update(Long id,Users userDetails);
    UserDto getAllUserEnrolledCourses(Long id);
    UserDto enrollUserToCourse(Long UserId,Long courseId);
    UserDto unEnrollUserFromCourse(Long UserId,Long courseId);
}
