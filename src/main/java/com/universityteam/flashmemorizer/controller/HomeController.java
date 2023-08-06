package com.universityteam.flashmemorizer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.universityteam.flashmemorizer.entity.User;
import com.universityteam.flashmemorizer.service.UserService;

import lombok.Builder;

@Builder
@Controller
public class HomeController {

    @Autowired
    private UserService userService;

    @GetMapping("/home")
    public String hello() {
        return "home";
    }
}
