package com.university.cambodia.courses.repository;

import com.university.cambodia.courses.entity.Course;
import com.university.cambodia.courses.entity.Student;
import com.university.cambodia.courses.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CourseRepositoryTest {

    @Autowired
    private CourseRepository courseRepository;

    @Test
    public void getCourses () {
        List<Course> courses = courseRepository.findAll();
        System.out.println(courses);
    }

    @Test
    public void saveCourseWithTeacher () {
        Teacher teacher = Teacher.builder()
                .lastName("Yous")
                .firstName("Yoeun")
                .build();

        Course course = Course.builder()
                .credit(12)
                .title("React native")
                .teacher(teacher)
                .build();
        courseRepository.save(course);

    }

    @Test
    public void findAllPagination () {
        Pageable firstPageWithThreeRecords = PageRequest.of(0,3);
        Pageable secondPageWithThreeRecords =  PageRequest.of(1, 2);
        List<Course> courses = courseRepository.findAll(secondPageWithThreeRecords).getContent();
        Long totalElement  = courseRepository.findAll(secondPageWithThreeRecords).getTotalElements();
        Integer totalRecord = courseRepository.findAll(secondPageWithThreeRecords).getTotalPages();
        System.out.println("totalEmenents = "+ totalElement);
        System.out.println("total pages = "+ totalRecord);
        System.out.println("course = " + courses);
    }

    @Test
    public void findAllSorting () {
        Pageable sortByTitle = PageRequest.of(0,2, Sort.by("title"));
        Pageable sortByCreditDesc = PageRequest.of(0,2, Sort.by("credit").descending());
        Pageable sortByTitleAndCreditDesc = PageRequest.of(0,2, Sort.by("title").descending().and(Sort.by("credit")));
        List<Course> courses = courseRepository.findAll(sortByCreditDesc).getContent();
        System.out.println("sorted" +  courses);
    }

    @Test
    public void findByTitleContaining () {
        Pageable sortByTitle = PageRequest.of(0,2, Sort.by("title"));
        List<Course> courses = courseRepository.findByTitleContaining("DBA", sortByTitle);
        System.out.println(courses);
    }

    @Test
    public void saveCourseWithStudentAndTeacher () {
        Teacher teacher = Teacher.builder()
                .firstName("Teacher")
                .lastName("Youk")
                .build();
        Student student = Student.builder()
                .email("yahoo@gmail.com")
                .firstName("nika")
                .lastName("joker")
                .build();
        Course course = Course.builder()
                .teacher(teacher)
                .title("Math")
                .credit(12)
                .build();
        course.addStudent(student);
        courseRepository.save(course);
    }
}