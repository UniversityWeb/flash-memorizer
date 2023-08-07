package com.universityteam.flashmemorizer.service.impl;

import com.universityteam.flashmemorizer.registration.UserInfoDetails;
import com.universityteam.flashmemorizer.repository.UserRepository;
import com.universityteam.flashmemorizer.service.UserRegistrationDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserRegistrationsDetailsServiceImpl implements UserRegistrationDetailsService {

    private final UserRepository userRepos;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepos.findByEmail(email)
                .map(UserInfoDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
