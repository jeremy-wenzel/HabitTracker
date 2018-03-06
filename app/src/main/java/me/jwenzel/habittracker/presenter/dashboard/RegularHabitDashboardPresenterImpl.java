package me.jwenzel.habittracker.presenter.dashboard;

import me.jwenzel.habittracker.presenter.BasePresenterImpl;
import me.jwenzel.habittracker.view.dashboard.RegularHabitDashboardView;


public class RegularHabitDashboardPresenterImpl extends BasePresenterImpl<RegularHabitDashboardView> implements RegularHabitDashboardPresenter {
    /**
     * Creates the BasePresenter and sets the view for the rest of the presenter classes
     * to access
     *
     * @param view
     */
    public RegularHabitDashboardPresenterImpl(RegularHabitDashboardView view) {
        super(view);
    }
}
