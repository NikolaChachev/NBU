package com.example.nbu;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.nbu.mvvm.AbstractViewModel;
import com.example.nbu.mvvm.activity.AbstractActivity;
import com.example.nbu.mvvm.vm.EmptyViewModel;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AbstractActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected Class<AbstractViewModel> getViewModelClass() {
        return AbstractViewModel.class;
    }

    @Override
    protected int getContainerViewId() {
        return R.id.fragment_container;
    }


}