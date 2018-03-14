package me.jwenzel.habittracker.business_objects;

/**
 * Created by Jeremy on 2/20/2018.
 */

public enum DifficultyEnum {
    EASY(0),
    NOVICE(1),
    FORMIDABLE(2),
    LEGENDARY(3);

    private final int value;

    DifficultyEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
