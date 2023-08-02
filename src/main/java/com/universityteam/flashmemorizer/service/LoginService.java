package com.universityteam.flashmemorizer.service;

import com.universityteam.flashmemorizer.login.Login;

public interface LoginService {
    Login login(String username, String password);
}
