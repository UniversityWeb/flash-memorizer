package com.universityteam.flashmemorizer.controller;

import com.universityteam.flashmemorizer.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

//    @GetMapping("/{userId}")
//    public UserDTO getUserID(@PathVariable long userId){
//        return new UserDTO(userId,)
//    }
}
