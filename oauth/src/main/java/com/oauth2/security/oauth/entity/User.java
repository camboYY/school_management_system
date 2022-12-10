package com.oauth2.security.oauth.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "user_sequence", sequenceName = "user_sequence", allocationSize = 1)
    private Integer id;
    private String firstName;
    private String lastName;
    @Column(length = 60)
    private String password;
    @Column(unique = true)
    private String username;
    private String role;
    private boolean enabled = false;
    private String email;
}
