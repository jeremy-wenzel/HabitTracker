package me.jwenzel.habittracker.presenter.dashboard;

import java.util.List;

import me.jwenzel.habittracker.business_objects.DailyHabit;
import me.jwenzel.habittracker.presenter.BasePresenter;

public interface DailyHabitDashboardPresenter extends BasePresenter {

    void onStartCalled();

    void onPostExecuteCalled(List<DailyHabit> dailyHabits);
}
