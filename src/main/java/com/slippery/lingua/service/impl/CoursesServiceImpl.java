package com.slippery.lingua.service.impl;

import com.slippery.lingua.dto.CourseDto;
import com.slippery.lingua.models.Courses;
import com.slippery.lingua.repository.CoursesRepository;
import com.slippery.lingua.service.CoursesService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class CoursesServiceImpl implements CoursesService {
    private final CoursesRepository coursesRepository;

    public CoursesServiceImpl(CoursesRepository coursesRepository) {
        this.coursesRepository = coursesRepository;
    }

    @Override
    public CourseDto createNewCourse(Courses courseDetails) {
        CourseDto response =new CourseDto();
        Courses existingCourse =coursesRepository.findByNameEqualsIgnoreCase(courseDetails.getName());
        if(existingCourse !=null){
            response.setMessage("Course with the name "+existingCourse.getName()+" already exists");
            response.setStatusCode(200);
            return response;
        }
        courseDetails.setLessonsInCourse(new ArrayList<>());
        courseDetails.setUsersEnrolled(new ArrayList<>());
        courseDetails.setUpdatedOn(null);
        coursesRepository.save(courseDetails);
        response.setMessage("New "+courseDetails.getName()+" course created successfully");
        response.setStatusCode(200);
        response.setCourse(courseDetails);
        return response;
    }

    @Override
    public CourseDto deleteCourseById(Long courseId) {
        Optional<CourseDto> existingCourse = Optional.ofNullable(findCourseById(courseId));
        CourseDto response =new CourseDto();
        if(existingCourse.get().getStatusCode() !=200){
            response.setStatusCode(404);
            response.setMessage(existingCourse.get().getMessage());
            return response;
        }
        coursesRepository.deleteById(courseId);
        response.setMessage("Course deleted successfully");
        response.setStatusCode(200);
        return response;
    }

    @Override
    public CourseDto findCourseById(Long courseId) {
        Optional<Courses> existingCourse =coursesRepository.findById(courseId);
        CourseDto response =new CourseDto();
        if(existingCourse.isEmpty()){
            response.setMessage("Course not found");
            response.setStatusCode(404);
            return response;
        }
        response.setCourse(existingCourse.get());
        response.setMessage("course with id "+courseId);
        response.setStatusCode(200);
        return response;
    }

    @Override
    public CourseDto deleteAllCourses() {
        CourseDto response =new CourseDto();
        coursesRepository.deleteAll();
        response.setMessage("All courses deleted");
        response.setStatusCode(200);
        return response;
    }

    @Override
    public CourseDto updateCourse(Courses courseDetails, Long id) {
        return null;
    }
}
