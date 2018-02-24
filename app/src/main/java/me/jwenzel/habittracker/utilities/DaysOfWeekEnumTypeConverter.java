package me.jwenzel.habittracker.utilities;

import android.arch.persistence.room.TypeConverter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jeremy on 2/24/2018.
 */

public final class DaysOfWeekEnumTypeConverter {

    @TypeConverter
    public static List<DayOfWeekEnum> fromInt(int daysOfTheWeek) {
        List<DayOfWeekEnum> dayOfWeekEnumList = new ArrayList<>();
        // Go through each day in the enum
        for (DayOfWeekEnum day: DayOfWeekEnum.values()) {
            // Verify that the day value is in integer
            if ((daysOfTheWeek & (1 << day.getValue())) == 1) {
                dayOfWeekEnumList.add(day);
            }
        }

        return dayOfWeekEnumList;
    }

    @TypeConverter
    public static int fromList(List<DayOfWeekEnum> daysOfTheWeekList) {
        int value = 0;
        // Set the bit value to the day
        for (DayOfWeekEnum day: daysOfTheWeekList) {
            value = value + (1 << day.getValue());
        }
        return value;
    }
}
