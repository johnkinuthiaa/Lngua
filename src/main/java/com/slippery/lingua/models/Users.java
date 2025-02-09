package com.slippery.lingua.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

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
    private String email;
    private String password;
    private String nativeLanguage;
    @Lob
    private String interests;
    private LocalDateTime registeredOn =LocalDateTime.now();
    private Boolean isActive;
    private Long streak;
    private String role;
}
