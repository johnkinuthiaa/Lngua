package com.slippery.lingua.controller;

import com.slippery.lingua.dto.CourseDto;
import com.slippery.lingua.models.Courses;
import com.slippery.lingua.service.CoursesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/courses")
public class CoursesController {
    private final CoursesService service;

    public CoursesController(CoursesService service) {
        this.service = service;
    }
    @PostMapping(path = "/create/new",consumes = "application/json")
    public ResponseEntity<CourseDto> createNewCourse(@RequestBody Courses courseDetails){
        return ResponseEntity.ok(service.createNewCourse(courseDetails));
    }

    @GetMapping("/get/id")
    public ResponseEntity<CourseDto> getCourseById(@RequestParam Long id) {
        return ResponseEntity.ok(service.findCourseById(id));
    }


    @DeleteMapping("/{id}/delete")
    public ResponseEntity<CourseDto> deleteCourseById(@PathVariable Long id) {
        return ResponseEntity.ok(service.deleteCourseById(id));
    }

    @DeleteMapping("/delete/all")
    public ResponseEntity<CourseDto> deleteAll() {
        return ResponseEntity.ok(service.deleteAllCourses());
    }
}
