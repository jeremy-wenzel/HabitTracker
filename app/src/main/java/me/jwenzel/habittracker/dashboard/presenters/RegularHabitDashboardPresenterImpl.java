package me.jwenzel.habittracker.dashboard.presenters;

import java.util.ArrayList;
import java.util.List;

import me.jwenzel.habittracker.business_objects.RegularHabit;
import me.jwenzel.habittracker.BasePresenterImpl;
import me.jwenzel.habittracker.dashboard.views.RegularHabitDashboardView;


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

    @Override
    public void onStartCalled() {
        List<RegularHabit> dummyData = new ArrayList<>();
        dummyData.add(RegularHabit.getRegularHabitTestData());
        getView().updateListViewUi(dummyData);

        getView().startSelectHabitTask();
    }

    @Override
    public void onPostExecuteCalled(List<RegularHabit> habits) {
        getView().updateListViewUi(habits);
    }
}
