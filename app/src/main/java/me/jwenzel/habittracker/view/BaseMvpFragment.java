package me.jwenzel.habittracker.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;

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

    public int getTitle() {
        return 0;
    }
}
