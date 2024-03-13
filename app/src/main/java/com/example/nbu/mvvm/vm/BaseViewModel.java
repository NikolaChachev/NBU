package com.example.nbu.mvvm.vm;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModel;

public class BaseViewModel extends ViewModel implements IViewModel {

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Nullable
    @Override
    public Bundle postNavigationArgs() {
        return null;
    }

    @Override
    public void receiveNavigationArgs(@Nullable final Bundle args) {

    }
}
