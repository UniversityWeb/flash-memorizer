package com.universityteam.flashmemorizer.service;

import com.universityteam.flashmemorizer.entity.User;

public interface UserService {
    User add(User user);
    User update(User user);
    User getById(Long Id);
}
