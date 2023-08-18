package com.universityteam.flashmemorizer.controller;
import com.universityteam.flashmemorizer.dto.UserDTO;
import com.universityteam.flashmemorizer.records.LoginRequest;
import com.universityteam.flashmemorizer.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.logging.Logger;

@Controller
@RequiredArgsConstructor
@RequestMapping("/login-process")
public class LoginController{
    @Autowired
    private final UserService userService;

    @PostMapping
    public String login(@org.jetbrains.annotations.NotNull @ModelAttribute("user") LoginRequest user){

        Logger logger = Logger.getLogger(LoginController.class.getName());
        logger.info("User: "+ user);

        UserDTO oauthUser = userService.loginUser(user);

        if(Objects.isNull(oauthUser))
            return "redirect:/home?loginFailed";

        return "redirect:/user/" + oauthUser.getId();
    }

}