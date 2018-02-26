package me.jwenzel.habittracker.business_objects;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 * Created by Jeremy on 2/24/2018.
 */

@Database(entities = {DailyHabit.class, RegularHabit.class}, version = 1)
public abstract class HabitDatabase extends RoomDatabase {
    public abstract HabitDao habitDao();
}
