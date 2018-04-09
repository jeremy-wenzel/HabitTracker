package me.jwenzel.habittracker.database.async_tasks;

import android.os.AsyncTask;

import me.jwenzel.habittracker.business_objects.BaseHabit;
import me.jwenzel.habittracker.business_objects.DailyHabit;
import me.jwenzel.habittracker.business_objects.RegularHabit;
import me.jwenzel.habittracker.database.DatabaseManager;

public class HabitUpdateAsyncTask extends AsyncTask<BaseHabit, Void, Boolean> {
    private DatabaseManager mDatabaseManager;

    public HabitUpdateAsyncTask(DatabaseManager databaseManager) {
        mDatabaseManager = databaseManager;
    }

    @Override
    protected Boolean doInBackground(BaseHabit... habits) {
        for (int i = 0; i < habits.length; ++i) {
            BaseHabit habit = habits[i];
            if (habit instanceof RegularHabit) {
                mDatabaseManager.update((RegularHabit) habit);
            }
            else if (habit instanceof DailyHabit) {
                mDatabaseManager.update((DailyHabit) habit);
            }
        }

        return true;
    }
}
