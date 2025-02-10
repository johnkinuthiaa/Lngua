package com.slippery.lingua.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Courses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String language;
    @Lob
    private String description;
    private String level;
    @JsonIgnore
    @ManyToMany
    private List<Users> usersEnrolled;
    @OneToMany
    private List<Lesson> lessonsInCourse;
    private LocalDateTime createdOn =LocalDateTime.now();
    private LocalDateTime updatedOn;
}
