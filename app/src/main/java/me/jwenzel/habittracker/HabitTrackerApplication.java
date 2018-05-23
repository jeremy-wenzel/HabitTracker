package me.jwenzel.habittracker;

import android.app.Application;

import me.jwenzel.habittracker.database.DatabaseManager;

public class HabitTrackerApplication extends Application {

    private NotificationHelper mNotificationHelper;

    @Override
    public void onCreate() {
        super.onCreate();

        // Initialize Notification channel
        mNotificationHelper = new NotificationHelper(this);
    }

    /**
     * Gets the database manager instance
     * @return
     */
    public DatabaseManager getDatabaseManager() {
        return DatabaseManager.getInstance(this);
    }

    public NotificationHelper getNotificationHelper() {
        return mNotificationHelper;
    }
}
