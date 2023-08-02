package com.universityteam.flashmemorizer.login;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "login")
@Getter
@Setter
public class Login {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private  String password;

    public Login(){

    }

    public Login(Long id, String username, String password){
        this.id = id;
        this.username = username;
        this.password = password;
    }
}
