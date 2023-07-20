package com.universityteam.flashmemorizer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.universityteam.flashmemorizer.entity.User;
import com.universityteam.flashmemorizer.repository.UserRepository;

import lombok.Builder;

@Builder
@Controller
public class HomeController {

    @Autowired
    private UserRepository userRepo;

    @GetMapping("/home")
    public String hello() {
        return "home";
    }
    
    @PostMapping("/home")
    public String processLogin(@RequestParam("username") String username, @RequestParam("pass_hash") String pass_hash) {
        User account_recently = User.builder().username(username).pass(pass_hash).build();
        if(checkAccount(account_recently))
            return "redirect:/home";
        return "redirect:/home"; //
    }

    private boolean checkAccount(User account)
    {
        return true;
    }
}
