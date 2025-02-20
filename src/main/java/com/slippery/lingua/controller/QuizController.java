package com.slippery.lingua.controller;

import com.slippery.lingua.dto.QuizDto;
import com.slippery.lingua.models.Quiz;
import com.slippery.lingua.service.QuizService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/quiz")
public class QuizController {
    private final QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }
    @PostMapping("/create")
    public ResponseEntity<QuizDto> createNewQuiz(@RequestBody Quiz quiz, @RequestParam Long lessonId){
        return ResponseEntity.ok(quizService.createNewQuiz(quiz, lessonId));
    }
}
