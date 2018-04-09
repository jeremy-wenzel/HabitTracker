package me.jwenzel.habittracker.database.async_tasks;

import android.os.AsyncTask;

import me.jwenzel.habittracker.business_objects.BaseHabit;
import me.jwenzel.habittracker.business_objects.RegularHabit;
import me.jwenzel.habittracker.database.DatabaseManager;

public class HabitDeleteAsyncTask extends AsyncTask<BaseHabit, Void, Boolean> {

    private DatabaseManager mDatabaseManager;

    public HabitDeleteAsyncTask(DatabaseManager databaseManager) {
        mDatabaseManager = databaseManager;
    }

    @Override
    protected Boolean doInBackground(BaseHabit... baseHabits) {
        for (BaseHabit habit : baseHabits) {
            mDatabaseManager.delete(habit);
        }
        return true;
    }
}
