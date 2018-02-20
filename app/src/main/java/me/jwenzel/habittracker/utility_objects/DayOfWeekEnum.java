package me.jwenzel.habittracker.utility_objects;

/**
 * Created by Jeremy on 2/19/2018.
 */

public enum DayOfWeekEnum {
    MONDAY(0),
    TUESDAY(1),
    WEDNESDAY(2),
    THURSDAY(3),
    FRIDAY(4),
    SATURDAY(5),
    SUNDAY(6);

    private final int value;

    DayOfWeekEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
