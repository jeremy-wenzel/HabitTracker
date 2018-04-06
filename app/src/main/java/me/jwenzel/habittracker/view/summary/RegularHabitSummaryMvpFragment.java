package me.jwenzel.habittracker.view.summary;

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
import me.jwenzel.habittracker.DailyHabitInsertAsyncTask;
import me.jwenzel.habittracker.DatabaseManager;
import me.jwenzel.habittracker.R;
import me.jwenzel.habittracker.business_objects.DifficultyEnum;
import me.jwenzel.habittracker.business_objects.RegularHabit;
import me.jwenzel.habittracker.business_objects.SimpleTime;
import me.jwenzel.habittracker.presenter.summary.RegularHabitSummaryPresenter;
import me.jwenzel.habittracker.presenter.summary.RegularHabitSummaryPresenterImpl;
import me.jwenzel.habittracker.utilities.DayOfWeekEnum;
import me.jwenzel.habittracker.view.BaseMvpFragment;

public class RegularHabitSummaryMvpFragment extends BaseMvpFragment<RegularHabitSummaryView, RegularHabitSummaryPresenter>
        implements RegularHabitSummaryView {

    @BindView(R.id.et_regular_habit_title) protected EditText mHabitTitle;
    @BindView(R.id.et_regular_habit_desc) protected EditText mHabitDesc;
    @BindView(R.id.btn_save_regular_habit) protected Button mSaveButton;

    @Override
    protected RegularHabitSummaryPresenter createPresenter() {
        return new RegularHabitSummaryPresenterImpl(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_regular_habit_summary, container, false);
        ButterKnife.bind(this, view);

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

        new DailyHabitInsertAsyncTask(dbManager).execute(habit);
    }
}
