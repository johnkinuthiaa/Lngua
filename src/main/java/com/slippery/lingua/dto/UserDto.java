package com.slippery.lingua.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.slippery.lingua.models.Courses;
import com.slippery.lingua.models.Users;
import lombok.Data;

import java.util.List;
import java.util.stream.IntStream;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto {
    private String message;
    private int statusCode;
    private Users user;
    private List<Users> usersList;
    private int authToken;
    private Courses course;
    private List<Courses> coursesList;
    private String jwtToken;
}
