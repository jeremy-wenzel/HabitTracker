package me.jwenzel.habittracker.summary.presenters;

import me.jwenzel.habittracker.summary.views.RegularHabitSummaryView;

public class RegularHabitSummaryPresenterImpl extends BaseHabitSummaryPresenterImpl<RegularHabitSummaryView> implements RegularHabitSummaryPresenter {
    /**
     * Creates the BasePresenter and sets the view for the rest of the presenter classes
     * to access
     *
     * @param view
     */
    public RegularHabitSummaryPresenterImpl(RegularHabitSummaryView view) {
        super(view);
    }
}
