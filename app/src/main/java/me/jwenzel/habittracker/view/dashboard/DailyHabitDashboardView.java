package me.jwenzel.habittracker.view.dashboard;

import java.util.List;

import me.jwenzel.habittracker.business_objects.DailyHabit;
import me.jwenzel.habittracker.view.BaseView;

public interface DailyHabitDashboardView extends BaseView {

    void startDatabaseTask();

    void updateDailyHabitList(List<DailyHabit> dailyHabits);
}
