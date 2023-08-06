package com.universityteam.flashmemorizer.controller;

import com.universityteam.flashmemorizer.entity.Login;
import com.universityteam.flashmemorizer.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping("/login")
public class LoginController{

    @Autowired
    private LoginService userService;

    @PostMapping
    public String login(@org.jetbrains.annotations.NotNull @ModelAttribute("user") Login user){
        Login oauthUser = userService.login(user.getUsername(), user.getPassword());

        System.out.println(oauthUser);
        if(Objects.nonNull(oauthUser)){
            return "redirect:/" + user.getId();
        }
        else{
            return "redirect:/login";
        }
    }
}
