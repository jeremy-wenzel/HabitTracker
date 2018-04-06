package me.jwenzel.habittracker.dashboard.presenters;

import java.util.List;

import me.jwenzel.habittracker.business_objects.DailyHabit;
import me.jwenzel.habittracker.BasePresenterImpl;
import me.jwenzel.habittracker.dashboard.views.DailyHabitDashboardView;

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

    @Override
    public void onStartCalled() {
        getView().startDatabaseTask();
    }

    @Override
    public void onPostExecuteCalled(List<DailyHabit> dailyHabit) {
        getView().updateDailyHabitList(dailyHabit);
    }
}
