package me.jwenzel.habittracker.business_objects;

import java.util.List;

import me.jwenzel.habittracker.utility_objects.DayOfWeekEnum;

/**
 * Created by Jeremy on 2/19/2018.
 */

public abstract class BaseHabit {

    protected String mName;
    protected String mDesc;
    protected boolean mUsingReminders;
    protected List<DayOfWeekEnum> mReminderDays;
    protected DifficultyEnum mDifficulty;
    // TODO: There has to be a better way of doing time
    protected int mReminderHour;
    protected int mReminderMinute;

    BaseHabit(String name, String desc, boolean isUsingReminders, List<DayOfWeekEnum> reminderDays,
                     int reminderHour, int reminderMinute, DifficultyEnum difficulty) {
        this.mName = name;
        this.mDesc = desc;
        this.mUsingReminders = isUsingReminders;
        this.mReminderDays = reminderDays;
        this.mReminderHour = reminderHour;
        this.mReminderMinute = reminderHour;
        this.mDifficulty = difficulty;
    }

    /**
     * Gets the name of the Habit
     * @return
     */
    public String getName() {
        return mName;
    }

    /**
     * Gets the description of the habit
     * @return
     */
    public String getDescription() {
        return mDesc;
    }

    /**
     * Is the habit using reminders?
     * @return True if so, false otherwise
     */
    public boolean isUsingReminders() {
        return mUsingReminders;
    }

    /**
     * The days of the week that are used for reminders
     * @return
     */
    public List<DayOfWeekEnum> getReminderDays() {
        return mReminderDays;
    }

    /**
     * The difficulty of the habit
     * @return
     */
    public DifficultyEnum getDifficulty() {
        return mDifficulty;
    }
}
