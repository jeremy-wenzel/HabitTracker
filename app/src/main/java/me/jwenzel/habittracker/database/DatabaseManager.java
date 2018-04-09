package me.jwenzel.habittracker.database;

import android.arch.persistence.room.Room;
import android.content.Context;

import java.util.List;

import me.jwenzel.habittracker.business_objects.BaseHabit;
import me.jwenzel.habittracker.business_objects.DailyHabit;
import me.jwenzel.habittracker.business_objects.RegularHabit;

public class DatabaseManager {

    private final String DATABASE_NAME = "habit.db";

    private static DatabaseManager mInstance;
    private HabitDatabase mDb;

    private DatabaseManager(Context applicationContext) {
        mDb = Room.databaseBuilder(applicationContext, HabitDatabase.class, DATABASE_NAME).build();
    }

    public static DatabaseManager getInstance(Context applicationContext) {
        if (mInstance == null) {
            mInstance = new DatabaseManager(applicationContext);
        }

        return mInstance;
    }

    public void insert(DailyHabit dailyHabit) {
        mDb.habitDao().insertAll(dailyHabit);
    }

    public void insert(RegularHabit regularHabit) {
        mDb.habitDao().insertAll(regularHabit);
    }

    public List<DailyHabit> getDailyHabits() {
        return mDb.habitDao().getAllDailyHabits();
    }

    public List<RegularHabit> getRegularHabits() {
        return mDb.habitDao().getAllRegularHabits();
    }

    public void update(DailyHabit dailyHabit) {
        mDb.habitDao().updateAll(dailyHabit);
    }

    public void update(RegularHabit regularHabit) {
        mDb.habitDao().updateAll(regularHabit);
    }

    public void delete(BaseHabit habit) {
        if (habit instanceof RegularHabit) {
            mDb.habitDao().deleteAll((RegularHabit) habit);
        }
        else if (habit instanceof DailyHabit){
            mDb.habitDao().deleteAll((DailyHabit) habit);
        }
    }
}
