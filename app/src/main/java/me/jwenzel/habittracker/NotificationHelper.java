package me.jwenzel.habittracker;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

import java.util.Calendar;

import me.jwenzel.habittracker.broadcast_receivers.NotificationReceiver;
import me.jwenzel.habittracker.business_objects.BaseHabit;

public class NotificationHelper extends ContextWrapper {

    private static final String CHANNEL_ID = "habittracker.jwenzel.me";
    private AlarmManager mAlarmManager;

    public NotificationHelper(Context base) {
        super(base);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, CHANNEL_ID, NotificationManager.IMPORTANCE_DEFAULT);
            // TODO: Add description of channel
            NotificationManager notificationManager = (NotificationManager) getSystemService(
                    NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(mChannel);
        }
        mAlarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
    }

    public NotificationCompat.Builder showHabitNotification(BaseHabit habit) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_add_box_black)
                .setContentTitle("TestTitle")
                .setContentText("TestContent");

        return builder;
    }

    public void notify(int id, NotificationCompat.Builder builder) {
        NotificationManagerCompat manager = NotificationManagerCompat.from(this);
        manager.notify(id, builder.build());
    }

    public void setNotificationReminder(Calendar time, BaseHabit habit) {
        // TODO: Check if there exists an alarm already for this habit, and remove it if necessary
        Intent intent = new Intent(this, NotificationReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, habit.getPrimaryKey(), intent, PendingIntent.FLAG_ONE_SHOT);
        mAlarmManager.set(AlarmManager.RTC_WAKEUP, time.getTimeInMillis(), pendingIntent);
    }
}
