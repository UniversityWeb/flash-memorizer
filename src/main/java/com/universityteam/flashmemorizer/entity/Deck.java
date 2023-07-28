package com.universityteam.flashmemorizer.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "decks")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Deck {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "deck_name", length = 50)
    private String name;

    @Column(name = "deck_desc", length = 500)
    private String desc;

    private Date creation = new Date();

    @Column(name = "last_modified")
    private Date modified = new Date();

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="user_id")
    private User user = new User();

    @OneToMany(mappedBy = "deck", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Card> cards = new ArrayList<>();

    @OneToMany(mappedBy = "deck", cascade = CascadeType.ALL)
    private List<SharedDeck> sharedDecks = new ArrayList<>();
}
