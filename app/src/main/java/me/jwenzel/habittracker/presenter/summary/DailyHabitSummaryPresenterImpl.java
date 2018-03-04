package me.jwenzel.habittracker.presenter.summary;

import me.jwenzel.habittracker.presenter.BasePresenterImpl;
import me.jwenzel.habittracker.view.summary.DailyHabitSummaryView;

public class DailyHabitSummaryPresenterImpl extends BasePresenterImpl<DailyHabitSummaryView> implements DailyHabitSummaryPresenter {
    /**
     * Creates the BasePresenter and sets the view for the rest of the presenter classes
     * to access
     *
     * @param view
     */
    public DailyHabitSummaryPresenterImpl(DailyHabitSummaryView view) {
        super(view);
    }
}
