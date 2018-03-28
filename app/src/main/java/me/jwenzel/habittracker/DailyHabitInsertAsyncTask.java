package me.jwenzel.habittracker;

import android.os.AsyncTask;

import me.jwenzel.habittracker.business_objects.DailyHabit;


public class DailyHabitInsertAsyncTask extends AsyncTask<DailyHabit, Void, Boolean> {

    private DatabaseManager mDatabaseManager;

    public DailyHabitInsertAsyncTask(DatabaseManager databaseManager) {
        mDatabaseManager = databaseManager;
    }

    @Override
    protected Boolean doInBackground(DailyHabit... dailyHabits) {
        for (int i = 0; i < dailyHabits.length; ++i) {
            mDatabaseManager.insert(dailyHabits[i]);
        }

        return true;
    }
}
