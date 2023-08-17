package com.universityteam.flashmemorizer.handlers;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class CustomLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler
        implements LogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication)
            throws IOException, ServletException {

        clearAuthenticationData();

        String refererUrl = request.getHeader("Referer");
        System.out.println("Logout from: " + refererUrl);

        super.onLogoutSuccess(request, response, authentication);
    }

    private void clearAuthenticationData(){
        Authentication authentication = new UsernamePasswordAuthenticationToken(null, null);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
