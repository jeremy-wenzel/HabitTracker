package me.jwenzel.habittracker.presenter.dashboard;

import me.jwenzel.habittracker.presenter.BasePresenterImpl;
import me.jwenzel.habittracker.view.dashboard.DashboardView;

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
