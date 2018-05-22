package me.jwenzel.habittracker;

import android.content.Context;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

import me.jwenzel.habittracker.business_objects.BaseHabit;

public class NotificationBuilder {

    public static final String CHANNEL_ID = "habittracker.jwenzel.me";

    public static void showHabitNotification(Context context, BaseHabit habit) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_add_box_black)
                .setContentTitle("TestTitle")
                .setContentText("TestContent");

        NotificationManagerCompat manager = NotificationManagerCompat.from(context);
        manager.notify(1, builder.build());
    }
}
