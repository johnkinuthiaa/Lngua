package com.slippery.lingua.service;

import com.slippery.lingua.dto.QuizDto;
import com.slippery.lingua.models.Quiz;

public interface QuizService {
    QuizDto createNewQuiz(Quiz quiz,Long lessonId);
}
