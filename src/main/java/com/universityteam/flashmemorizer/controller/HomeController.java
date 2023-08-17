package com.universityteam.flashmemorizer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

    @GetMapping("/access-denied")
    public String accessDenied(){
        return "access-denied";
    }
}
