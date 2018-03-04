package me.jwenzel.habittracker.presenter;

import me.jwenzel.habittracker.view.DailyHabitDashboardView;

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
