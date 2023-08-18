package com.universityteam.flashmemorizer.controller;

import com.universityteam.flashmemorizer.dto.UserDTO;
import com.universityteam.flashmemorizer.exception.DeckNotFoundException;
import com.universityteam.flashmemorizer.exception.UserNotFoundException;
import com.universityteam.flashmemorizer.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UserController {

    private final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @ResponseBody
    @PostMapping("/get-by-username")
    public UserDTO getByUsername (@RequestBody String username) {
        System.out.println("an nè!");
        try {
            System.out.println(username);
           return   userService.getByUsername(username.replaceAll("\"", ""));
        } catch (UserNotFoundException e) {
            log.error("User not found with username: {}", username, e);
            return null;
        }
    }
}
