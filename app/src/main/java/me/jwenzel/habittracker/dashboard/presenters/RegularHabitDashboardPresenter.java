package me.jwenzel.habittracker.dashboard.presenters;

import java.util.List;

import me.jwenzel.habittracker.business_objects.RegularHabit;
import me.jwenzel.habittracker.BasePresenter;


public interface RegularHabitDashboardPresenter extends BasePresenter {

    void onStartCalled();

    void onPostExecuteCalled(List<RegularHabit> habits);
}
