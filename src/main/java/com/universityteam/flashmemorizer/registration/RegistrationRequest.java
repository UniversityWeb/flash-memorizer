package com.universityteam.flashmemorizer.registration;

public record RegistrationRequest (
    String username,
    String pass,
    String fullName,
    String email,
    String role) {
}
