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

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.jwenzel.habittracker.database.DatabaseManager;
import me.jwenzel.habittracker.R;
import me.jwenzel.habittracker.adapters.RegularHabitAdapter;
import me.jwenzel.habittracker.business_objects.RegularHabit;
import me.jwenzel.habittracker.dashboard.presenters.RegularHabitDashboardPresenter;
import me.jwenzel.habittracker.dashboard.presenters.RegularHabitDashboardPresenterImpl;
import me.jwenzel.habittracker.BaseMvpFragment;
import me.jwenzel.habittracker.summary.views.RegularHabitSummaryMvpFragment;


public class RegularHabitDashboardMvpFragment extends BaseMvpFragment<RegularHabitDashboardView, RegularHabitDashboardPresenter> implements RegularHabitDashboardView {

    @BindView(R.id.list_view_dashboard) protected ListView mListView;

    private RegularHabitAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_habit_dashboard, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        getPresenter().onStartCalled();
    }

    @Override
    protected RegularHabitDashboardPresenter createPresenter() {
        return new RegularHabitDashboardPresenterImpl(this);
    }

    @Override
    public int getTitle() {
        return R.string.regular_dashboard_title;
    }

    @Override
    public void startSelectHabitTask() {
        new SelectRegularHabitAsyncTask(DatabaseManager.getInstance(getContext())).execute();
    }

    @Override
    public void updateListViewUi(List<RegularHabit> habitList) {
        mAdapter = new RegularHabitAdapter(getContext(), habitList);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(RegularHabitDashboardMvpFragment.this.getContext(), "Regular", Toast.LENGTH_LONG).show();

                RegularHabit habit = (RegularHabit) adapterView.getItemAtPosition(i);
                RegularHabitSummaryMvpFragment fragment = RegularHabitSummaryMvpFragment.getInstance(habit);
                RegularHabitDashboardMvpFragment.this.startNewFragment(fragment, true);
                return true;
            }
        });
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
            getPresenter().onPostExecuteCalled(habits);
        }
    }
}
