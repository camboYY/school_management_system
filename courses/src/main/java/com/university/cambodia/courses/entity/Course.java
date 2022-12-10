package com.university.cambodia.courses.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Course {

    @Id
    @SequenceGenerator(name = "course_sequence", sequenceName = "course_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "course_sequence")
    private Integer courseId;
    private String title;
    private Integer credit;

    @OneToOne(mappedBy = "course")
    private CourseMaterial courseMaterial;

    @ManyToOne( cascade = CascadeType.ALL)
    @JoinColumn(name = "teacher_id", referencedColumnName = "teacherId")
    private Teacher teacher;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "student_course_mapping",
            joinColumns = @JoinColumn( name = "course_id", referencedColumnName = "courseId"),
            inverseJoinColumns = @JoinColumn(name = "", referencedColumnName = "studentId")
    )
    private List<Student> studentList;

    public void addStudent (Student student) {
        if(studentList == null) {
            studentList = new ArrayList<>();
        }
        studentList.add(student);
    }
}
