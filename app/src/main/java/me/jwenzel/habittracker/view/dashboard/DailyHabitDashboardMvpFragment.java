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
import me.jwenzel.habittracker.adapters.DailyHabitAdapter;
import me.jwenzel.habittracker.business_objects.DailyHabit;
import me.jwenzel.habittracker.presenter.dashboard.DailyHabitDashboardPresenter;
import me.jwenzel.habittracker.presenter.dashboard.DailyHabitDashboardPresenterImpl;
import me.jwenzel.habittracker.view.BaseMvpFragment;

public class DailyHabitDashboardMvpFragment extends BaseMvpFragment<DailyHabitDashboardView, DailyHabitDashboardPresenter>
        implements DailyHabitDashboardView {

    /**
     * Creates the presenter object and returns it. This method used when the
     * MvpFragment has just started and allows us to set the presenter
     *
     * @return A new instance of the presenter that we will be using
     */
    @Override
    protected DailyHabitDashboardPresenter createPresenter() {
        return new DailyHabitDashboardPresenterImpl(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_habit_dashboard, container, false);

        ArrayList<DailyHabit> dailyHabits = new ArrayList<>();
        dailyHabits.add(DailyHabit.testDailyHabitData());

        DailyHabitAdapter adapter = new DailyHabitAdapter(getContext(), dailyHabits);
        ListView listView = view.findViewById(R.id.list_view_dashboard);
        listView.setAdapter(adapter);

        return view;
    }

    @Override
    public int getTitle() {
        return R.string.daily_habit_title;
    }
}
