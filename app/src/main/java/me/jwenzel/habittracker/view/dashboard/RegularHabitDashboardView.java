package me.jwenzel.habittracker.view.dashboard;

import java.util.List;

import me.jwenzel.habittracker.business_objects.RegularHabit;
import me.jwenzel.habittracker.view.BaseView;


public interface RegularHabitDashboardView extends BaseView {

    void startSelectHabitTask();

    void updateListViewUi(List<RegularHabit> habitList);
}
