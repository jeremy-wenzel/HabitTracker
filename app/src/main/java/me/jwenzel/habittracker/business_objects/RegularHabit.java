package me.jwenzel.habittracker.business_objects;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;

import java.util.ArrayList;
import java.util.List;

import me.jwenzel.habittracker.utilities.DayOfWeekEnum;

/**
 * Created by Jeremy on 2/26/2018.
 */

@Entity
public class RegularHabit extends BaseHabit {

    // TODO: Change the times to be a Simple time
    @ColumnInfo(name = "reminder_begin_hour")
    private int mReminderBeginHour;

    @ColumnInfo(name = "reminder_begin_minute")
    private int mReminderBeginMinute;

    @ColumnInfo(name = "reminder_end_hour")
    private int mReminderEndHour;

    @ColumnInfo(name = "reminder_end_minute")
    private int mReminderEndMinute;

    @ColumnInfo(name = "reminder_repeated")
    private int mReminderRepeated;

    public RegularHabit(String name, String description, boolean isUsingReminders, List<DayOfWeekEnum> reminderDays, DifficultyEnum difficulty, int reminderBeginHour, int reminderBeginMinute, int reminderEndHour, int reminderEndMinute, int reminderRepeated) {
        super(name, description, isUsingReminders, reminderDays, difficulty);

        this.mReminderBeginHour = reminderBeginHour;
        this.mReminderBeginMinute = reminderBeginMinute;
        this.mReminderEndHour = reminderEndHour;
        this.mReminderEndMinute = reminderEndMinute;
        this.mReminderRepeated = reminderRepeated;
    }

    public static RegularHabit getRegularHabitTestData() {
        return new RegularHabit("TestRegular", "Reg.Desc", false, new ArrayList<DayOfWeekEnum>(), DifficultyEnum.EASY, 0, 0, 0, 0, 0);
    }

    public int getReminderBeginHour() {
        return mReminderBeginHour;
    }

    public void setReminderBeginHour(int reminderBeginHour) {
        mReminderBeginHour = reminderBeginHour;
    }

    public int getReminderBeginMinute() {
        return mReminderBeginMinute;
    }

    public void setReminderBeginMinute(int reminderBeginMinute) {
        mReminderBeginMinute = reminderBeginMinute;
    }

    public int getReminderEndHour() {
        return mReminderEndHour;
    }

    public void setReminderEndHour(int reminderEndHour) {
        mReminderEndHour = reminderEndHour;
    }

    public int getReminderEndMinute() {
        return mReminderEndMinute;
    }

    public void setReminderEndMinute(int reminderEndMinute) {
        mReminderEndMinute = reminderEndMinute;
    }

    public int getReminderRepeated() {
        return mReminderRepeated;
    }

    public void setReminderRepeated(int reminderRepeated) {
        mReminderRepeated = reminderRepeated;
    }
}
