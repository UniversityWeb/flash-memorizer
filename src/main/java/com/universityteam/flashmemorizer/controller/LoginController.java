package com.universityteam.flashmemorizer.controller;

import com.universityteam.flashmemorizer.entity.Login;
import com.universityteam.flashmemorizer.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Objects;

@Controller
public class LoginController{

    @Autowired
    private LoginService userService;

    @GetMapping("/home")

    public ModelAndView login(){
        ModelAndView mav = new ModelAndView("login");
        mav.addObject("user", new Login());
        return mav;
    }

    @PostMapping("/home")
    public String login(@org.jetbrains.annotations.NotNull @ModelAttribute("user") Login user){
        Login oauthUser = userService.login(user.getUsername(), user.getPassword());

        System.out.println(oauthUser);
        if(Objects.nonNull(oauthUser)){
            return "redirect:/" + user.getId();
        }
        else{
            return "redirect:/home";
        }
    }
}
