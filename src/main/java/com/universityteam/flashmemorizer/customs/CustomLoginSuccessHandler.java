package com.universityteam.flashmemorizer.customs;

import com.universityteam.flashmemorizer.entity.UserHolder;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.logging.Logger;

@Service
public class CustomLoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler
        implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication)
            throws IOException, ServletException {

        UserHolder user = (UserHolder) authentication.getPrincipal();
        String username = user.getUsername();

        Logger logger = Logger.getLogger(CustomLoginSuccessHandler.class.getName());
        logger.info("The user " + username + " has logged in.");

        String redirectUrl = "/user/" + user.getUserHolder().getId();
        response.sendRedirect(redirectUrl);
    }


}
