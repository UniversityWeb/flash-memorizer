package com.uniteam.flashmemorizer.controller;

import com.uniteam.flashmemorizer.dto.UserDTO;
import com.uniteam.flashmemorizer.exception.PasswordMismatchException;
import com.uniteam.flashmemorizer.exception.UserNotFoundException;
import com.uniteam.flashmemorizer.form.ChangePassForm;
import com.uniteam.flashmemorizer.service.user.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    private final Logger log = LogManager.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @GetMapping
    public List<UserDTO> getUsers (){
        return userService.getUsers();
    }

    @GetMapping("/edit")
    public String getDetails(Model m) {
        Long id = userService.getCurrentUserId();
        UserDTO userHolder = userService.getById(id);
        ChangePassForm passForm = ChangePassForm.builder().userId(id).build();
        log.info("Users retrieved successfully for userId: {}", id);
        m.addAttribute("passForm", passForm);
        m.addAttribute("user", userHolder);
        return "user-profile";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("user") UserDTO user, RedirectAttributes ra) {
        try {
            Long userID = userService.getCurrentUserId();
            user.setId(userID);
            userService.updateNotPassword(user);
            log.info("User with userId: {} updated successfully!", user.getId());
            ra.addFlashAttribute("successMsg", "User updated successfully!");
        } catch (UserNotFoundException e) {
            log.error("Error updating user with userId: {}: {}", user.getId(), e.getMessage());
            ra.addFlashAttribute("errorMsg", e.getMessage());
        }
        return "redirect:/user/edit";
    }

    @PostMapping("/delete")
    public String delete() throws UserNotFoundException {
        Long userID = userService.getCurrentUserId();
        userService.delete(userID);
        return "redirect:/home";
    }

    @PostMapping("/change-password")
    public String changePassword(ChangePassForm passForm, RedirectAttributes ra) {
        try {
            Long userID = userService.getCurrentUserId();
            passForm.setUserId(userID);
            userService.changePassword(passForm);
            log.info("Password changed for user with ID: {}", passForm.getUserId());
            ra.addFlashAttribute("successMsg", "Password updated successfully!");
        } catch (PasswordMismatchException e) {
            log.error("Error while changing password with userId: {}", passForm.getUserId() ,e);
            ra.addFlashAttribute("errorMsg", "Password and confirmation do not match");
        } catch (UserNotFoundException e) {
            log.error(e.getMessage());
            return "redirect:/login";
        }
        return "redirect:/user/edit";
    }
}