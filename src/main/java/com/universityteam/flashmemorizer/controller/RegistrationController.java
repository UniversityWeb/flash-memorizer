package com.universityteam.flashmemorizer.controller;

import com.universityteam.flashmemorizer.dto.UserDTO;
import com.universityteam.flashmemorizer.entity.User;
import com.universityteam.flashmemorizer.records.RegistrationRequest;
import com.universityteam.flashmemorizer.events.RegistrationCompleteEventListener;
import com.universityteam.flashmemorizer.entity.VerificationToken;
import com.universityteam.flashmemorizer.repository.VerificationTokenRepository;
import jakarta.mail.MessagingException;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.*;

import com.universityteam.flashmemorizer.entity.RegistrationCompleteEvent;
import com.universityteam.flashmemorizer.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

import java.io.UnsupportedEncodingException;
import java.util.logging.Logger;

@RestController
@RequiredArgsConstructor
@RequestMapping("/register")
public class RegistrationController {
    private final UserService userService;
    private final ApplicationEventPublisher publisher;
    private final VerificationTokenRepository tokenRepository;
    private final RegistrationCompleteEventListener eventListener;
    private final HttpServletRequest servletRequest;

    @PostMapping
    public String registerUser(@RequestBody RegistrationRequest registrationRequest, final HttpServletRequest request){
        UserDTO user = userService.registerUser(registrationRequest);
        publisher.publishEvent(new RegistrationCompleteEvent(user, applicationUrl(request)));
        return "Success! Please, check yout email for to complete your registration!";
    }

    @GetMapping("/verifyEmail")
    public String VerifyEmail(@RequestParam("token") String token){
        VerificationToken theToken = tokenRepository.findByToken(token);
        if(theToken.getUser().isEnabled()){
            return "This account has already been verified, please, login.";
        }
        String verificationResult = userService.validateToken(token);
        if(verificationResult.equalsIgnoreCase("valid")){
            return "Email verified successfully. Now you can login account!";
        }
        return "Invalid verification link, please, check your email for new verification link. </a>" ;
    }

    @GetMapping("/resend-verification-token")
    public String resendVerificationToken(@RequestParam("token") String oldToken, final HttpServletRequest request)
            throws MessagingException, UnsupportedEncodingException {
        VerificationToken verificationToken = userService.generateNewVerificationCode(oldToken);
        User theUser = verificationToken.getUser();
        resendVerificationTokenEmail(theUser, applicationUrl(request), verificationToken);
        return "A new verification link has been sent to your email, please, check to active your account";
    }

    private void resendVerificationTokenEmail(User theUser, String applicationUrl, VerificationToken verificationToken)
            throws MessagingException, UnsupportedEncodingException {
        String url = applicationUrl + "/register/verifyEmail?token=" + verificationToken;
        eventListener.sendVerificationEmail(url);
        Logger logger = Logger.getLogger(RegistrationController.class.getName());
        logger.info("Click the link to verify your registration: "+ url);
    }

    public String applicationUrl(HttpServletRequest request){
        return "http://" + request.getServerName() + ":"
                + request.getServerPort() + request.getContextPath();
    }
}
