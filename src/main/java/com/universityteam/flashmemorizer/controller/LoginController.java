package com.universityteam.flashmemorizer.controller;

import com.universityteam.flashmemorizer.dto.LoginDTO;
import com.universityteam.flashmemorizer.dto.UserDTO;
import com.universityteam.flashmemorizer.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Controller
@RequiredArgsConstructor
@RequestMapping("/home")
public class LoginController{

    @Autowired
    private final UserService userService;
    @PostMapping
    public String login(@org.jetbrains.annotations.NotNull @ModelAttribute("user") LoginDTO user){
        UserDTO oauthUser = userService.loginUser(user);
        System.out.println(oauthUser);
        if(Objects.nonNull(oauthUser)){
            return "redirect:/user/" + oauthUser.getId();
        }
        else{
            return "redirect:/home";
        }
    }

}
