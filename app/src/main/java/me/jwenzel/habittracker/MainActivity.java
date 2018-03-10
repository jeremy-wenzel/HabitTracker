package me.jwenzel.habittracker;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import me.jwenzel.habittracker.view.dashboard.DashboardMvpFragment;

public class MainActivity extends AppCompatActivity {

    Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TODO: Need to change how we set up the action bar
        setSupportActionBar((Toolbar)findViewById(R.id.toolbar));
        ActionBar toolbar = getSupportActionBar();
        Fragment fragment = new DashboardMvpFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.main_activity_fragment_container, fragment)
                .commit();
    }
}
