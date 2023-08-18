package com.universityteam.flashmemorizer.controller;

import com.universityteam.flashmemorizer.dto.UserDTO;
import com.universityteam.flashmemorizer.entity.User;
import com.universityteam.flashmemorizer.entity.UserHolder;
import com.universityteam.flashmemorizer.exception.PasswordMismatchException;
import com.universityteam.flashmemorizer.exception.UserNotFoundException;
import com.universityteam.flashmemorizer.form.ChangePassForm;
import com.universityteam.flashmemorizer.service.UserService;
import com.universityteam.flashmemorizer.service.impl.CustomUserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Locale;

@Controller
@RequiredArgsConstructor
@RequestMapping("")
public class UserController {

    private final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;
    private CustomUserDetailsServiceImpl userDetailsService;
    @GetMapping
    public List<UserDTO> getUsers (){
        return userService.getUsers();
    }

    @GetMapping("/edit")
    public String getDetails(@RequestParam Long userId, Model m) {
        try {
            UserDTO user = userService.getById(userId);
            ChangePassForm passForm = ChangePassForm.builder().userId(userId).build();
            log.info("Users retrieved successfully for userId: {}", userId);
            m.addAttribute("passForm", passForm);
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
            userService.updateNotPassword(user);
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

    @PostMapping("/change-password")
    public String changePassword(ChangePassForm passForm, RedirectAttributes ra) {
        try {
            userService.changePassword(passForm);
            log.info("Password changed for user with ID: {}", passForm.getUserId());
            ra.addFlashAttribute("successMsg", "Password updated successfully!");
        } catch (PasswordMismatchException e) {
            log.error("Error while changing password with userId: {}", passForm.getUserId(),e);
            ra.addFlashAttribute("errorMsg", "Password and confirmation do not match");
        } catch (UserNotFoundException e) {
            log.error(e.getMessage());
            return "redirect:/login";
        }
        return "redirect:/users/edit?userId=" + passForm.getUserId();
    }

    @GetMapping("/user/{id}")
    @PreAuthorize("#id == authentication.principal.userHolder.id")
    public String getUserHome(@PathVariable("id") Long id, Model model, Authentication authentication) {
        UserHolder userHolder = (UserHolder) authentication.getPrincipal();
        model.addAttribute("fullName", userHolder.getUserHolder().getFullName());
        return "user-home";
    }
}

