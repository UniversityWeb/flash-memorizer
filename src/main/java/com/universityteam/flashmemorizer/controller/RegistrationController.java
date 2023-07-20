package com.universityteam.flashmemorizer.controller;

import com.universityteam.flashmemorizer.dto.UserDTO;
import com.universityteam.flashmemorizer.registration.RegistrationRequest;
import com.universityteam.flashmemorizer.registration.envents.listener.RegistrationCompleteEventListener;
import com.universityteam.flashmemorizer.registration.token.VerificationToken;
import com.universityteam.flashmemorizer.registration.token.VerificationTokenRepository;
import jakarta.mail.MessagingException;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.*;

import com.universityteam.flashmemorizer.registration.envents.RegistrationCompleteEvent;
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

        String url = applicationUrl(servletRequest) + "/register/resend-verification-token?token=" + token;

        VerificationToken theToken = tokenRepository.findByToken(token);
        if(theToken.getUser().isEnable()){
            return "This account has already been verified, please, login!";
        }
        String verificationResult = userService.validateToken(token);
        if(verificationResult.equalsIgnoreCase("valid")){
            return "Email verifid successfully. Now you can login account <3";
        }
        return "Invalid verification token, <a href=" + url +"\"> Get a new verification link. </a>" ;
    }

    public String resendVerificationToken(@RequestParam("token") String oldToken,
                                          final HttpServletRequest request) throws MessagingException, UnsupportedEncodingException {
        VerificationToken verificationToken = userService.generateNewVerificationCode(oldToken);
        UserDTO theUser = verificationToken.getUser();
        resendVerificationTokenEmail(theUser, applicationUrl(request), verificationToken);
        return "A new verification link has been sent to your email, please, check to active your account";
    }

    @GetMapping("/resend-verification-token")
    private void resendVerificationTokenEmail(UserDTO theUser, String applicationUrl, VerificationToken verificationToken) throws MessagingException, UnsupportedEncodingException {
        String url = applicationUrl + "/register/verifyEmail?token=" + verificationToken;
        eventListener.sendVerificationEmail(url);
        Logger logger = Logger.getLogger(RegistrationController.class.getName());
        logger.info("Click the link to verify your registration: "+ url);
    }

    public String applicationUrl(HttpServletRequest request){
        return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }
}
