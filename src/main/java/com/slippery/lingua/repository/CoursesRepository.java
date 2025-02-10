package com.slippery.lingua.repository;

import com.slippery.lingua.models.Courses;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoursesRepository extends JpaRepository<Courses,Long> {
    Courses findByNameEqualsIgnoreCase(String name);
}
