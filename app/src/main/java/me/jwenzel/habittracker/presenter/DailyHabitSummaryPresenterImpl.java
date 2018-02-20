package me.jwenzel.habittracker.presenter;

import me.jwenzel.habittracker.view.DailyHabitSummaryView;

/**
 * Created by Jeremy on 2/19/2018.
 */

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
