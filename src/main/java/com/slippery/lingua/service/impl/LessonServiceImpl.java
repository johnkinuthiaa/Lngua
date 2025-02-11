package com.slippery.lingua.service.impl;

import com.slippery.lingua.dto.LessonDto;
import com.slippery.lingua.models.Courses;
import com.slippery.lingua.models.Lesson;
import com.slippery.lingua.repository.CoursesRepository;
import com.slippery.lingua.repository.LessonRepository;
import com.slippery.lingua.service.LessonService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LessonServiceImpl implements LessonService {
    private final LessonRepository repository;
    private final CoursesRepository coursesRepository;

    public LessonServiceImpl(LessonRepository repository, CoursesRepository coursesRepository) {
        this.repository = repository;
        this.coursesRepository = coursesRepository;
    }

    @Override
    public LessonDto createNewLesson(Lesson lessonDetails,Long courseId) {
        LessonDto response =new LessonDto();
        Optional<Courses> existingCourse =coursesRepository.findById(courseId);
        if(existingCourse.isEmpty()){
            response.setMessage("Course not found!");
            response.setStatusCode(404);
            return response;
        }
        lessonDetails.setUpdatedOn(null);
        lessonDetails.setCourse(existingCourse.get());
        repository.save(lessonDetails);
        var lessons =existingCourse.get().getLessonsInCourse();
        lessons.add(lessonDetails);
        existingCourse.get().setLessonsInCourse(lessons);
        coursesRepository.save(existingCourse.get());
        response.setMessage("New lesson added in "+existingCourse.get().getName());
        response.setStatusCode(200);
        return response;
    }

    @Override
    public LessonDto updateLesson(Lesson lesson, Long lessonId) {
        return null;
    }

    @Override
    public LessonDto findLessonById(Long lessonId) {
        return null;
    }

    @Override
    public LessonDto deleteLessonById(Long lessonId) {
        return null;
    }
}
