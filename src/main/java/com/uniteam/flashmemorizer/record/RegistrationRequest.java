package com.uniteam.flashmemorizer.record;

public record RegistrationRequest (
    String fullName,
    String email,
    String username,
    String password,
    String passwordConfirm) {
}
