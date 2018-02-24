package me.jwenzel.habittracker.utilities;

import android.arch.persistence.room.TypeConverter;

import me.jwenzel.habittracker.business_objects.DifficultyEnum;

/**
 * Created by Jeremy on 2/24/2018.
 */

public final class DifficultyConverter {

    @TypeConverter
    public static int fromEnum(DifficultyEnum difficultyEnum) {
        return difficultyEnum.getValue();
    }

    @TypeConverter
    public static DifficultyEnum toEnum(int difficulty) {
        for (DifficultyEnum difficultyEnum : DifficultyEnum.values()) {
            if (difficulty == difficultyEnum.getValue()) {
                return difficultyEnum;
            }
        }

        // TODO: Fix this
        return DifficultyEnum.LEGENDARY;
    }
}
