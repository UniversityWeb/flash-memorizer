package com.universityteam.flashmemorizer.entity;

import com.universityteam.flashmemorizer.enums.ERating;
import com.universityteam.flashmemorizer.keys.UserCardId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import java.util.Date;

@Data
@Entity
@IdClass(UserCardId.class)
@Table(name = "user_card")
@EnableAutoConfiguration
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserCard {
    @Id
    @Column(name = "card_id")
    private Long cardId;

    @Id
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "last_review", nullable = false)
    private Date lastReview;

    @Column(name = "review_count", nullable = false)
    private Long reviewCount;

    @Column(name = "next_interval", nullable = false)
    private Long interval;

    @Enumerated(EnumType.STRING)
    private ERating rating;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "card_id", insertable = false, updatable = false, nullable = false)
    private Card card;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", insertable = false, updatable = false, nullable = false)
    private User user;
}
