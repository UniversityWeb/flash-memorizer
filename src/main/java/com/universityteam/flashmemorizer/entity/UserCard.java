package com.universityteam.flashmemorizer.entity;

import com.universityteam.flashmemorizer.enums.ERating;
import com.universityteam.flashmemorizer.keys.UserCardId;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Data
@Entity
@IdClass(UserCardId.class)
@Table(name = "user_cards")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class UserCard {
    @Id
    @Column(name = "card_id")
    private Long cardId;

    @Id
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "last_review")
    private Date lastReview;

    @Column(name = "review_count")
    private Long reviewCount;

    @Column(name = "next_interval")
    private Long interval;

    @Enumerated(EnumType.STRING)
    private ERating rating;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "card_id", insertable = false, updatable = false)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Card card;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private User user;
}
