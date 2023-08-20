package com.universityteam.flashmemorizer.controller;

import com.universityteam.flashmemorizer.dto.UserHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.universityteam.flashmemorizer.service.UserService;

import java.util.logging.Logger;

@Controller
public class HomeController {
    @Autowired
    private UserService userService;

    @GetMapping("/home")
    public String showHomePage() {
        return "home";
    }

    @GetMapping("/access-denied")
    public String accessDenied(Authentication authentication){
        UserHolder userHolder = (UserHolder) authentication.getPrincipal();
        Logger logger = Logger.getLogger(HomeController.class.getName());
        logger.info("User: "+ userHolder.getUserHolder().getId());
        return "access-denied";
    }


}