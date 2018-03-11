package me.jwenzel.habittracker.view.dashboard;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import me.jwenzel.habittracker.R;
import me.jwenzel.habittracker.adapters.RegularHabitAdapter;
import me.jwenzel.habittracker.business_objects.RegularHabit;
import me.jwenzel.habittracker.presenter.dashboard.RegularHabitDashboardPresenter;
import me.jwenzel.habittracker.presenter.dashboard.RegularHabitDashboardPresenterImpl;
import me.jwenzel.habittracker.view.BaseMvpFragment;


public class RegularHabitDashboardMvpFragment extends BaseMvpFragment<RegularHabitDashboardView, RegularHabitDashboardPresenter> implements RegularHabitDashboardView {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_habit_dashboard, container, false);

        ArrayList<RegularHabit> regularHabits = new ArrayList<>();
        regularHabits.add(RegularHabit.getRegularHabitTestData());

        RegularHabitAdapter adapter = new RegularHabitAdapter(getContext(), regularHabits);
        ListView listView = view.findViewById(R.id.list_view_dashboard);
        listView.setAdapter(adapter);

        return view;
    }

    @Override
    protected RegularHabitDashboardPresenter createPresenter() {
        return new RegularHabitDashboardPresenterImpl(this);
    }

    @Override
    public int getTitle() {
        return R.string.regular_dashboard_title;
    }
}
