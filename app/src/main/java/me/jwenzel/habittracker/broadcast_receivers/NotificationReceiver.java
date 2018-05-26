package me.jwenzel.habittracker.broadcast_receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import me.jwenzel.habittracker.services.ReminderNotificationService;

public class NotificationReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "BroadcastReceiver.onReceive", Toast.LENGTH_LONG).show();

        Intent notificationIntent = new Intent(context, ReminderNotificationService.class);
        context.startService(notificationIntent);
    }
}
