package com.universityteam.flashmemorizer.records;

public record RegistrationRequest (
    String username,
    String pass,
    String fullName,
    String email) {
}
