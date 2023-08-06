package com.universityteam.flashmemorizer.login.registration;

public record RegistrationRequest (
    String username,
    String pass,
    String fullName,
    String email,
    String role) {
}
