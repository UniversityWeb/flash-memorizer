package com.universityteam.flashmemorizer.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

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

    @Column(updatable = false)
    private Date creation = new Date();

    @Column(name = "last_modified")
    private Date modified = new Date();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

<<<<<<< HEAD

    @OneToMany(mappedBy = "deck", cascade = {CascadeType.ALL, CascadeType.REMOVE}, orphanRemoval = true)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
=======
    @OneToMany(mappedBy = "deck", orphanRemoval = true, cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
>>>>>>> ecca4ca (fix the recursive problem with @ToString.Exclude)
    private List<Card> cards;

    @OneToMany(mappedBy = "deck", orphanRemoval = true, cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private List<SharedDeck> sharedDecks;
}
