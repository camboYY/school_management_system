package com.university.cambodia.courses.repository;

import com.university.cambodia.courses.entity.Course;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {
    List<Course> findByTitleContaining (String title, Pageable pageable);
}
