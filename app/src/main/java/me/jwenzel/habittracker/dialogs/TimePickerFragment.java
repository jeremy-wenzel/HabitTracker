package me.jwenzel.habittracker.dialogs;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.app.TimePickerDialog;
import android.text.format.DateFormat;
import android.util.Log;
import android.widget.TimePicker;

import java.util.Calendar;

/**
 * Creates a time Timepicker fragment for users to pick a time.
 * Requires the parent fragment implement TimePickerDialog.OnTimeSetListener
 */
public class TimePickerFragment extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        //Use the current time as the default values for the time picker
        if (!(getParentFragment() instanceof TimePickerDialog.OnTimeSetListener)) {
            Log.d("TimePickerFragment", "Parent Fragment not instance of TimePickerDialog.OnTimeSetListener");
            throw new IllegalArgumentException("Parent Fragment not instance of TimePickerDialog.OnTimeSetListener");
        }

        TimePickerDialog.OnTimeSetListener listener = (TimePickerDialog.OnTimeSetListener) getParentFragment();
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        //Create and return a new instance of TimePickerDialog
        return new TimePickerDialog(getActivity(),listener, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
    }
}
