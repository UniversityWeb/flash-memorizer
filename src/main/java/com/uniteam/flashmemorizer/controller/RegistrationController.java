package com.uniteam.flashmemorizer.controller;

import com.uniteam.flashmemorizer.dto.UserDTO;
import com.uniteam.flashmemorizer.entity.User;
import com.uniteam.flashmemorizer.record.RegistrationRequest;
import com.uniteam.flashmemorizer.listener.RegistrationCompleteEventListener;
import com.uniteam.flashmemorizer.entity.VerificationToken;

import com.uniteam.flashmemorizer.utility.Utils;
import jakarta.mail.MessagingException;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.uniteam.flashmemorizer.event.RegistrationCompleteEvent;
import com.uniteam.flashmemorizer.service.user.UserService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

import java.io.UnsupportedEncodingException;
import java.util.logging.Logger;

@Controller
@RequiredArgsConstructor
@RequestMapping("/register-process")
public class RegistrationController {

    private final UserService userService;
    private final ApplicationEventPublisher publisher;
    private final RegistrationCompleteEventListener eventListener;

    @PostMapping
    public String registerUser(@org.jetbrains.annotations.NotNull @ModelAttribute("user") RegistrationRequest registrationRequest,
                               final HttpServletRequest request){
        Logger logger = Logger.getLogger(RegistrationController.class.getName());
        logger.info("Registration Request: " + registrationRequest);

        if(userService.isExistsEmail(registrationRequest.email()))
            return "redirect:/home?isExistsEmail";

        if(userService.isExistUsername(registrationRequest.username()))
            return "redirect:/home?isExistsUsername";

        if(userService.passwordNotMatch(registrationRequest.password(),registrationRequest.passwordConfirm()))
            return "redirect:/home?passwordNotMatch";

        UserDTO user = userService.registerUser(registrationRequest);
        publisher.publishEvent(new RegistrationCompleteEvent(user, applicationUrl(request)));
        return "registration-success";
    }

    @GetMapping("/verifyEmail")
    public String VerifyEmail(@RequestParam("token") String token, Model model){
        Logger logger = Logger.getLogger(RegistrationController.class.getName());
        logger.info("Token: " + token);

        model.addAttribute("valid", false);
        String message = userService.validateToken(token);

        if(message.equalsIgnoreCase(Utils.SUCCESS_TOKEN_MSG)) {
            model.addAttribute("valid", true);
        }

        model.addAttribute("message", message);
        return "verify-email";
    }

    @GetMapping("/resend-verification-token")
    public String resendVerificationToken(@RequestParam("token") String oldToken, final HttpServletRequest request)
            throws MessagingException, UnsupportedEncodingException {
        VerificationToken verificationToken = userService.generateNewVerificationCode(oldToken);
        User theUser = verificationToken.getUser();
        resendVerificationTokenEmail(theUser, applicationUrl(request), verificationToken);
        return Utils.RESEND_TOKEN_MSG;
    }

    private void resendVerificationTokenEmail(User theUser, String applicationUrl, VerificationToken verificationToken)
            throws MessagingException, UnsupportedEncodingException {
        String url = applicationUrl + "/register-process/verifyEmail?token=" + verificationToken;
        eventListener.sendVerificationEmail(url);
        Logger logger = Logger.getLogger(RegistrationController.class.getName());
        logger.info("Click the link to verify your registration: "+ url);
    }

    public String applicationUrl(HttpServletRequest request){
        return "http://" + request.getServerName() + ":"
                + request.getServerPort() + request.getContextPath();
    }
}