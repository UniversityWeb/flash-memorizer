package com.universityteam.flashmemorizer.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, length = 50)
    private String username;

    @Column(name = "pass_hash", length = 100)
    private String pass;

    @Column(unique = true, length = 255)
    private String email;

    @Column(name = "full_name", length = 100)
    private String fullName;

    @Column(name = "registration")
    private Date registration;

    @Column(name = "last_login")
    private Date lastLogin;

    @Column(name = "role", length = 45)
    private String role;
    
    private boolean isEnabled = false;

    @OneToMany(mappedBy = "user", orphanRemoval = true, cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private List<Deck> decks;

    @OneToMany(mappedBy = "user", orphanRemoval = true, cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private List<UserCard> userCards;

    @OneToMany(mappedBy = "user", orphanRemoval = true, cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private List<VerificationToken> verificationTokens;
}
