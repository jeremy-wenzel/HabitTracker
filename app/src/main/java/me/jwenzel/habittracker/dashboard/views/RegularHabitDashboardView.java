package me.jwenzel.habittracker.dashboard.views;

import java.util.List;

import me.jwenzel.habittracker.business_objects.RegularHabit;
import me.jwenzel.habittracker.BaseView;


public interface RegularHabitDashboardView extends BaseView {

    void startSelectHabitTask();

    void updateListViewUi(List<RegularHabit> habitList);
}
