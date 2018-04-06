package me.jwenzel.habittracker.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import me.jwenzel.habittracker.R;
import me.jwenzel.habittracker.presenter.BasePresenter;

public abstract class BaseMvpFragment<V extends BaseView, P extends BasePresenter> extends Fragment implements BaseView {

    // The presenter that we are attached to
    private P mPresenter;

    public BaseMvpFragment() {
        mPresenter = createPresenter();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * Returns the the presenter that we are attached to
     *
     * @return The instance of the presenter
     */
    protected P getPresenter() {
        return mPresenter;
    }

    /**
     * Creates the presenter object and returns it. This method used when the
     * MvpFragment has just started and allows us to set the presenter
     *
     * @return A new instance of the presenter that we will be using
     */
    protected abstract P createPresenter();

    /**
     * The title of the fragment that should be shown to users
     * @return The string resource id to be shown
     */
    public int getTitle() {
        return 0;
    }

    /**
     * Starts a new fragment. Can also add the fragment to the backstack if needed
     * @param fragment The fragment to be started
     * @param addToBackStack Should the fragment transaction be added to the backstack?
     */
    public void startNewFragment(Fragment fragment, boolean addToBackStack) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction().replace(R.id.main_activity_fragment_container, fragment);

        if (addToBackStack) {
            transaction.addToBackStack(null);
        }

        transaction.commit();
    }

    public void finishFragment() {
        getFragmentManager().popBackStack();
    }
}
