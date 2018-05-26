package me.jwenzel.habittracker.services;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Intent;
import android.support.annotation.Nullable;

import android.support.v4.app.NotificationCompat;
import android.util.Log;

import me.jwenzel.habittracker.business_objects.DailyHabit;
import me.jwenzel.habittracker.database.DatabaseManager;

public class ReminderNotificationService extends IntentService {
    public static final String HABIT_KEY = "id";

    public ReminderNotificationService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if (!intent.hasExtra(HABIT_KEY)) {
            Log.d(this.getClass().getCanonicalName(), "Intent had habit key extra");
            return;
        }
        int id = intent.getIntExtra(HABIT_KEY, -1);
        DatabaseManager manager = DatabaseManager.getInstance(this);
        DailyHabit habit = manager.getDailyHabit(id);
        new NotificationCompat.Builder(this, "" + id);
    }
}
