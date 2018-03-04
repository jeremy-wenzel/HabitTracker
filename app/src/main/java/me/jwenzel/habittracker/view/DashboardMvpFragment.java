package me.jwenzel.habittracker.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import me.jwenzel.habittracker.R;
import me.jwenzel.habittracker.presenter.DashboardPresenter;
import me.jwenzel.habittracker.presenter.DashboardPresenterImpl;

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
        mViewPager = view.findViewById(R.id.viewpager);
        mViewPager.setAdapter(mViewPagerAdapter);

        TabLayout tabLayout = view.findViewById(R.id.tablayout);
        tabLayout.setupWithViewPager(mViewPager);

        return view;
    }

    /**
     * A class to create the tabs
     */
    class ViewPagerAdapter extends FragmentPagerAdapter {

        ArrayList<BaseMvpFragment> mFragments = new ArrayList<BaseMvpFragment>();

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(BaseMvpFragment fragment) {
            mFragments.add(fragment);
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return mFragments.get(position).getTitle();
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
