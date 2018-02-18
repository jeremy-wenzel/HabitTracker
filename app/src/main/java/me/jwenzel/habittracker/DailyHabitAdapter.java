package me.jwenzel.habittracker;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Jeremy on 2/18/2018.
 */

public class DailyHabitAdapter extends ArrayAdapter<String> {

    public DailyHabitAdapter(Context context, ArrayList<String> input) {
        super(context, 0, input);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String str = getItem(position);
        if (convertView == null) {
            Log.d("TestTag", "The convert view is null");
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_daily_habit, parent, false);
        }

        TextView tvHabitName = (TextView) convertView.findViewById(R.id.daily_title);
        tvHabitName.setText(str);
        return convertView;
    }
}
