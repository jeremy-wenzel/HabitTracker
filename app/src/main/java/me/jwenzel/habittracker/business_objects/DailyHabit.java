package me.jwenzel.habittracker.business_objects;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.TypeConverters;

import java.util.List;

import me.jwenzel.habittracker.utilities.DayOfWeekEnum;
import me.jwenzel.habittracker.utilities.DaysOfWeekEnumTypeConverter;

/**
 * Created by Jeremy on 2/19/2018.
 */

@Entity
public class DailyHabit extends BaseHabit {

    @ColumnInfo(name = "active_days")
    @TypeConverters(DaysOfWeekEnumTypeConverter.class)
    private List<DayOfWeekEnum> mActiveDays;

    public DailyHabit(String name, String description, boolean isUsingReminders, List<DayOfWeekEnum> reminderDays,
               int reminderHour, int reminderMinute, DifficultyEnum difficulty, List<DayOfWeekEnum> activeDays) {
        super(name, description, isUsingReminders, reminderDays, reminderHour, reminderMinute, difficulty);

        this.mActiveDays = activeDays;
    }


    public List<DayOfWeekEnum> getActiveDays() {
        return mActiveDays;
    }
}
