package com.universityteam.flashmemorizer.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Data
@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, length = 50)
    private String username;

    @Column(name = "pass_hash", length = 100)
    private String pass;

    @Column(unique = true, length = 50)
    private String email;

    @Column(name = "full_name", length = 100)
    private String fullName;

    @Column(updatable = false)
    private Date registration;

    @Column(name = "last_login")
    private Date lastLogin;
}
