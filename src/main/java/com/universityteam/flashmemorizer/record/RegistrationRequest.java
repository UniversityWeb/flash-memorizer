package com.universityteam.flashmemorizer.record;

public record RegistrationRequest (
    String fullname,
    String email,
    String username,
    String password,
    String passwordConfirm) {
}
