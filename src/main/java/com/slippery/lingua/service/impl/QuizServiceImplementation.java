package com.slippery.lingua.service.impl;

import com.slippery.lingua.dto.QuizDto;
import com.slippery.lingua.models.Lesson;
import com.slippery.lingua.models.Quiz;
import com.slippery.lingua.repository.LessonRepository;
import com.slippery.lingua.repository.QuizRepository;
import com.slippery.lingua.service.QuizService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class QuizServiceImplementation implements QuizService {
    private final QuizRepository repository;
    private final LessonRepository lessonRepository;

    public QuizServiceImplementation(QuizRepository repository, LessonRepository lessonRepository) {
        this.repository = repository;
        this.lessonRepository = lessonRepository;
    }

    @Override
    public QuizDto createNewQuiz(Quiz quiz,Long lessonId) {
        QuizDto response =new QuizDto();
        Optional<Lesson> existingLesson =lessonRepository.findById(lessonId);
        if(existingLesson.isEmpty()){
            response.setMessage("Lesson not found");
            response.setStatusCode(404);
            return response;
        }
        quiz.setLessonQuiz(existingLesson.get());
        quiz.setUpdatedOn(null);
        repository.save(quiz);
        response.setMessage("New quiz created");
        response.setStatusCode(200);
        response.setQuiz(quiz);
        return null;
    }
}
