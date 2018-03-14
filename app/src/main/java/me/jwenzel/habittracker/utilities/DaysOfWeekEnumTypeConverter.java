package me.jwenzel.habittracker.utilities;

import android.arch.persistence.room.TypeConverter;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    public static Integer[] selectedIndicesFromList(List<DayOfWeekEnum> selectedDays) {
        Set<Integer> selectedIndices = new HashSet<>();

        for (DayOfWeekEnum day : selectedDays) {
            int dayValue = day.getValue();
            if (!selectedIndices.contains(dayValue)) {
                selectedIndices.add(dayValue);
            }
        }

        Integer[] returnArr = new Integer[selectedIndices.size()];
        returnArr = selectedIndices.toArray(returnArr);

        return returnArr;
    }

    public static List<DayOfWeekEnum> listFromSelectedIndices(Integer[] which) {
        List<DayOfWeekEnum> selectedDays = new ArrayList<>();
        DayOfWeekEnum[] values = DayOfWeekEnum.values();

        // TODO: This is really ugly but Java doesn't have LINQ
        for (Integer day : which) {
            for (DayOfWeekEnum dayValue : values) {
                if (dayValue.getValue() == day) {
                    selectedDays.add(dayValue);
                    break;
                }
            }
        }

        return selectedDays;
    }
}
