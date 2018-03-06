package me.jwenzel.habittracker.view.dashboard;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.jwenzel.habittracker.R;
import me.jwenzel.habittracker.presenter.dashboard.RegularHabitDashboardPresenter;
import me.jwenzel.habittracker.presenter.dashboard.RegularHabitDashboardPresenterImpl;
import me.jwenzel.habittracker.view.BaseMvpFragment;


public class RegularHabitDashboardMvpFragment extends BaseMvpFragment<RegularHabitDashboardView, RegularHabitDashboardPresenter> implements RegularHabitDashboardView {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_regular_habit_summary, container, false);
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
