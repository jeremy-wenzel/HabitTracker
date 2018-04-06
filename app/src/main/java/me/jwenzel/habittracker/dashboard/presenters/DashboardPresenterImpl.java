package me.jwenzel.habittracker.dashboard.presenters;

import me.jwenzel.habittracker.BasePresenterImpl;
import me.jwenzel.habittracker.dashboard.views.DashboardView;

public class DashboardPresenterImpl extends BasePresenterImpl<DashboardView> implements DashboardPresenter {
    /**
     * Creates the BasePresenter and sets the view for the rest of the presenter classes
     * to access
     *
     * @param view
     */
    public DashboardPresenterImpl(DashboardView view) {
        super(view);
    }
}
