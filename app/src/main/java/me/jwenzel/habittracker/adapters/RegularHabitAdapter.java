package me.jwenzel.habittracker.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import me.jwenzel.habittracker.R;
import me.jwenzel.habittracker.business_objects.DailyHabit;
import me.jwenzel.habittracker.business_objects.RegularHabit;

/**
 * Created by Jeremy on 2/26/2018.
 */

public class RegularHabitAdapter extends ArrayAdapter<RegularHabit> {
    public RegularHabitAdapter(@NonNull Context context, List<RegularHabit> habits) {
        super(context, 0, habits);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        RegularHabit str = getItem(position);
        if (convertView == null) {
            Log.d("TestTag", "The convert view is null");
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_regular_habit, parent, false);
        }

        TextView tvHabitName = convertView.findViewById(R.id.tv_daily_habit_item_name);
        tvHabitName.setText(str.getName());
        return convertView;
    }
}
