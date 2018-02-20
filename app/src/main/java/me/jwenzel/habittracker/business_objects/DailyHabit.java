package me.jwenzel.habittracker.business_objects;

import java.util.List;

import me.jwenzel.habittracker.utility_objects.DayOfWeekEnum;

/**
 * Created by Jeremy on 2/19/2018.
 */

public class DailyHabit extends BaseHabit {

    private List<DayOfWeekEnum> mActiveDays;

    DailyHabit(String name, String desc, boolean isUsingReminders, List<DayOfWeekEnum> reminderDays,
               int reminderHour, int reminderMinute, DifficultyEnum difficulty, List<DayOfWeekEnum> activeDays) {
        super(name, desc, isUsingReminders, reminderDays, reminderHour, reminderMinute, difficulty);

        this.mActiveDays = activeDays;
    }


    public List<DayOfWeekEnum> getActiveDays() {
        return mActiveDays;
    }
}
