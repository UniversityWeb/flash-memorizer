package com.universityteam.flashmemorizer.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import java.util.Date;

@Data
@Entity
@Table(name = "cards")
@EnableAutoConfiguration
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

    private Date creation;

    @Column(name = "last_modified")
    private Date modified;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="deck_id")
    private Deck deck;
}
