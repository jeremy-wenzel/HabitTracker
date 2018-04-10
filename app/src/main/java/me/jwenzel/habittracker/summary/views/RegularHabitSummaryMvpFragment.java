package me.jwenzel.habittracker.summary.views;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.jwenzel.habittracker.database.async_tasks.HabitInsertAsyncTask;
import me.jwenzel.habittracker.database.DatabaseManager;
import me.jwenzel.habittracker.R;
import me.jwenzel.habittracker.business_objects.DifficultyEnum;
import me.jwenzel.habittracker.business_objects.RegularHabit;
import me.jwenzel.habittracker.business_objects.SimpleTime;
import me.jwenzel.habittracker.database.async_tasks.HabitDeleteAsyncTask;
import me.jwenzel.habittracker.database.async_tasks.HabitUpdateAsyncTask;
import me.jwenzel.habittracker.summary.presenters.RegularHabitSummaryPresenter;
import me.jwenzel.habittracker.summary.presenters.RegularHabitSummaryPresenterImpl;
import me.jwenzel.habittracker.utilities.DayOfWeekEnum;

public class RegularHabitSummaryMvpFragment extends BaseHabitSummaryMvpFragment<RegularHabitSummaryView, RegularHabitSummaryPresenter>
        implements RegularHabitSummaryView {
    private static final String PRIMARY_KEY = "primary_key";
    private static final String NAME_KEY = "name";
    private static final String DESC_KEY = "desc";

    @BindView(R.id.et_regular_habit_title) protected EditText mHabitTitle;
    @BindView(R.id.et_regular_habit_desc) protected EditText mHabitDesc;
    @BindView(R.id.btn_save_regular_habit) protected Button mSaveButton;

    private int mPrimaryKey;

    public static RegularHabitSummaryMvpFragment getInstance(RegularHabit habit) {
        Bundle args = new Bundle();

        args.putInt(PRIMARY_KEY, habit.getPrimaryKey());
        args.putString(NAME_KEY, habit.getName());
        args.putString(DESC_KEY, habit.getDescription());

        RegularHabitSummaryMvpFragment fragment = new RegularHabitSummaryMvpFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    protected RegularHabitSummaryPresenter createPresenter() {
        return new RegularHabitSummaryPresenterImpl(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.fragment_regular_habit_summary, container, false);
        ButterKnife.bind(this, view);

        Bundle args = getArguments();
        if (args != null) {
            mHabitTitle.setText(args.getString(NAME_KEY));
            mHabitDesc.setText(args.getString(DESC_KEY));

            mPrimaryKey = args.getInt(PRIMARY_KEY);

            setIsExistingHabit(true);
        }

        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getPresenter().saveButtonClicked();
            }
        });
        return view;
    }

    @Override
    public void saveHabit() {
        String name = mHabitTitle.getText().toString();
        String desc = mHabitDesc.getText().toString();

        DifficultyEnum difficultyEnum = DifficultyEnum.EASY;
        ArrayList<DayOfWeekEnum> dayOfWeekEnums = new ArrayList<>();
        dayOfWeekEnums.add(DayOfWeekEnum.WEDNESDAY);

        RegularHabit habit = new RegularHabit(name, desc, false, dayOfWeekEnums, difficultyEnum, new SimpleTime(0, 0), new SimpleTime(0,0), 0);
        DatabaseManager dbManager = DatabaseManager.getInstance(RegularHabitSummaryMvpFragment.this.getContext());

        if (isExistingHabit()) {
            habit.setPrimaryKey(mPrimaryKey);
            new HabitUpdateAsyncTask(dbManager).execute(habit);
        }
        else {
            new HabitInsertAsyncTask(dbManager).execute(habit);
        }
        finishFragment();
    }

    @Override
    public void deleteHabit() {
        String name = mHabitTitle.getText().toString();
        String desc = mHabitDesc.getText().toString();

        DifficultyEnum difficultyEnum = DifficultyEnum.EASY;
        ArrayList<DayOfWeekEnum> dayOfWeekEnums = new ArrayList<>();
        dayOfWeekEnums.add(DayOfWeekEnum.WEDNESDAY);

        RegularHabit habit = new RegularHabit(name, desc, false, dayOfWeekEnums, difficultyEnum, new SimpleTime(0, 0), new SimpleTime(0,0), 0);
        habit.setPrimaryKey(mPrimaryKey);
        DatabaseManager dbManager = DatabaseManager.getInstance(RegularHabitSummaryMvpFragment.this.getContext());

        new HabitDeleteAsyncTask(dbManager).execute(habit);
        finishFragment();
    }
}
