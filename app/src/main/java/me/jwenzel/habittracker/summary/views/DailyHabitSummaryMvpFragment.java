package me.jwenzel.habittracker.summary.views;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v4.app.AlarmManagerCompat;
import android.support.v4.app.NotificationCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.jwenzel.habittracker.NotificationHelper;
import me.jwenzel.habittracker.broadcast_receivers.NotificationReceiver;
import me.jwenzel.habittracker.business_objects.BaseHabit;
import me.jwenzel.habittracker.database.async_tasks.HabitInsertAsyncTask;
import me.jwenzel.habittracker.database.DatabaseManager;
import me.jwenzel.habittracker.R;
import me.jwenzel.habittracker.business_objects.DailyHabit;
import me.jwenzel.habittracker.business_objects.DifficultyEnum;
import me.jwenzel.habittracker.business_objects.SimpleTime;
import me.jwenzel.habittracker.database.async_tasks.HabitDeleteAsyncTask;
import me.jwenzel.habittracker.database.async_tasks.HabitUpdateAsyncTask;
import me.jwenzel.habittracker.dialogs.MasterDialoger;
import me.jwenzel.habittracker.services.ReminderNotificationService;
import me.jwenzel.habittracker.summary.presenters.DailyHabitSummaryPresenter;
import me.jwenzel.habittracker.summary.presenters.DailyHabitSummaryPresenterImpl;
import me.jwenzel.habittracker.utilities.DayOfWeekEnum;
import me.jwenzel.habittracker.utilities.DaysOfWeekEnumTypeConverter;

public class DailyHabitSummaryMvpFragment extends BaseHabitSummaryMvpFragment<DailyHabitSummaryView, DailyHabitSummaryPresenter> implements DailyHabitSummaryView {

    private static final String PRIMARY_KEY = "primary_key";

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

    public static DailyHabitSummaryMvpFragment newInstance(DailyHabit habit) {
        DailyHabitSummaryMvpFragment fragment = new DailyHabitSummaryMvpFragment();

        Bundle args = new Bundle();
        args.putInt(PRIMARY_KEY, habit.getPrimaryKey());
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
            setIsExistingHabit(true);
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
    public void onStart() {
        super.onStart();
        if (isExistingHabit()) {
            getPresenter().onStart(mPrimaryKey);
        }
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
        if (isExistingHabit()) {
            habit.setPrimaryKey(mPrimaryKey);
            new HabitUpdateAsyncTask(manager).execute(habit);
        }
        else {
            new HabitInsertAsyncTask(manager).execute(habit);
            NotificationHelper helper = getApplication().getNotificationHelper();
            helper.notify(1, helper.showHabitNotification(habit));
        }

        AlarmManager alarmManager = (AlarmManager) getActivity().getSystemService(getActivity().ALARM_SERVICE);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND, 10);
        Intent service = new Intent(getContext(), NotificationReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getContext(), 1, service, PendingIntent.FLAG_ONE_SHOT);

        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);

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

    @Override
    public void loadHabit(int habitId) {
        new SelectHabitAsyncTask().execute(habitId);
    }

    @Override
    public void displayHabit(BaseHabit habit) {
        mNameInput.setText(habit.getName());
        mDescInput.setText(habit.getDescription());
        mReminderCheckbox.setChecked(habit.isUsingReminders());
    }

    private class SelectHabitAsyncTask extends AsyncTask<Integer, Void, BaseHabit> {

        @Override
        protected BaseHabit doInBackground(Integer... ints) {
            DatabaseManager manager = getApplication().getDatabaseManager();
            DailyHabit habit = manager.getDailyHabit(ints[0]);
            return habit;
        }

        @Override
        protected void onPostExecute(BaseHabit habit) {
            getPresenter().onHabitLoaded(habit);
        }
    }
}
