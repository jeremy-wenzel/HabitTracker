package me.jwenzel.habittracker.dashboard.views;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import me.jwenzel.habittracker.database.DatabaseManager;
import me.jwenzel.habittracker.R;
import me.jwenzel.habittracker.adapters.DailyHabitAdapter;
import me.jwenzel.habittracker.business_objects.DailyHabit;
import me.jwenzel.habittracker.dashboard.presenters.DailyHabitDashboardPresenter;
import me.jwenzel.habittracker.dashboard.presenters.DailyHabitDashboardPresenterImpl;
import me.jwenzel.habittracker.BaseMvpFragment;
import me.jwenzel.habittracker.summary.views.DailyHabitSummaryMvpFragment;

public class DailyHabitDashboardMvpFragment extends BaseMvpFragment<DailyHabitDashboardView, DailyHabitDashboardPresenter>
        implements DailyHabitDashboardView {

    private DailyHabitAdapter mAdapter;
    private ListView mListView;

    /**
     * Creates the presenter object and returns it. This method used when the
     * MvpFragment has just started and allows us to set the presenter
     *
     * @return A new instance of the presenter that we will be using
     */
    @Override
    protected DailyHabitDashboardPresenter createPresenter() {
        return new DailyHabitDashboardPresenterImpl(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_habit_dashboard, container, false);

        ArrayList<DailyHabit> dailyHabits = new ArrayList<>();
        dailyHabits.add(DailyHabit.testDailyHabitData());

        mAdapter = new DailyHabitAdapter(getContext(), dailyHabits);
        mListView = view.findViewById(R.id.list_view_dashboard);
        mListView.setAdapter(mAdapter);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        getPresenter().onStartCalled();
    }

    @Override
    public int getTitle() {
        return R.string.daily_habit_title;
    }

    @Override
    public void startDatabaseTask() {
        new SelectDailyHabitAsyncTask(DatabaseManager.getInstance(getContext())).execute();
    }

    @Override
    public void updateDailyHabitList(List<DailyHabit> dailyHabits) {
        mAdapter = new DailyHabitAdapter(getContext(), dailyHabits);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(DailyHabitDashboardMvpFragment.this.getContext(), "Testy", Toast.LENGTH_LONG).show();
                DailyHabit habit = (DailyHabit) adapterView.getItemAtPosition(i);

                DailyHabitSummaryMvpFragment fragment = DailyHabitSummaryMvpFragment.newInstance(habit);

                // Set the fragment using the manager and commit it
                DailyHabitDashboardMvpFragment.this.startNewFragment(fragment, true);

                return true;
            }
        });
    }

    private class SelectDailyHabitAsyncTask extends AsyncTask<Void, Void, List<DailyHabit>> {
        private DatabaseManager mDatabaseManager;

        SelectDailyHabitAsyncTask(DatabaseManager databaseManager) {
            mDatabaseManager = databaseManager;
        }

        @Override
        protected List<DailyHabit> doInBackground(Void... voids) {
            return mDatabaseManager.getDailyHabits();
        }

        @Override
        protected void onPostExecute(List<DailyHabit> dailyHabits) {
            getPresenter().onPostExecuteCalled(dailyHabits);
        }
    }
}
