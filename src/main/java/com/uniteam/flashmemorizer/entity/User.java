package com.uniteam.flashmemorizer.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
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

    private boolean isEnabled = false;

    @OneToMany(mappedBy = "user", orphanRemoval = true, cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private List<Deck> decks;

    @OneToOne(mappedBy = "user", orphanRemoval = false, cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private VerificationToken verificationToken;
}
