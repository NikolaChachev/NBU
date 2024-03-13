package com.example.nbu.mvvm.vm;

import android.os.Bundle;

public interface IViewModel {

    /**
     * This method is called when view model is resumed.
     * The view model lifecycle is bound to the views lifecycle.
     * Note that if the view is activity
     * this method will be called from the [Activity.onResume].
     * If the view is fragment this method may be called
     * from [Fragment.onResume] or [Fragment.onHiddenChanged].
     */
    void onResume();

    /**
     * This method is called when view model is paused.
     * The view model lifecycle is bound to the views lifecycle.
     * Note that if the view is of type activity
     * this method will be called from the [Activity.onPause].
     * If the view is of type fragment this method may be called
     * from [Fragment.onPause] or [Fragment.onHiddenChanged].
     */
    void onPause();

    /**
     * Override this method if you want to pass arguments
     * to the next screen that is about to open.
     *
     * @return the arguments that will be passed
     */
    Bundle postNavigationArgs();

    /**
     * Override this method to receive navigation arguments
     * when this view model is resumed.
     *
     * @param args the arguments that are passed, this may be null
     */
    void receiveNavigationArgs(Bundle args);
}
