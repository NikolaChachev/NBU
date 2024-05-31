package com.example.nbu.presentation.combat.encounter;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.library.baseAdapters.BR;
import com.example.nbu.R;
import com.example.nbu.databinding.FragmentEncounterBinding;
import com.example.nbu.mvvm.fragment.AbstractFragment;
import com.example.nbu.presentation.combat.battle.CombatFragment;
import com.example.nbu.presentation.combat.battle.CombatViewModel;
import com.example.nbu.presentation.town.TownCenterFragment;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class EncounterFragment extends AbstractFragment<FragmentEncounterBinding, CombatViewModel> {

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel.refreshEnemy();
        binding.encounterFleeCombat.setOnClickListener(v -> {
            viewModel.attemptToFlee();
        });
        binding.encounterStartCombatButton.setOnClickListener(v -> navigateToView(CombatFragment.class, null));

        binding.encounterEnemyDescription.setText(viewModel.getEnemyDescription());
        viewModel._combatStatus.observe(getViewLifecycleOwner(), result -> {
            switch (result) {
                case FAILED_FLEE:
                    navigateToView(CombatFragment.class, null);
                    break;
                case SUCCESSFUL_FLEE:
                    buildAlertDialog();
                    break;
                default:
                    break;
            }
        });
    }

    private void buildAlertDialog() {
        final String title = "Predicament";
        final String message = "You managed to flee!";

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(R.string.encounter_dialog_continue_button, (dialogInterface, i) -> {
                    //continue adventure
                    viewModel.refreshEnemy();
                    binding.encounterEnemyDescription.setText(viewModel.getEnemyDescription());

                })
                .setNegativeButton(R.string.encounter_dialog_return_to_town_button, (dialogInterface, i) -> {
                    //return to Town
                    navigateToView(TownCenterFragment.class, null);
                });
        builder.create().show();
    }

    @Override
    protected int getViewModelResId() {
        return BR.combatVM;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_encounter;
    }

    @Override
    protected Class<CombatViewModel> getViewModelClass() {
        return CombatViewModel.class;
    }
}
