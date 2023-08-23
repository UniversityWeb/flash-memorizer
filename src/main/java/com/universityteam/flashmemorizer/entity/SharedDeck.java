package com.universityteam.flashmemorizer.entity;

import com.universityteam.flashmemorizer.keys.SharedDeckId;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Data
@Entity
@IdClass(SharedDeckId.class)
@Table(name = "share_decks")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class SharedDeck {
    @Id
    @Column(name = "recipient_id")
    private Long recipientId;

    @Id
    @Column(name = "deck_id")
    private Long deckId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipient_id", referencedColumnName = "id", insertable = false, updatable = false)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private User recipient;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "deck_id", referencedColumnName = "id", insertable = false, updatable = false)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Deck deck;

    private Date creation;
}
