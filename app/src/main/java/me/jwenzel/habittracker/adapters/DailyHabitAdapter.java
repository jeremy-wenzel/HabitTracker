package me.jwenzel.habittracker.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import me.jwenzel.habittracker.R;
import me.jwenzel.habittracker.business_objects.DailyHabit;

public class DailyHabitAdapter extends ArrayAdapter<DailyHabit> {

    public DailyHabitAdapter(Context context, List<DailyHabit> input) {
        super(context, 0, input);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //String str = getItem(position);
        if (convertView == null) {
            Log.d("TestTag", "The convert view is null");
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_daily_habit, parent, false);
        }

//        TextView tvHabitName = (TextView) convertView.findViewById(R.id.daily_title);
//        tvHabitName.setText(str);
        return convertView;
    }
}
