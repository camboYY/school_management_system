package com.university.cambodia.courses.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Assignment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String assignmentId;
    private String title;
    private String body;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "student_assignment_mapping",
            joinColumns = @JoinColumn( name = "assignment_id", referencedColumnName = "assignmentId"),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "userId")
    )
    private List<AppUser> appUsers;
}
