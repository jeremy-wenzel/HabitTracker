package me.jwenzel.habittracker.summary.views;

import me.jwenzel.habittracker.BaseView;

public interface DailyHabitSummaryView extends BaseView {

    void displayDaysOfWeekDialog();

    void saveHabit();

    void deleteHabit();
}
