package com.example.nbu.presentation.combat.battle;

import android.os.Bundle;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.nbu.BR;
import com.example.nbu.R;
import com.example.nbu.databinding.FragmentCombatBinding;
import com.example.nbu.mvvm.fragment.AbstractFragment;
import com.example.nbu.presentation.combat.summary.SummaryFragment;
import com.example.nbu.presentation.menu.MainMenuFragment;
import com.example.nbu.service.data.SharedCharacterViewModel;
import com.example.nbu.service.player.Adventurer;
import com.example.nbu.service.player.Inventory;
import com.example.nbu.service.prefs.NbuSharedPrefs;
import dagger.hilt.android.AndroidEntryPoint;
import javax.inject.Inject;

@AndroidEntryPoint
public class CombatFragment extends AbstractFragment<FragmentCombatBinding, CombatViewModel> {

    private CombatAdapter combatAdapter;

    private SharedCharacterViewModel sharedViewModel;

    @Inject
    NbuSharedPrefs sharedPrefs;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.setCombatVM(viewModel);
        viewModel.runNextCombatRound();
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedCharacterViewModel.class);
        sharedViewModel.updateCurrentBackground(viewModel.getEnemyImage());
        combatAdapter = new CombatAdapter(viewModel.getLogs(), getContext());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        binding.combatLogRecycleView.setAdapter(combatAdapter);
        binding.combatLogRecycleView.setLayoutManager(layoutManager);
        setupButtons();
        setupObservers();
    }

    private void setupButtons() {
        binding.combatLeftButton.setOnClickListener(view -> {
            viewModel.runNextCombatRound();
            combatAdapter.insertItems(viewModel.getLogs());
            binding.combatLogRecycleView.scrollToPosition(combatAdapter.getItemCount() - 1);
            sharedViewModel.updateCurrentHealth();

        });

        binding.combatRightButton.setOnClickListener(view -> {
            viewModel.runCombatUntilEnd();
            sharedViewModel.updateCurrentHealth();
            combatAdapter.insertItems(viewModel.getLogs());
            binding.combatLogRecycleView.scrollToPosition(combatAdapter.getItemCount() - 1);
        });
    }

    private void updateButtonsFunctionality(int rightButtonText, int leftButtonText, IButtonFunc leftButton, IButtonFunc rightButton) {
        binding.combatLeftButton.setText(leftButtonText);
        binding.combatLeftButton.setOnClickListener(leftButton::callFunctionality);
        binding.combatRightButton.setText(rightButtonText);
        binding.combatRightButton.setOnClickListener(rightButton::callFunctionality);

    }

    private void setupObservers() {
        viewModel._combatStatus.observe(this.getViewLifecycleOwner(), value -> {
            switch (value) {
                case ENCOUNTER:
                case IN_PROGRESS:
                    break;
                case VICTORY:
                    binding.combatLeftButton.setVisibility(View.GONE);
                    binding.combatRightButton.setText(R.string.combat_right_button_victory);
                    updateButtonsFunctionality(
                            R.string.combat_right_button_victory,
                            R.string.combat_left_button_progress,
                            (v) -> {
                            },
                            (v) -> {
                                navigateToView(SummaryFragment.class, new Bundle());
                            });
                    break;
                case DEFEAT:
                    binding.combatRightButton.setText(R.string.combat_right_button_victory);
                    sharedPrefs.clearSaveFile();
                    updateButtonsFunctionality(
                            R.string.combat_right_button_defeat,
                            R.string.combat_left_button_defeat,
                            (v) -> {
                                requireActivity().finishAffinity();
                                System.exit(0);
                            },
                            (v) -> {
                                Adventurer.restartAdventurerStats();
                                Inventory.getInstance().clearInventory();
                                sharedViewModel.updateCurrentHealth();
                                navigateToView(MainMenuFragment.class, null);
                            });
                    //todo
                    break;
            }
        });
    }

    @Override
    protected int getViewModelResId() {
        return BR.combatVM;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_combat;
    }

    @Override
    protected Class<CombatViewModel> getViewModelClass() {
        return CombatViewModel.class;
    }
}
