package me.jwenzel.habittracker.business_objects;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.TypeConverters;

import java.util.ArrayList;
import java.util.List;

import me.jwenzel.habittracker.utilities.DayOfWeekEnum;
import me.jwenzel.habittracker.utilities.SimpleTimeConverter;

/**
 * Created by Jeremy on 2/26/2018.
 */

@Entity
public class RegularHabit extends BaseHabit {

    @ColumnInfo(name = "reminder_repeated")
    private int mReminderRepeated;

    @ColumnInfo(name = "reminder_begin")
    @TypeConverters(SimpleTimeConverter.class)
    private SimpleTime mReminderBeginTime;

    @ColumnInfo(name = "reminder_end")
    @TypeConverters(SimpleTimeConverter.class)
    private SimpleTime mReminderEndTime;

    public RegularHabit(String name, String description, boolean isUsingReminders, List<DayOfWeekEnum> reminderDays,
                        DifficultyEnum difficulty, SimpleTime reminderBeginTime, SimpleTime reminderEndTime, int reminderRepeated) {
        super(name, description, isUsingReminders, reminderDays, difficulty);

        this.mReminderBeginTime = reminderBeginTime;
        this.mReminderEndTime = reminderEndTime;
        this.mReminderRepeated = reminderRepeated;
    }

    public static RegularHabit getRegularHabitTestData() {
        return new RegularHabit("TestRegular", "Reg.Desc", false, new ArrayList<DayOfWeekEnum>(),
                DifficultyEnum.EASY, new SimpleTime(0, 0), new SimpleTime(0,0), 0);
    }

    public SimpleTime getReminderBeginTime() {
        return mReminderBeginTime;
    }

    public void setReminderBeginTime(SimpleTime reminderBeginTime) {
        mReminderBeginTime = reminderBeginTime;
    }

    public SimpleTime getReminderEndTime() {
        return mReminderEndTime;
    }

    public void setReminderEndTime(SimpleTime reminderEndTime) {
        mReminderEndTime = reminderEndTime;
    }


    public int getReminderRepeated() {
        return mReminderRepeated;
    }

    public void setReminderRepeated(int reminderRepeated) {
        mReminderRepeated = reminderRepeated;
    }
}
