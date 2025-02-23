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
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String resources;
    @Lob
    private String content;
    private LocalDateTime createdOn =LocalDateTime.now();
    private LocalDateTime updatedOn;
    @ManyToOne
    private Courses course;
    @OneToOne
    private Quiz lessonQuiz;
}
