package me.jwenzel.habittracker.dashboard.presenters;

import java.util.List;

import me.jwenzel.habittracker.business_objects.DailyHabit;
import me.jwenzel.habittracker.BasePresenter;

public interface DailyHabitDashboardPresenter extends BasePresenter {

    void onStartCalled();

    void onPostExecuteCalled(List<DailyHabit> dailyHabits);
}
