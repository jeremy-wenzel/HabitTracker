package me.jwenzel.habittracker.view.dashboard;

import android.arch.persistence.room.Database;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.jwenzel.habittracker.DatabaseManager;
import me.jwenzel.habittracker.R;
import me.jwenzel.habittracker.adapters.RegularHabitAdapter;
import me.jwenzel.habittracker.business_objects.RegularHabit;
import me.jwenzel.habittracker.presenter.dashboard.RegularHabitDashboardPresenter;
import me.jwenzel.habittracker.presenter.dashboard.RegularHabitDashboardPresenterImpl;
import me.jwenzel.habittracker.view.BaseMvpFragment;


public class RegularHabitDashboardMvpFragment extends BaseMvpFragment<RegularHabitDashboardView, RegularHabitDashboardPresenter> implements RegularHabitDashboardView {

    @BindView(R.id.list_view_dashboard) protected ListView mListView;

    private RegularHabitAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_habit_dashboard, container, false);
        ButterKnife.bind(this, view);

        ArrayList<RegularHabit> regularHabits = new ArrayList<>();
        regularHabits.add(RegularHabit.getRegularHabitTestData());

        mAdapter = new RegularHabitAdapter(getContext(), regularHabits);
        mListView = view.findViewById(R.id.list_view_dashboard);
        mListView.setAdapter(mAdapter);

        new SelectRegularHabitAsyncTask(DatabaseManager.getInstance(getContext())).execute();

        return view;
    }

    @Override
    protected RegularHabitDashboardPresenter createPresenter() {
        return new RegularHabitDashboardPresenterImpl(this);
    }

    @Override
    public int getTitle() {
        return R.string.regular_dashboard_title;
    }

    private class SelectRegularHabitAsyncTask extends AsyncTask<Void, Void, List<RegularHabit>> {

        private DatabaseManager mManager;

        public SelectRegularHabitAsyncTask(DatabaseManager databaseManager) {
            mManager = databaseManager;
        }

        @Override
        protected List<RegularHabit> doInBackground(Void... voids) {
            return mManager.getRegularHabits();
        }

        @Override
        protected void onPostExecute(List<RegularHabit> habits) {
            mAdapter = new RegularHabitAdapter(getContext(), habits);
            mListView.setAdapter(mAdapter);
        }
    }
}
