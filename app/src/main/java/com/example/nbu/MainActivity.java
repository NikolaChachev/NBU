package com.example.nbu;

import android.os.Bundle;
import android.view.View;
import androidx.core.content.res.ResourcesCompat;
import androidx.lifecycle.ViewModelProvider;
import com.example.nbu.databinding.ActivityMainBinding;
import com.example.nbu.mvvm.activity.AbstractActivity;
import com.example.nbu.mvvm.vm.EmptyViewModel;
import com.example.nbu.presentation.inventory.InventoryFragment;
import com.example.nbu.presentation.menu.MainMenuFragment;
import com.example.nbu.service.data.SharedCharacterViewModel;
import com.example.nbu.service.player.Adventurer;
import com.example.nbu.service.prefs.NbuSharedPrefs;
import dagger.hilt.android.AndroidEntryPoint;
import javax.inject.Inject;

@AndroidEntryPoint
public class MainActivity extends AbstractActivity<ActivityMainBinding, EmptyViewModel> {

    @Inject
    NbuSharedPrefs sharedPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Adventurer adventurer = Adventurer.initializeAdventurer("J`zargo");
        binding.mainCharacterName.setText(adventurer.getName());
        binding.mainCharacterName.setOnClickListener(v -> openView(InventoryFragment.class, null));
        SharedCharacterViewModel sharedViewModel = new ViewModelProvider(this).get(SharedCharacterViewModel.class);

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
        sharedViewModel._currentBackground.observe(this, data -> binding.mainActivityImage.setImageDrawable(
                ResourcesCompat.getDrawable(getResources(), data, null)));
        openView(MainMenuFragment.class, null);
    }

    @Override
    protected void onStop() {
        super.onStop();
        sharedPrefs.saveGameProgress();
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