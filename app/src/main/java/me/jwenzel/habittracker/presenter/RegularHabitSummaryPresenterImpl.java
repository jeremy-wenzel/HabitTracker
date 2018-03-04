package me.jwenzel.habittracker.presenter;

import me.jwenzel.habittracker.view.RegularHabitSummaryView;

public class RegularHabitSummaryPresenterImpl extends BasePresenterImpl<RegularHabitSummaryView> implements RegularHabitSummaryPresenter {
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