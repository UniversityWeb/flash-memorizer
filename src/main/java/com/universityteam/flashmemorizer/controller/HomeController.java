package com.universityteam.flashmemorizer.controller;

import com.universityteam.flashmemorizer.records.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.universityteam.flashmemorizer.service.UserService;

@Controller
public class HomeController {
    @Autowired
    private UserService userService;

    @GetMapping("/home")
    public String showHomePage() {
        return "home";
    }
}
