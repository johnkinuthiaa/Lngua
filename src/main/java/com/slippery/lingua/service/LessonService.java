package com.slippery.lingua.service;

import com.slippery.lingua.dto.LessonDto;
import com.slippery.lingua.models.Lesson;

public interface LessonService {
    LessonDto createNewLesson(Lesson lessonDetails,Long courseId);
    LessonDto updateLesson(Lesson lesson,Long lessonId);
    LessonDto findLessonById(Long lessonId);
    LessonDto deleteLessonById(Long lessonId);
}
