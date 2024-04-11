package com.example.nbu;

import android.os.Bundle;

import com.example.nbu.databinding.ActivityMainBinding;
import com.example.nbu.mvvm.activity.AbstractActivity;
import com.example.nbu.mvvm.vm.EmptyViewModel;
import com.example.nbu.presentation.character.Adventurer;
import com.example.nbu.presentation.combat.CombatFragment;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AbstractActivity<ActivityMainBinding, EmptyViewModel> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Adventurer.initializeAdventurer("Bobcho");
        binding.mainCharacterName.setText(Adventurer.getInstance().getName());
        openView(CombatFragment.class, null);
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