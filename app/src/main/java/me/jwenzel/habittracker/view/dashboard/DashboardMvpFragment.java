package me.jwenzel.habittracker.view.dashboard;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.afollestad.materialdialogs.MaterialDialog;

import java.util.ArrayList;

import me.jwenzel.habittracker.R;
import me.jwenzel.habittracker.dialogs.MasterDialoger;
import me.jwenzel.habittracker.presenter.dashboard.DashboardPresenter;
import me.jwenzel.habittracker.presenter.dashboard.DashboardPresenterImpl;
import me.jwenzel.habittracker.view.BaseMvpFragment;

public class DashboardMvpFragment extends BaseMvpFragment<DashboardView, DashboardPresenter> implements DashboardView {
    private ViewPagerAdapter mViewPagerAdapter;
    private ViewPager mViewPager;

    /**
     * Creates the presenter object and returns it. This method used when the
     * MvpFragment has just started and allows us to set the presenter
     *
     * @return A new instance of the presenter that we will be using
     */
    @Override
    protected DashboardPresenter createPresenter() {
        return new DashboardPresenterImpl(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        mViewPagerAdapter = new ViewPagerAdapter(getFragmentManager());
            mViewPagerAdapter.addFragment(new DailyHabitDashboardMvpFragment());
        mViewPagerAdapter.addFragment(new RegularHabitDashboardMvpFragment());
        mViewPager = view.findViewById(R.id.viewpager);
        mViewPager.setAdapter(mViewPagerAdapter);

        TabLayout tabLayout = view.findViewById(R.id.tablayout);
        tabLayout.setupWithViewPager(mViewPager);

        FloatingActionButton button = view.findViewById(R.id.fab);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MasterDialoger.buildAddHabitDialog(DashboardMvpFragment.this.getContext(), new MaterialDialog.ListCallbackSingleChoice() {
                    @Override
                    public boolean onSelection(MaterialDialog dialog, View itemView, int which, CharSequence text) {
                        Fragment fragment = null;
                        switch (which) {
                            case 0:
                                // Create a new fragment and move to it
                                fragment = new DailyHabitDashboardMvpFragment();
                                break;
                            case 1:
                                // Create a new fragment and move to it
                                fragment = new RegularHabitDashboardMvpFragment();
                                break;
                        }

                        if (fragment != null) {
                            DashboardMvpFragment.this.startNewFragment(fragment, true);
                            return true;
                        }

                        return false;
                    }
                }).show();
            }
        });

        return view;
    }

    /**
     * A class to create the tabs
     */
    class ViewPagerAdapter extends FragmentPagerAdapter {

        ArrayList<BaseMvpFragment> mFragments = new ArrayList<>();
        ArrayList<String> mTitles = new ArrayList<>();

        ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        void addFragment(BaseMvpFragment fragment) {
            mFragments.add(fragment);
            mTitles.add(getString(fragment.getTitle()));
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles.get(position);
        }

        /**
         * Return the Fragment associated with a specified position.
         *
         * @param position
         */
        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        /**
         * Return the number of views available.
         */
        @Override
        public int getCount() {
            return mFragments.size();
        }
    }
}
