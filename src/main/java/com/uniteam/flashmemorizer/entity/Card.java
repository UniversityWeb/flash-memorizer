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

    @Lob
    @Column(name = "card_term", columnDefinition = "LONGTEXT")
    private String term;

    @Lob
    @Column(name = "card_desc", columnDefinition = "LONGTEXT")
    private String desc;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="deck_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Deck deck;
}
