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
    private HabitDao mDao;

    private DatabaseManager(Context applicationContext) {
        HabitDatabase mDb = Room.databaseBuilder(applicationContext, HabitDatabase.class, DATABASE_NAME).build();
        mDao = mDb.habitDao();
    }

    public static DatabaseManager getInstance(Context applicationContext) {
        if (mInstance == null) {
            mInstance = new DatabaseManager(applicationContext);
        }

        return mInstance;
    }

    public void insert(DailyHabit dailyHabit) {
        mDao.insertAll(dailyHabit);
    }

    public void insert(RegularHabit regularHabit) {
        mDao.insertAll(regularHabit);
    }

    public List<DailyHabit> getDailyHabits() {
        return mDao.getAllDailyHabits();
    }

    public DailyHabit getDailyHabit(int id) {
        return mDao.getDailyHabit(id);
    }

    public List<RegularHabit> getRegularHabits() {
        return mDao.getAllRegularHabits();
    }

    public RegularHabit getRegularHabit(int id) {
        return mDao.getRegularHabit(id);
    }

    public void update(DailyHabit dailyHabit) {
        mDao.updateAll(dailyHabit);
    }

    public void update(RegularHabit regularHabit) {
        mDao.updateAll(regularHabit);
    }

    public void delete(BaseHabit habit) {
        if (habit instanceof RegularHabit) {
            mDao.deleteAll((RegularHabit) habit);
        }
        else if (habit instanceof DailyHabit){
            mDao.deleteAll((DailyHabit) habit);
        }
    }
}
