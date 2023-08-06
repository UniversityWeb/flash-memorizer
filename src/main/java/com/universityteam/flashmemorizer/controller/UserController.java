package com.universityteam.flashmemorizer.controller;

import com.universityteam.flashmemorizer.converter.UserConverter;
import com.universityteam.flashmemorizer.entity.User;
import com.universityteam.flashmemorizer.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final UserConverter userConverter;

    @GetMapping
    public List<User> getUsers (){
        return userConverter.convertDtoToEntity(userService.getUsers());
    }
}
