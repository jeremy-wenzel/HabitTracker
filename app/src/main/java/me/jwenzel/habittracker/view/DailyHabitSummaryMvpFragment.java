package me.jwenzel.habittracker.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.jwenzel.habittracker.R;
import me.jwenzel.habittracker.presenter.DailyHabitSummaryPresenter;
import me.jwenzel.habittracker.presenter.DailyHabitSummaryPresenterImpl;

/**
 * Created by Jeremy on 2/19/2018.
 */

public class DailyHabitSummaryMvpFragment extends BaseMvpFragment<DailyHabitSummaryView, DailyHabitSummaryPresenter> implements DailyHabitSummaryView {
    /**
     * Creates the presenter object and returns it. This method used when the
     * MvpFragment has just started and allows us to set the presenter
     *
     * @return A new instance of the presenter that we will be using
     */
    @Override
    protected DailyHabitSummaryPresenter createPresenter() {
        return new DailyHabitSummaryPresenterImpl(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_daily_habit_summary, container, false);
        return view;
    }
}
