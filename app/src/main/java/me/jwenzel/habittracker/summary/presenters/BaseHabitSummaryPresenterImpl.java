package me.jwenzel.habittracker.summary.presenters;

import me.jwenzel.habittracker.BasePresenterImpl;
import me.jwenzel.habittracker.BasePresenter;
import me.jwenzel.habittracker.business_objects.BaseHabit;
import me.jwenzel.habittracker.summary.views.BaseHabitSummaryView;

public abstract class BaseHabitSummaryPresenterImpl<V extends BaseHabitSummaryView> extends BasePresenterImpl<V> implements BaseHabitSummaryPresenter {
    /**
     * Creates the BasePresenter and sets the view for the rest of the presenter classes
     * to access
     *
     * @param view
     */
    public BaseHabitSummaryPresenterImpl(V view) {
        super(view);
    }


    @Override
    public void saveButtonClicked() {
        getView().saveHabit();
    }

    @Override
    public void deleteButtonClicked() {
        getView().deleteHabit();
    }

    @Override
    public void onHabitLoaded(BaseHabit habit) {
        getView().displayHabit(habit);
    }

    @Override
    public void onStart(int habitId) {
        getView().loadHabit(habitId);
    }
}
