package com.university.cambodia.courses.repository;

import com.university.cambodia.courses.entity.Guardian;
import com.university.cambodia.courses.entity.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;


    @Test
    public void saveStudentWithGuardian() {
        Guardian guardian = Guardian.builder().
                email("mook.me@gmail.com")
                .name("Gauardianame")
                .mobile("99787774444")
                .build();

        Student student = Student.builder()
                .firstName("Sivahn")
                .email("student@yahoo.com")
                .lastName("Upworkd")
                .guardian(guardian)
                .build();
        studentRepository.save(student);
    }

    @Test
    public void findByFirstName () {
        List<Student> studentList = studentRepository.findByFirstName("Sivahn");
        System.out.println(studentList+"studentlist");

    }

    @Test
    public void findByFirstNameContaining () {

        List<Student> studentList = studentRepository.findByFirstNameContaining("Siv");
        System.out.println(studentList+"studentList");
    }

    @Test
    public void findByLastNameNotNull () {
        List<Student> studentList = studentRepository.findByLastNameNotNull();
        System.out.println(studentList+"studentList");
    }

    @Test
    public void getStudentByEmailAddress () {
        Student student = studentRepository.getStudentByEmailAddress("student@yahoo.com");
        System.out.println(student+"student");
    }

    @Test
    public void getStudentFirstNAmeByEmailAddress () {
        String firstName = studentRepository.getStudentFirstNAmeByEmailAddress("student@yahoo.com");
        System.out.println(firstName+"student");
    }

    @Test
    public void findByGuardianName () {
        List<Student> studentList = studentRepository.findByGuardianName("nikhil");
        System.out.println(studentList+"studentList");
    }

    @Test
    public void getStudentByEmailAddressNative () {
        Student student = studentRepository.getStudentByEmailAddressNative("student@yahoo.com");
        System.out.println(student+"student");
    }

    @Test
    public void getStudentByEmailAddressNativeParam () {
        Student student = studentRepository.getStudentByEmailAddressNativeParam("student@yahoo.com");
        System.out.println(student+"student");
    }

    @Test
    public void updateStudentByFirstNameAndEmail () {
        int student = studentRepository.updateStudentByFirstNameAndEmail( "lookup","student@yahoo.com");
        System.out.println(student+"student");
    }
}