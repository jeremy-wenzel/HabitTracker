package me.jwenzel.habittracker.view.summary;

import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;

import java.util.ArrayList;
import java.util.List;

import me.jwenzel.habittracker.DailyHabitInsertAsyncTask;
import me.jwenzel.habittracker.DatabaseManager;
import me.jwenzel.habittracker.R;
import me.jwenzel.habittracker.business_objects.DailyHabit;
import me.jwenzel.habittracker.business_objects.DifficultyEnum;
import me.jwenzel.habittracker.business_objects.HabitDatabase;
import me.jwenzel.habittracker.business_objects.SimpleTime;
import me.jwenzel.habittracker.dialogs.MasterDialoger;
import me.jwenzel.habittracker.presenter.summary.DailyHabitSummaryPresenter;
import me.jwenzel.habittracker.presenter.summary.DailyHabitSummaryPresenterImpl;
import me.jwenzel.habittracker.utilities.DayOfWeekEnum;
import me.jwenzel.habittracker.utilities.DaysOfWeekEnumTypeConverter;
import me.jwenzel.habittracker.view.BaseMvpFragment;

public class DailyHabitSummaryMvpFragment extends BaseMvpFragment<DailyHabitSummaryView, DailyHabitSummaryPresenter> implements DailyHabitSummaryView {

    // TODO: We need to rename these
    private EditText mNameInput;
    private EditText mDescInput;
    private TextView mDaysActive;
    private TextView mDays;
    private CheckBox mReminderCheckbox;
    private Button mSaveButton;

    private SimpleTime mReminderTime;
    private ArrayList<DayOfWeekEnum> mActiveDays = new ArrayList<>();
    private DifficultyEnum mDifficulty;

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
        View view = inflater.inflate(R.layout.fragment_daily_habit_summary, container, false);

        mNameInput = view.findViewById(R.id.et_daily_habit_name);
        mDescInput = view.findViewById(R.id.et_daily_habit_desc);
        mReminderCheckbox = view.findViewById(R.id.cb_daily_habit_reminder);
        mSaveButton = view.findViewById(R.id.btn_daily_habit_save);
        mDaysActive = view.findViewById(R.id.tv_daily_habit_active_desc);
        mDays = view.findViewById(R.id.tv_daily_habit_active_days);

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

        new DailyHabitInsertAsyncTask(DatabaseManager.getInstance(DailyHabitSummaryMvpFragment.this.getContext())).execute(habit);
        finishFragment();
    }
}
