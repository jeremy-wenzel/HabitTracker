package me.jwenzel.habittracker.summary.presenters;

import me.jwenzel.habittracker.BasePresenter;
import me.jwenzel.habittracker.business_objects.BaseHabit;

public interface BaseHabitSummaryPresenter extends BasePresenter{

    void saveButtonClicked();

    void deleteButtonClicked();

    void onHabitLoaded(BaseHabit habit);

    void onStart(int habitId);
}
