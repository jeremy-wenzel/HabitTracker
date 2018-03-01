package me.jwenzel.habittracker.business_objects;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.TypeConverters;

import java.util.List;

import me.jwenzel.habittracker.utilities.DayOfWeekEnum;
import me.jwenzel.habittracker.utilities.DaysOfWeekEnumTypeConverter;
import me.jwenzel.habittracker.utilities.SimpleTimeConverter;

@Entity
public class DailyHabit extends BaseHabit {

    @ColumnInfo(name = "active_days")
    @TypeConverters(DaysOfWeekEnumTypeConverter.class)
    private List<DayOfWeekEnum> mActiveDays;

    // TODO: There has to be a better way of doing time

    @ColumnInfo(name = "reminder_time")
    @TypeConverters(SimpleTimeConverter.class)
    private SimpleTime mReminderTime;

    public DailyHabit(String name, String description, boolean isUsingReminders, List<DayOfWeekEnum> reminderDays,
               SimpleTime reminderTime, DifficultyEnum difficulty, List<DayOfWeekEnum> activeDays) {
        super(name, description, isUsingReminders, reminderDays, difficulty);

        this.mActiveDays = activeDays;
        this.mReminderTime = reminderTime;
    }


    public List<DayOfWeekEnum> getActiveDays() {
        return mActiveDays;
    }

    public SimpleTime getReminderTime() {
        return mReminderTime;
    }
}
