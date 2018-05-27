package me.jwenzel.habittracker.broadcast_receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import me.jwenzel.habittracker.services.ReminderNotificationService;

public class NotificationReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (!intent.hasExtra(ReminderNotificationService.HABIT_KEY)) {
            Log.d("NotificationReceiver", "onReceive: could not find extra for habit key");
            return;
        }

        int id = intent.getIntExtra(ReminderNotificationService.HABIT_KEY, -1);
        Intent notificationIntent = new Intent(context, ReminderNotificationService.class);
        notificationIntent.putExtra(ReminderNotificationService.HABIT_KEY, id);
        context.startService(notificationIntent);
    }
}
