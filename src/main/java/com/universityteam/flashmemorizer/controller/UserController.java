package com.universityteam.flashmemorizer.controller;

import com.universityteam.flashmemorizer.dto.UserDTO;
import com.universityteam.flashmemorizer.exception.UserNotFoundException;
import com.universityteam.flashmemorizer.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.util.StringUtils;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    private final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder encoder;

    @GetMapping
    public List<UserDTO> getUsers (){
        return userService.getUsers();
    }

    @GetMapping("/edit")
    public String getDetails(@RequestParam Long userId, Model m) {
        try {
            UserDTO user = userService.getById(userId);
            log.info("Users retrieved successfully for userId: {}", userId);
            m.addAttribute("user", user);
            return "user-profile";
        } catch (UserNotFoundException e) {
            log.error("Error while fetching users with userId: {}", userId, e);
            return "redirect:/login";
        }
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("user") UserDTO user, RedirectAttributes ra) {
        try {
            if (!StringUtils.isEmpty(user.getPass()))
                user.setPass(encoder.encode(user.getPass()));
            userService.update(user);
            log.info("User with userId: {} updated successfully!", user.getId());
            ra.addFlashAttribute("successMsg", "User updated successfully!");
        } catch (UserNotFoundException e) {
            log.error("Error updating user with userId: {}: {}", user.getId(), e.getMessage());
            ra.addFlashAttribute("errorMsg", e.getMessage());
        }
        return "redirect:/users/edit?userId=" + user.getId();
    }

    @PostMapping("/delete")
    public String delete(@ModelAttribute("user") UserDTO user) throws UserNotFoundException {
        // handle logout account
        //

        userService.delete(user.getId());
        return "redirect:/login";
    }
}
