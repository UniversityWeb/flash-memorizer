package com.universityteam.flashmemorizer.service.impl;

import com.universityteam.flashmemorizer.login.Login;
import com.universityteam.flashmemorizer.repository.LoginRepository;
import com.universityteam.flashmemorizer.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginRepository repo;

    public Login login(String username, String password){
        Login user = repo.findByUsernameAndPassword(username, password);
        return user;
    }
}
