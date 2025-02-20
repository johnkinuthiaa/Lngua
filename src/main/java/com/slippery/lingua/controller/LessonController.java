package com.slippery.lingua.controller;

import com.slippery.lingua.dto.LessonDto;
import com.slippery.lingua.models.Lesson;
import com.slippery.lingua.service.LessonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/lessons")
public class LessonController {
    private final LessonService service;

    public LessonController(LessonService service) {
        this.service = service;
    }
    @PostMapping("/create")
    public ResponseEntity<LessonDto> createNewLesson(@RequestBody Lesson lesson, @RequestParam Long courseId) {
        return ResponseEntity.ok(service.createNewLesson(lesson, courseId));
    }

    @PutMapping("/remove-from-course")
    public ResponseEntity<LessonDto> removeLessonFromCourse(@RequestParam Long lessonId,@RequestParam Long courseId) {
        return ResponseEntity.ok(service.removeLessonFromCourse(lessonId, courseId));
    }
    @GetMapping("/{id}/get")
    public ResponseEntity<LessonDto> findLessonById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findLessonById(id));
    }
}
