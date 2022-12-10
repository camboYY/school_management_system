package com.university.cambodia.courses.repository;

import com.university.cambodia.courses.entity.Course;
import com.university.cambodia.courses.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TeacherRepositoryTest {

    @Autowired
    private TeacherRepository teacherRepository;

    @Test
    public void saveTeacher () {

//                Course courseDBA = Course.builder()
//                .title("DBA")
//                .credit(4)
//                .build();
//        Course courseJAVA = Course.builder()
//                .title("JAVA Spring")
//                .credit(10)
//                .build();
//
//        Teacher teacher = Teacher.builder()
//                .firstName("Joker")
//                .lastName("maker")
//                .courses(List.of(courseDBA, courseJAVA))
//                .build();
//        teacherRepository.save(teacher);
    }
}