package me.jwenzel.habittracker.summary.views;

import me.jwenzel.habittracker.BaseView;
import me.jwenzel.habittracker.business_objects.BaseHabit;

public interface BaseHabitSummaryView extends BaseView {

    void saveHabit();

    void deleteHabit();

    void loadHabit(int habitId);

    void displayHabit(BaseHabit habit);
}
