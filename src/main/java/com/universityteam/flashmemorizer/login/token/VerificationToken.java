package com.universityteam.flashmemorizer.login.token;

import java.util.Calendar;
import java.util.Date;

import com.universityteam.flashmemorizer.dto.UserDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class VerificationToken {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String token;
    private Date expirationTime;
    private static final int EXPIRATION_TIME = 15;

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserDTO user;
    
    public VerificationToken(String token, UserDTO user){
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
