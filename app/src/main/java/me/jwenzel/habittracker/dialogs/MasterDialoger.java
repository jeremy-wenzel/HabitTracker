package me.jwenzel.habittracker.dialogs;

import android.content.Context;

import com.afollestad.materialdialogs.MaterialDialog;

import java.util.List;

import me.jwenzel.habittracker.R;
import me.jwenzel.habittracker.utilities.DayOfWeekEnum;
import me.jwenzel.habittracker.utilities.DaysOfWeekEnumTypeConverter;

public final class MasterDialoger {

    /**
     * Builds the days of the week dialog to be used throughout the app.
     * @param context
     * @param selectedDays
     * @param callBack
     * @return
     */
    public static MaterialDialog.Builder buildDaysOfTheWeekDialog(Context context, List<DayOfWeekEnum> selectedDays,
                                                                  MaterialDialog.ListCallbackMultiChoice callBack) {
        return new MaterialDialog.Builder(context)
                .title(R.string.select_days)
                .items(R.array.days_of_the_week)
                .itemsCallbackMultiChoice(DaysOfWeekEnumTypeConverter.selectedIndicesFromList(selectedDays), callBack)
                .positiveText(R.string.accept)
                .negativeText(R.string.cancel);
    }

    public static MaterialDialog.Builder buildAddHabitDialog(Context context,
                                                             MaterialDialog.ListCallbackSingleChoice callBack) {
        return new MaterialDialog.Builder(context)
                .title("Select Habit")
                .items(R.array.new_habit_options)
                .itemsCallbackSingleChoice(-1, callBack)
                .positiveText("Accept")
                .negativeText("Cancel");
    }
}
