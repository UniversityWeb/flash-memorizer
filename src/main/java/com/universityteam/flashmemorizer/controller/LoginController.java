package com.universityteam.flashmemorizer.controller;

import com.universityteam.flashmemorizer.dto.UserDTO;
import com.universityteam.flashmemorizer.records.LoginRequest;
import com.universityteam.flashmemorizer.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Objects;

@Controller
@RequiredArgsConstructor
@RequestMapping("/login-process")
public class LoginController{

    @Autowired
    private final UserService userService;

    @PostMapping
    public RedirectView login(@org.jetbrains.annotations.NotNull @ModelAttribute("user") LoginRequest user){
        System.out.println(user);
        UserDTO oauthUser = userService.loginUser(user);
        System.out.println(oauthUser);
        if(Objects.nonNull(oauthUser)){
            return new RedirectView("/user/" + oauthUser.getId(), true);
        }
        else{
            return new RedirectView("/home", true);
        }
    }

}
