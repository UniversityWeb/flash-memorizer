package com.universityteam.flashmemorizer.service.impl;

import com.universityteam.flashmemorizer.entity.User;
import com.universityteam.flashmemorizer.repository.UserRepository;
import com.universityteam.flashmemorizer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepo;

    @Override
    public User add(User user) {
        try {
            return userRepo.save(user);
        } catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public User update(User user) {
        Optional<User> optUser = userRepo.findById(user.getId());
        if (optUser.isEmpty()) return null;
        try {
            return userRepo.save(user);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public User getById(Long Id) {
        return userRepo.findById(Id).get();
    }
}
