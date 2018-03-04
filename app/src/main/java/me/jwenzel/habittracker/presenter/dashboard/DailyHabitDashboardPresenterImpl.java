package me.jwenzel.habittracker.presenter.dashboard;

import me.jwenzel.habittracker.presenter.BasePresenterImpl;
import me.jwenzel.habittracker.view.dashboard.DailyHabitDashboardView;

public class DailyHabitDashboardPresenterImpl extends BasePresenterImpl<DailyHabitDashboardView> implements DailyHabitDashboardPresenter {
    /**
     * Creates the BasePresenter and sets the view for the rest of the presenter classes
     * to access
     *
     * @param view
     */
    public DailyHabitDashboardPresenterImpl(DailyHabitDashboardView view) {
        super(view);
    }
}
