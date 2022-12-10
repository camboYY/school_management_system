package com.university.cambodia.courses.repository;

import com.university.cambodia.courses.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    public List<Student> findByFirstName (String firstName);
    public List<Student> findByFirstNameContaining(String firstName);
    public List<Student> findByLastNameNotNull ();
    public List<Student> findByGuardianName (String guardianName);
    @Query("select s from Student s where s.email = ?1") // Student is Entity name is not from table name and email is property of Student entity
    public Student getStudentByEmailAddress (String email);
    @Query("select s.firstName from Student s where s.email = ?1")
    public String getStudentFirstNAmeByEmailAddress (String email);
    @Query(value = "SELECT s.* FROM tbl_student s WHERE s.email_address = ?1",nativeQuery = true)
    Student getStudentByEmailAddressNative (String emailAddress);
    @Query(value = "SELECT s.* FROM tbl_student s WHERE s.email_address = :emailAddress",nativeQuery = true)
    Student getStudentByEmailAddressNativeParam (@Param("emailAddress") String emailAddress);
    @Modifying
    @Query(value = "update tbl_student  set first_name = ?1 where email_address = ?2", nativeQuery = true)
    @Transactional
    int updateStudentByFirstNameAndEmail (String firstName, String email);
}
