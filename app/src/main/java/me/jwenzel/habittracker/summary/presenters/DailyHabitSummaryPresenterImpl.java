package me.jwenzel.habittracker.summary.presenters;

import me.jwenzel.habittracker.summary.views.DailyHabitSummaryView;

public class DailyHabitSummaryPresenterImpl extends BaseHabitSummaryPresenterImpl<DailyHabitSummaryView> implements DailyHabitSummaryPresenter {
    /**
     * Creates the BasePresenter and sets the view for the rest of the presenter classes
     * to access
     *
     * @param view
     */
    public DailyHabitSummaryPresenterImpl(DailyHabitSummaryView view) {
        super(view);
    }

    @Override
    public void daysActiveClicked() {
        getView().displayDaysOfWeekDialog();
    }
}
