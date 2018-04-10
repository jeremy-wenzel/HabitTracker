package me.jwenzel.habittracker.summary.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.jwenzel.habittracker.database.async_tasks.DailyHabitInsertAsyncTask;
import me.jwenzel.habittracker.database.DatabaseManager;
import me.jwenzel.habittracker.R;
import me.jwenzel.habittracker.business_objects.DailyHabit;
import me.jwenzel.habittracker.business_objects.DifficultyEnum;
import me.jwenzel.habittracker.business_objects.SimpleTime;
import me.jwenzel.habittracker.database.async_tasks.HabitDeleteAsyncTask;
import me.jwenzel.habittracker.database.async_tasks.HabitUpdateAsyncTask;
import me.jwenzel.habittracker.dialogs.MasterDialoger;
import me.jwenzel.habittracker.summary.presenters.DailyHabitSummaryPresenter;
import me.jwenzel.habittracker.summary.presenters.DailyHabitSummaryPresenterImpl;
import me.jwenzel.habittracker.utilities.DayOfWeekEnum;
import me.jwenzel.habittracker.utilities.DaysOfWeekEnumTypeConverter;
import me.jwenzel.habittracker.BaseMvpFragment;

public class DailyHabitSummaryMvpFragment extends BaseHabitSummaryMvpFragment<DailyHabitSummaryView, DailyHabitSummaryPresenter> implements DailyHabitSummaryView {

    private static final String PRIMARY_KEY = "primary_key";
    private static final String NAME_KEY = "name";
    private static final String DESC_KEY = "desc";
    private static final String REMINDER_KEY = "hasReminder";
    private static final String ACTIVE_DAYS_KEY = "active_days";
    private static final String REMINDER_DAYS_KEY = "reminder_days";
    private static final String DIFFICULTY_KEY = "difficulty";

    @BindView(R.id.et_daily_habit_name) protected EditText mNameInput;
    @BindView(R.id.et_daily_habit_desc) protected EditText mDescInput;
    @BindView(R.id.tv_daily_habit_active_desc) protected TextView mDaysActive;
    @BindView(R.id.tv_daily_habit_active_days) protected TextView mDays;
    @BindView(R.id.cb_daily_habit_reminder) protected CheckBox mReminderCheckbox;
    @BindView(R.id.btn_daily_habit_save) protected Button mSaveButton;

    private SimpleTime mReminderTime;
    private ArrayList<DayOfWeekEnum> mActiveDays = new ArrayList<>();
    private DifficultyEnum mDifficulty;

    private int mPrimaryKey;

    private boolean mIsExistingHabit;

    public static DailyHabitSummaryMvpFragment newInstance(DailyHabit habit) {
        DailyHabitSummaryMvpFragment fragment = new DailyHabitSummaryMvpFragment();

        Bundle args = new Bundle();
        args.putInt(PRIMARY_KEY, habit.getPrimaryKey());
        args.putString(NAME_KEY, habit.getName());
        args.putString(DESC_KEY, habit.getDescription());
        args.putBoolean(REMINDER_KEY, habit.isUsingReminders());
        args.putInt(ACTIVE_DAYS_KEY,DaysOfWeekEnumTypeConverter.fromList(habit.getActiveDays()));
        args.putInt(REMINDER_DAYS_KEY, DaysOfWeekEnumTypeConverter.fromList(habit.getActiveDays()));
        args.putInt(DIFFICULTY_KEY, habit.getDifficulty().getValue());

        fragment.setArguments(args);

        return fragment;
    }

    /**
     * Creates the presenter object and returns it. This method used when the
     * MvpFragment has just started and allows us to set the presenter
     *
     * @return A new instance of the presenter that we will be using
     */
    @Override
    protected DailyHabitSummaryPresenter createPresenter() {
        return new DailyHabitSummaryPresenterImpl(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.fragment_daily_habit_summary, container, false);

        ButterKnife.bind(this, view);

        if (getArguments() != null) {
            Bundle args = getArguments();
            mPrimaryKey = args.getInt(PRIMARY_KEY);
            mNameInput.setText(args.getString(NAME_KEY));
            mDescInput.setText(args.getString(DESC_KEY));
            mReminderCheckbox.setChecked(args.getBoolean(REMINDER_KEY));

            mIsExistingHabit = true;
        }

        mDaysActive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPresenter().daysActiveClicked();
            }
        });

        mSaveButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                getPresenter().saveButtonClicked();
            }
        });

        return view;
    }

    @Override
    public void displayDaysOfWeekDialog() {
        MasterDialoger.buildDaysOfTheWeekDialog(this.getContext(), mActiveDays, new MaterialDialog.ListCallbackMultiChoice() {
            @Override
            public boolean onSelection(MaterialDialog dialog, Integer[] which, CharSequence[] text) {

                /*
                 * TODO: This is god awful but it mostly works
                 */
                List<DayOfWeekEnum> dayOfWeekList = DaysOfWeekEnumTypeConverter.listFromSelectedIndices(which);
                String[] dayArray = getResources().getStringArray(R.array.days_of_the_week);

                StringBuilder builder = new StringBuilder();
                boolean first = true;
                for (Integer index : which) {
                    builder.append(dayArray[index]);
                    if (!first) {
                        builder.append(",");
                    }
                    else {
                        first = false;
                    }
                }

                mDays.setText(builder.toString());
                return false;
            }
        }).show();
    }

    @Override
    public void saveHabit() {
        String name = mNameInput.getText().toString();
        String desc = mDescInput.getText().toString();
        boolean hasReminders = mReminderCheckbox.isChecked();
        mReminderTime = new SimpleTime(0, 0);
        mDifficulty = DifficultyEnum.EASY;

        DailyHabit habit = new DailyHabit(name, desc, hasReminders, mActiveDays, mReminderTime, mDifficulty, null);

        DatabaseManager manager = DatabaseManager.getInstance(DailyHabitSummaryMvpFragment.this.getContext());
        if (mIsExistingHabit) {
            habit.setPrimaryKey(mPrimaryKey);
            new HabitUpdateAsyncTask(manager).execute(habit);
        }
        else {
            new DailyHabitInsertAsyncTask(manager).execute(habit);
        }
        finishFragment();
    }

    @Override
    public void deleteHabit() {
        String name = mNameInput.getText().toString();
        String desc = mDescInput.getText().toString();
        boolean hasReminders = mReminderCheckbox.isChecked();
        mReminderTime = new SimpleTime(0, 0);
        mDifficulty = DifficultyEnum.EASY;

        DailyHabit habit = new DailyHabit(name, desc, hasReminders, mActiveDays, mReminderTime, mDifficulty, null);
        habit.setPrimaryKey(mPrimaryKey);

        DatabaseManager manager = DatabaseManager.getInstance(DailyHabitSummaryMvpFragment.this.getContext());
        new HabitDeleteAsyncTask(manager).execute(habit);

        finishFragment();
    }
}
