package com.universityteam.flashmemorizer.records;

public record RegistrationRequest (
    String fullname,
    String email,
    String username,
    String password,
    String passwordConfirm) {
}
