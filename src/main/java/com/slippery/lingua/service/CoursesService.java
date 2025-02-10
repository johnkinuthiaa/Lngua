package com.slippery.lingua.service;

import com.slippery.lingua.dto.CourseDto;
import com.slippery.lingua.models.Courses;

public interface CoursesService {
    CourseDto createNewCourse(Courses courseDetails);
    CourseDto deleteCourseById(Long courseId);
    CourseDto findCourseById(Long courseId);
    CourseDto deleteAllCourses();
    CourseDto updateCourse(Courses courseDetails,Long id);

}
