package com.universityteam.flashmemorizer.enums;

public enum ERating {
    LOW(1),
    BELOW_AVERAGE(2),
    AVERAGE(3),
    ABOVE_AVERAGE(4),
    HIGH(5);

    private final int value;

    ERating(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
