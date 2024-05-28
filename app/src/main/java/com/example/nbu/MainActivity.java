package com.example.nbu;

import android.os.Bundle;
import androidx.lifecycle.ViewModelProvider;
import com.example.nbu.databinding.ActivityMainBinding;
import com.example.nbu.mvvm.activity.AbstractActivity;
import com.example.nbu.mvvm.vm.EmptyViewModel;
import com.example.nbu.presentation.character.Adventurer;
import com.example.nbu.presentation.combat.encounter.EncounterFragment;
import com.example.nbu.presentation.inventory.InventoryFragment;
import com.example.nbu.service.data.SharedCharacterViewModel;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AbstractActivity<ActivityMainBinding, EmptyViewModel> {

    private SharedCharacterViewModel sharedViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Adventurer.initializeAdventurer("Bobcho");
        binding.mainCharacterName.setText(Adventurer.getInstance().getName());
        binding.mainCharacterName.setOnClickListener(v -> {
            openView(InventoryFragment.class, null);
        });
        sharedViewModel = new ViewModelProvider(this).get(SharedCharacterViewModel.class);
        openView(EncounterFragment.class, null);
        binding.mainButton.setOnClickListener(l -> {

        });
        sharedViewModel._characterHealth.observe(this, data -> {
            binding.mainCharacterHealthBar.setProgress(data.intValue());
        });
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