package me.jwenzel.habittracker.view.dashboard;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.jwenzel.habittracker.R;
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
        return inflater.inflate(R.layout.fragment_daily_habit_dashboard, container, false);
    }

    @Override
    public String getTitle() {
        return "Daily";
    }
}
