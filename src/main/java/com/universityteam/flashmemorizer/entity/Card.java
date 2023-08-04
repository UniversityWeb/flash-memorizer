package com.universityteam.flashmemorizer.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@Table(name = "cards")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "card_term", length = 100)
    private String term;

    @Column(name = "card_desc", length = 1000)
    private String desc;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="deck_id")
    private Deck deck;

    @OneToMany(mappedBy = "card", orphanRemoval = true, cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private List<UserCard> userCards;
}
