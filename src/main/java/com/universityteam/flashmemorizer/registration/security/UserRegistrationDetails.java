package com.universityteam.flashmemorizer.registration.security;

import com.universityteam.flashmemorizer.entity.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
public class UserRegistrationDetails implements UserDetails {

    private String username;
    private String password;
    private boolean isEnabled;
    private List<GrantedAuthority> authorities;

    public UserRegistrationDetails(User user){
        this.username = user.getUsername();
        this.password = user.getPass();
//        this.isEnabled = user.isEnable();
//        this.authorities = Arrays.stream(user.getRole()
//                .split(","))
//                .map(SimpleGrantedAuthority::new)
//                .collect(Collectors.toList());
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }
}
