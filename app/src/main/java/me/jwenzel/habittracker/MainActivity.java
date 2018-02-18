package me.jwenzel.habittracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayList<String> testArr = new ArrayList<String>();
        testArr.add("test1");
        testArr.add("test2");
        testArr.add("test3");
        ListView list = (ListView) findViewById(R.id.main_list_view);
        DailyHabitAdapter arrayAdapter = new DailyHabitAdapter(this, testArr);
        list.setAdapter(arrayAdapter);
    }
}
