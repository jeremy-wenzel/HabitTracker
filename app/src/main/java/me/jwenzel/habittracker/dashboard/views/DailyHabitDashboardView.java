package me.jwenzel.habittracker.dashboard.views;

import java.util.List;

import me.jwenzel.habittracker.business_objects.DailyHabit;
import me.jwenzel.habittracker.BaseView;

public interface DailyHabitDashboardView extends BaseView {

    void startDatabaseTask();

    void updateDailyHabitList(List<DailyHabit> dailyHabits);
}
