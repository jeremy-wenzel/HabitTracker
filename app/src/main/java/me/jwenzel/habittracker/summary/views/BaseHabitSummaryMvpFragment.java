package me.jwenzel.habittracker.summary.views;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import me.jwenzel.habittracker.BaseMvpFragment;
import me.jwenzel.habittracker.R;
import me.jwenzel.habittracker.summary.presenters.BaseHabitSummaryPresenter;

public abstract class BaseHabitSummaryMvpFragment<V extends BaseHabitSummaryView, P extends BaseHabitSummaryPresenter>
        extends BaseMvpFragment<V, P> implements BaseHabitSummaryView {
    private boolean mIsExistingHabit;

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_habit_summary, menu);

        MenuItem delete = menu.findItem(R.id.action_delete);
        delete.setVisible(mIsExistingHabit);

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_delete:
                getPresenter().deleteButtonClicked();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    protected void setIsExistingHabit(boolean isExistingHabit) {
        mIsExistingHabit = isExistingHabit;
    }

    protected boolean isExistingHabit() {
        return mIsExistingHabit;
    }
}
