package me.jwenzel.habittracker.business_objects;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.TypeConverters;

import java.util.List;

import me.jwenzel.habittracker.utilities.DayOfWeekEnum;
import me.jwenzel.habittracker.utilities.DaysOfWeekEnumTypeConverter;

@Entity
public class DailyHabit extends BaseHabit {

    @ColumnInfo(name = "active_days")
    @TypeConverters(DaysOfWeekEnumTypeConverter.class)
    private List<DayOfWeekEnum> mActiveDays;

    // TODO: There has to be a better way of doing time

    @ColumnInfo(name = "reminder_hour")
    private int mReminderHour;

    @ColumnInfo(name = "reminder_minute")
    private int mReminderMinute;

    public DailyHabit(String name, String description, boolean isUsingReminders, List<DayOfWeekEnum> reminderDays,
               int reminderHour, int reminderMinute, DifficultyEnum difficulty, List<DayOfWeekEnum> activeDays) {
        super(name, description, isUsingReminders, reminderDays, difficulty);

        this.mActiveDays = activeDays;
        this.mReminderHour = reminderHour;
        this.mReminderMinute = reminderMinute;
    }


    public List<DayOfWeekEnum> getActiveDays() {
        return mActiveDays;
    }

    public int getReminderHour() {
        return mReminderHour;
    }

    public int getReminderMinute() {
        return mReminderMinute;
    }
}
