package com.universityteam.flashmemorizer.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

@Data
@Entity
@Table(name = "login")
@EnableAutoConfiguration
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Login {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(name = "pass_hash", nullable = false)
    private  String password;
}
