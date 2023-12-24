package com.uniteam.flashmemorizer.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@IdClass(VerificationToken.class)
@Table(name = "verification_token")
@AllArgsConstructor
@Builder
@Getter
@Setter
public class VerificationToken implements Serializable {
    @Id
    @Column(name="id", nullable=false)
    @GeneratedValue(strategy=GenerationType.AUTO, generator="EVENT_SEQ")
    @SequenceGenerator(name="EVENT_SEQ",
            sequenceName="EVENT_SEQ",
            allocationSize=1)
    private Long id;

    @Column(name = "expiration_time")
    private Date expirationTime;

    @Column(name = "token", length = 255)
    private String token;


    private static final int EXPIRATION_TIME = 15;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private User user;
    
    public VerificationToken(String token, User user){
        super();
        this.token = token;
        this.user = user;
        this.expirationTime = this.getTokenExpirationTime(); 
    }

    public VerificationToken(String token){
        super();
        this.token = token;
        this.expirationTime = this.getTokenExpirationTime(); 
    }

    public Date getTokenExpirationTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(new Date().getTime()); 
        calendar.add(Calendar.MINUTE, EXPIRATION_TIME); 
        return new Date(calendar.getTime().getTime());
    }
}
