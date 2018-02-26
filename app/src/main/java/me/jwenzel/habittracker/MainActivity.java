package me.jwenzel.habittracker;

import android.arch.persistence.room.Room;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import me.jwenzel.habittracker.business_objects.RegularHabit;
import me.jwenzel.habittracker.business_objects.DifficultyEnum;
import me.jwenzel.habittracker.business_objects.HabitDao;
import me.jwenzel.habittracker.business_objects.HabitDatabase;
import me.jwenzel.habittracker.utilities.DayOfWeekEnum;
import me.jwenzel.habittracker.view.RegularHabitSummaryMvpFragment;
import me.jwenzel.habittracker.view.RegularHabitSummaryMvpFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new TestAsyncClass().execute();

        Fragment fragment = new RegularHabitSummaryMvpFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.main_activity_fragment_container, fragment).commit();
    }

    private class TestAsyncClass extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            HabitDatabase db = Room.databaseBuilder(getApplicationContext(), HabitDatabase.class, "habit.db").build();

            HabitDao habitDao = db.habitDao();

            // Insert newly created entity
            RegularHabit dh = new RegularHabit("TEst", "Desc", true, new ArrayList<DayOfWeekEnum>(), DifficultyEnum.EASY, 0, 0, 0, 0, 0);
            habitDao.insertAll(dh);

            List<RegularHabit> list = habitDao.getAllRegularHabits();

            for (RegularHabit test : list) {
                System.out.println(test.getName());
            }

            return null;
        }
    }
}
