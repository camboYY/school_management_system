package com.university.cambodia.courses.repository;

import com.university.cambodia.courses.entity.Course;
import com.university.cambodia.courses.entity.CourseMaterial;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CourseMaterialRepositoryTest {

    @Autowired
    private CourseMaterialRepository courseMaterialRepository;

    @Test
    public void saveCourseMaterial () {
        Course course = Course.builder()
                .title(".net")
                .credit(12)
                .build();
        CourseMaterial courseMaterial = CourseMaterial.builder()
                .course(course)
                .url("www.google2.com")
                .build();
        courseMaterialRepository.save(courseMaterial);
    }

    @Test
    public void fetchCourseMaterials () {
        List<CourseMaterial> courseMaterials = courseMaterialRepository.findAll();
        System.out.println(courseMaterials+"courses Material");
    }

}