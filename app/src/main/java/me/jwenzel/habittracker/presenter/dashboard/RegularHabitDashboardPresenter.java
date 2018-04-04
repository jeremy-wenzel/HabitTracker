package me.jwenzel.habittracker.presenter.dashboard;

import java.util.List;

import me.jwenzel.habittracker.business_objects.RegularHabit;
import me.jwenzel.habittracker.presenter.BasePresenter;


public interface RegularHabitDashboardPresenter extends BasePresenter {

    void onStartCalled();

    void onPostExecuteCalled(List<RegularHabit> habits);
}
