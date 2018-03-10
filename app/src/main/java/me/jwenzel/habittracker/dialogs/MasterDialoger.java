package me.jwenzel.habittracker.dialogs;

import android.content.Context;
import android.support.annotation.NonNull;

import com.afollestad.materialdialogs.MaterialDialog;

import java.util.List;

import me.jwenzel.habittracker.R;
import me.jwenzel.habittracker.utilities.DayOfWeekEnum;
import me.jwenzel.habittracker.utilities.DaysOfWeekEnumTypeConverter;

public final class MasterDialoger {

    /**
     * Builds the days of the week dialog to be used throughout the app.
     * @param context The current context
     * @param selectedDays The days that are currently selected. Will shown as selected in the dialog
     * @param callBack The call back method that should be called when the user accepts
     * @return
     */
    public static MaterialDialog.Builder buildDaysOfTheWeekDialog(@NonNull Context context, List<DayOfWeekEnum> selectedDays,
                                                                  @NonNull MaterialDialog.ListCallbackMultiChoice callBack) {
        return new MaterialDialog.Builder(context)
                .title(R.string.select_days)
                .items(R.array.days_of_the_week)
                .itemsCallbackMultiChoice(DaysOfWeekEnumTypeConverter.selectedIndicesFromList(selectedDays), callBack)
                .positiveText(R.string.accept)
                .negativeText(R.string.cancel);
    }

    /**
     * Creates an Add Habit dialog that displays the different habit options that can be created
     * @param context The current context
     * @param callBack The call back method that should be called when the user accepts
     * @return
     */
    public static MaterialDialog.Builder buildAddHabitDialog(@NonNull Context context,
                                                             @NonNull MaterialDialog.ListCallbackSingleChoice callBack) {
        return new MaterialDialog.Builder(context)
                .title("Select Habit")
                .items(R.array.new_habit_options)
                .itemsCallbackSingleChoice(0, callBack)
                .positiveText("Accept")
                .negativeText("Cancel");
    }
}
