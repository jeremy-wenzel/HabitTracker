package me.jwenzel.habittracker.business_objects;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Created by Jeremy on 2/24/2018.
 */

@Dao
public interface HabitDao {
    // Insert Daily Habits
    @Insert
    void insertAll(DailyHabit... dailyHabits);

    // Delete Daily Habits
    @Delete
    void deleteAll(DailyHabit... dailyHabits);

    // Update Daily Habits
    @Update
    void updateAll(DailyHabit... dailyHabits);

    @Query("SELECT * FROM dailyhabit")
    List<DailyHabit> getAllDailyHabits();

    // Insert Regular Habits

    // Delete Regular Habits

    // Update Regular Habits
}
