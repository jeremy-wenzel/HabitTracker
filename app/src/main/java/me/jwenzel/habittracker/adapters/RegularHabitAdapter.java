package me.jwenzel.habittracker.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

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
        return super.getView(position, convertView, parent);
    }
}
