package com.universityteam.flashmemorizer.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "decks")
@EnableAutoConfiguration
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Deck {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "deck_name", nullable = false, length = 50)
    private String name;

    @Column(name = "deck_desc", nullable = false, length = 500)
    private String desc;

    @Column(nullable = false)
    private Date creation;

    @Column(name = "last_modified", nullable = false)
    private Date modified;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "deck", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Card> cards;

    @OneToMany(mappedBy = "deck", cascade = CascadeType.ALL)
    private List<SharedDeck> sharedDecks;
}
