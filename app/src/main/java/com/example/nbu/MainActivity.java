package com.example.nbu;

import android.os.Bundle;
import android.util.Log;

import com.example.nbu.databinding.ActivityMainBinding;
import com.example.nbu.mvvm.activity.AbstractActivity;
import com.example.nbu.mvvm.vm.EmptyViewModel;
import com.example.nbu.presentation.InventoryFragment;
import com.example.nbu.service.pojos.Item;
import com.example.nbu.service.pojos.Weapon;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AbstractActivity<ActivityMainBinding, EmptyViewModel> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Weapon weapon = new Weapon("Axeoverthenlettersfoa", 200, 5, 10, 2);
        Item weapon1 = new Weapon("Sword", 100, 2,4,1);
        Log.e("test", weapon + "\n" + weapon1);
        openView(InventoryFragment.class, null);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected Class<EmptyViewModel> getViewModelClass() {
        return EmptyViewModel.class;
    }

    @Override
    protected int getContainerViewId() {
        return R.id.fragment_container;
    }


}