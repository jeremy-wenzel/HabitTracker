package me.jwenzel.habittracker.utilities;

import android.arch.persistence.room.TypeConverter;

import me.jwenzel.habittracker.business_objects.SimpleTime;

public class SimpleTimeConverter {

    @TypeConverter
    public static String toStringTime(SimpleTime time) {
        return time.toString();
    }

    @TypeConverter
    public static SimpleTime fromStringTime(String time) {
        return SimpleTime.fromStringTime(time);
    }
}
