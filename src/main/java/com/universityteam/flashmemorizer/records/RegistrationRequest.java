package com.universityteam.flashmemorizer.records;

public record RegistrationRequest (
    String fullname,
    String username,
    String email,
    String password) {
}
