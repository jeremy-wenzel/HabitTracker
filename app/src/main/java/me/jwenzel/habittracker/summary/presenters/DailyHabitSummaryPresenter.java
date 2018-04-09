package me.jwenzel.habittracker.summary.presenters;

import me.jwenzel.habittracker.BasePresenter;

public interface DailyHabitSummaryPresenter extends BasePresenter {

    void daysActiveClicked();

    void saveButtonClicked();

    void deleteButtonClicked();
}
