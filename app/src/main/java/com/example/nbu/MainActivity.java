package com.example.nbu;

import android.os.Bundle;
import android.view.View;
import androidx.lifecycle.ViewModelProvider;
import com.example.nbu.databinding.ActivityMainBinding;
import com.example.nbu.mvvm.activity.AbstractActivity;
import com.example.nbu.mvvm.vm.EmptyViewModel;
import com.example.nbu.presentation.character.Adventurer;
import com.example.nbu.presentation.inventory.InventoryFragment;
import com.example.nbu.presentation.menu.MainMenuFragment;
import com.example.nbu.service.data.SharedCharacterViewModel;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AbstractActivity<ActivityMainBinding, EmptyViewModel> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Adventurer adventurer = Adventurer.initializeAdventurer("Bobcho");
        binding.mainCharacterName.setText(adventurer.getName());
        binding.mainCharacterName.setOnClickListener(v -> openView(InventoryFragment.class, null));
        SharedCharacterViewModel sharedViewModel = new ViewModelProvider(this).get(SharedCharacterViewModel.class);
        openView(MainMenuFragment.class, null);

        sharedViewModel._characterHealth.observe(this, data -> {
            binding.mainCharacterHealthBar.setProgress(data.intValue());
            binding.mainCharacterHealthText.setText(
                    getString(R.string.main_activity_character_health_text, (int) adventurer.getCurrentHealth(), (int) adventurer.getMaxHealth()));
        });
        sharedViewModel._gameStarted.observe(this, data -> {
            if (data) {
                binding.mainCharacterInfoGroup.setVisibility(View.VISIBLE);
            }
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