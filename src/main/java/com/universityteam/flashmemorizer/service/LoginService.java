package com.universityteam.flashmemorizer.service;

import com.universityteam.flashmemorizer.entity.Login;

public interface LoginService {
    Login login(String username, String password);
}
