package me.jwenzel.habittracker;

public abstract class BasePresenterImpl<V extends BaseView> implements BasePresenter {
    // The view we are attached to
    private V mView;

    /**
     * Creates the BasePresenter and sets the view for the rest of the presenter classes
     * to access
     * @param view
     */
    public BasePresenterImpl(V view) {
        mView = view;
    }

    /**
     * Returns the view that the presenter is attached to
     * @return The view we are attached to
     */
    protected V getView() {
        return mView;
    }

}
