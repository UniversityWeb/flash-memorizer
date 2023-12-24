package com.uniteam.flashmemorizer.exception;

public class VerificationTokenNotFoundException extends RuntimeException{
    public VerificationTokenNotFoundException(String message) {
        super(message);
    }
}
