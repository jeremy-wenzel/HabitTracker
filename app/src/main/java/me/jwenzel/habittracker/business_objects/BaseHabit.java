package me.jwenzel.habittracker.business_objects;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import java.util.List;

import me.jwenzel.habittracker.utilities.DayOfWeekEnum;
import me.jwenzel.habittracker.utilities.DaysOfWeekEnumTypeConverter;
import me.jwenzel.habittracker.utilities.DifficultyConverter;

public abstract class BaseHabit {

    @PrimaryKey(autoGenerate = true)
    private int primaryKey;

    @ColumnInfo(name = "name")
    protected String mName;

    @ColumnInfo(name = "desc")
    protected String mDescription;

    @ColumnInfo(name = "is_using_reminders")
    protected boolean mIsUsingReminders;

    @ColumnInfo(name = "reminder_days")
    @TypeConverters({DaysOfWeekEnumTypeConverter.class})
    protected List<DayOfWeekEnum> mReminderDays;

    @ColumnInfo(name = "difficulty")
    @TypeConverters(DifficultyConverter.class)
    protected DifficultyEnum mDifficulty;




    BaseHabit(String name, String description, boolean isUsingReminders, List<DayOfWeekEnum> reminderDays,
                     DifficultyEnum difficulty) {
        this.mName = name;
        this.mDescription = description;
        this.mIsUsingReminders = isUsingReminders;
        this.mReminderDays = reminderDays;
        this.mDifficulty = difficulty;
    }

    public int getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(int primaryKey) {
        this.primaryKey = primaryKey;
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
        return mDescription;
    }

    /**
     * Is the habit using reminders?
     * @return True if so, false otherwise
     */
    public boolean isUsingReminders() {
        return mIsUsingReminders;
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
