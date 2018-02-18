package me.jwenzel.habittracker;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

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
        CustomAdapter arrayAdapter = new CustomAdapter(this, testArr);
        list.setAdapter(arrayAdapter);
    }

    public class CustomAdapter extends ArrayAdapter<String> {
        public CustomAdapter(Context context, ArrayList<String> input) {
            super(context, android.R.layout.simple_list_item_1, input);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            String str = getItem(position);
            if (convertView == null) {
                Log.d("TestTag", "The convert view is null");
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_daily_habit, parent, false);
            }

            TextView tvHabitName = (TextView) convertView.findViewById(R.id.daily_title);
            tvHabitName.setText(str);
            return convertView;
        }
    }
}
