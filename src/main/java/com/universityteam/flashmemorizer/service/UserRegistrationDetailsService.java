package com.universityteam.flashmemorizer.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface UserRegistrationDetailsService{
    UserDetails loadUserByUsername(String email);
}
