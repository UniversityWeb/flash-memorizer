package com.universityteam.flashmemorizer.enums;

public enum ERating {
    LOW(1.3),
    BELOW_AVERAGE(2.8),
    AVERAGE(2.0),
    ABOVE_AVERAGE(2.2),
    HIGH(2.5);

    private final double easeFactor;

    ERating(double easeFactor) {
        this.easeFactor = easeFactor;
    }

    public double getEaseFactor() {
        return easeFactor;
    }
}