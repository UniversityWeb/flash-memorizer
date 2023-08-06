package com.universityteam.flashmemorizer.entity;

import java.util.Calendar;
import java.util.Date;

import jakarta.persistence.*;
import lombok.*;


@Data
@Getter
@Setter
@Entity
@NoArgsConstructor
@IdClass(VerificationToken.class)
@Table(name = "verification_token")
@AllArgsConstructor
@Builder
public class VerificationToken {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "token", length = 255)
    private String token;

    @Column(name = "expirationTime")
    private Date expirationTime;

    private static final int EXPIRATION_TIME = 15;

    @OneToOne
    @JoinColumn(name = "user_id")
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
