package me.jwenzel.habittracker;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Button;
import me.jwenzel.habittracker.view.dashboard.DashboardMvpFragment;

public class MainActivity extends AppCompatActivity {

    Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        mButton = findViewById(R.id.test_button);
//
//        mButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                ArrayList<DayOfWeekEnum> list = new ArrayList<>();
//                list.add(DayOfWeekEnum.MONDAY);
//                list.add(DayOfWeekEnum.SATURDAY);
//                list.add(DayOfWeekEnum.WEDNESDAY);
//                MasterDialoger.buildDaysOfTheWeekDialog(MainActivity.this, list, new MaterialDialog.ListCallbackMultiChoice() {
//
//                    /**
//                     * Return true to allow the check box to be checked, if the alwaysCallSingleChoice() option is
//                     * used.
//                     *
//                     * @param dialog The dialog of which a list item was selected.
//                     * @param which  The indices of the items that were selected.
//                     * @param text   The text of the items that were selected.
//                     * @return True to allow the checkbox to be selected.
//                     */
//                    @Override
//                    public boolean onSelection(MaterialDialog dialog, Integer[] which, CharSequence[] text) {
//                        return false;
//                    }
//                }).show();
//            }
//        });
//        new TestAsyncClass().execute();
//
        // TODO: Need to change how we set up the action bar
        setSupportActionBar((Toolbar)findViewById(R.id.toolbar));
        ActionBar toolbar = getSupportActionBar();
        Fragment fragment = new DashboardMvpFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.main_activity_fragment_container, fragment)
                .addToBackStack(null)
                .commit();
    }

    /**
     * Take care of popping the fragment back stack or finishing the activity
     * as appropriate.
     */
    @Override
    public void onBackPressed() {
        FragmentManager fm = getSupportFragmentManager();
        if (fm.getBackStackEntryCount() > 0) {
            Log.i("MainActivity", "popping backstack");
            fm.popBackStack();
        }
        else {
            super.onBackPressed();
        }
    }

    //    private class TestAsyncClass extends AsyncTask<Void, Void, Void> {
//
//        @Override
//        protected Void doInBackground(Void... voids) {
//            HabitDatabase db = Room.databaseBuilder(getApplicationContext(), HabitDatabase.class, "habit.db").build();
//
//            HabitDao habitDao = db.habitDao();
//
//            // Insert newly created entity
//            RegularHabit dh = new RegularHabit("TEst", "Desc", true, new ArrayList<DayOfWeekEnum>(), DifficultyEnum.EASY, 0, 0, 0, 0, 0);
//            habitDao.insertAll(dh);
//
//            List<RegularHabit> list = habitDao.getAllRegularHabits();
//
//            for (RegularHabit test : list) {
//                System.out.println(test.getName());
//            }
//
//            return null;
//        }
//    }
}
