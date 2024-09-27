package com.uniteam.flashmemorizer.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "cards")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "card_term", columnDefinition = "text")
    private String term;

    @Column(name = "card_desc", columnDefinition = "text")
    private String desc;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="deck_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Deck deck;
}
