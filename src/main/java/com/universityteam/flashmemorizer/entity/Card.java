package com.universityteam.flashmemorizer.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
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
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "card_term")
    private String term;

    @Column(name = "card_desc")
    private String desc;

    private Date creation;

    @Column(name = "last_modified")
    private Date modified;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "deck_id")
    private Long deckId;
}
