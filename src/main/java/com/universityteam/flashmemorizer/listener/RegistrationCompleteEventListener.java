package com.universityteam.flashmemorizer.listener;

import java.io.UnsupportedEncodingException;
import java.util.UUID;

import com.universityteam.flashmemorizer.converter.UserConverter;
import com.universityteam.flashmemorizer.dto.UserDTO;
import com.universityteam.flashmemorizer.event.RegistrationCompleteEvent;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.universityteam.flashmemorizer.entity.User;
import com.universityteam.flashmemorizer.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class RegistrationCompleteEventListener 
    implements ApplicationListener<RegistrationCompleteEvent>{
   
    private final UserService userService;
    private final JavaMailSender mailSender;
    private UserDTO theUser;

    @Override
    public void onApplicationEvent(RegistrationCompleteEvent event){
        this.theUser = event.getUser();
        String verificationToken = UUID.randomUUID().toString();
        userService.saveUserVerifycationToken(this.theUser, verificationToken);
        String url = event.getApplicationUrl() + "/register-process/verifyEmail?token=" + verificationToken;
        try {
            sendVerificationEmail(url);
        } catch (MessagingException | UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        log.info("Click this link to verify your registration: {}", url);
    }

    public void sendVerificationEmail(String url) throws MessagingException, UnsupportedEncodingException {
        String subject = "Email Verification";
        String senderName = "User Registration Portal Service";
        String mailContent = "<p> Hi, " + this.theUser.getFullName() + "</p>"
                + "<p> Thank you for registering with us, "
                + "Please, follow the link beloew to complete your registration. </p>"
                + "<a href =\"" + url + "\"> Verify your email to active your account. </a>"
                + "<p> Thank your <br> Users Registration Portal Service";
        MimeMessage message = mailSender.createMimeMessage();
        var messageHelper = new MimeMessageHelper(message, "UTF-8");

        //Fill In Mail Source
        messageHelper.setFrom("vanantran009@gmail.com", senderName);
        messageHelper.setTo(this.theUser.getEmail());
        messageHelper.setSubject(subject);
        messageHelper.setText(mailContent, true);
        mailSender.send(message);
    }
}
