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
import me.jwenzel.habittracker.business_objects.DailyHabit;
import me.jwenzel.habittracker.services.ReminderNotificationService;

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

    /**
     * Builds the notification using the habit information
     * @param habit The habit to display
     * @return A notification builder that can be used to display to the user
     */
    public NotificationCompat.Builder buildHabitNotification(BaseHabit habit) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_add_box_black)
                .setContentTitle(habit.getName())
                .setContentText(habit.getDescription());

        return builder;
    }

    /**
     * Displays the notification to the user
     * @param id Id of the notification. (probably needs to be unique?)
     * @param builder Notification builder
     */
    public void notify(int id, NotificationCompat.Builder builder) {
        NotificationManagerCompat manager = NotificationManagerCompat.from(this);
        manager.notify(id, builder.build());
    }

    /**
     * Sets a notification reminder in the AlarmManager to notify the user about a habit
     *
     * @param dailyHabit
     */
    public void setNotificationReminder(DailyHabit dailyHabit) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR, dailyHabit.getReminderTime().get12Hour());
        calendar.set(Calendar.MINUTE, dailyHabit.getReminderTime().getMinute());
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.AM_PM, dailyHabit.getReminderTime().isAm() ? Calendar.AM : Calendar.PM);

        Intent intent = new Intent(this, NotificationReceiver.class);
        intent.putExtra(ReminderNotificationService.HABIT_KEY, dailyHabit.getPrimaryKey());
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, dailyHabit.getPrimaryKey(), intent, PendingIntent.FLAG_ONE_SHOT);
        mAlarmManager.cancel(pendingIntent);
        mAlarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
    }
}
