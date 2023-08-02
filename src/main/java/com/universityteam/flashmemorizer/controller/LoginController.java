package com.universityteam.flashmemorizer.controller;

import com.universityteam.flashmemorizer.login.Login;
import com.universityteam.flashmemorizer.service.LoginService;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Objects;

@Controller
public class LoginController{

    @Autowired
    private LoginService userService;

    @GetMapping("/login")

    public ModelAndView login(){
        ModelAndView mav = new ModelAndView("login");
        mav.addObject("user", new Login());
        return mav;
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("user") Login user){
        Login oauthUser = userService.login(user.getUsername(), user.getPassword());

        System.out.println(oauthUser);
        if(Objects.nonNull(oauthUser)){
            return "redirect:/home";
        }
        else{
            return "redirect:/login";
        }
    }
//
//    @RequestMapping(value = {"/logout"}, method = RequestMethod.POST)
//    public String logoutDo(HttpServletRequest request, HttpServletResponse reponse){
//        return "redirect:/home";
//    }
}
