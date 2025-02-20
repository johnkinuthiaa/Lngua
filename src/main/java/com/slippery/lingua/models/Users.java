package com.slippery.lingua.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String email;
    private String nativeLanguage;
    private String interests;
    private LocalDateTime registeredOn =LocalDateTime.now();
    private Boolean isActive;
    private Long streak =0L;
    private String role;
    @ManyToMany
    private List<Courses> coursesEnrolled =new ArrayList<>();
}
