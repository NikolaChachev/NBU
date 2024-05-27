package com.example.nbu.presentation.combat.summary;

import android.os.Bundle;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.example.nbu.BR;
import com.example.nbu.R;
import com.example.nbu.databinding.FragmentSummaryBinding;
import com.example.nbu.mvvm.fragment.AbstractFragment;
import com.example.nbu.presentation.character.Adventurer;
import com.example.nbu.presentation.character.AdventurerStat;
import com.example.nbu.presentation.character.Enemy;
import com.example.nbu.presentation.combat.encounter.EncounterFragment;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class SummaryFragment extends AbstractFragment<FragmentSummaryBinding, SummaryViewModel> {

    public static final String LAST_ENEMY_KEY = "LAST_ENEMY_DEFEATED";

    private Adventurer adventurer;

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adventurer = Adventurer.getInstance();
        //if arguments are null, something went wrong with the navigation
        assert getArguments() != null;
        int enemy = getArguments().getInt(LAST_ENEMY_KEY);
        StringBuilder sb = new StringBuilder();
        sb.append("You have earned ").append(enemy).append(" gold from this encounter!");
        binding.summaryRewardsText.setText(sb.toString());
        setupLevelUpUI();
        setupButtons();
    }

    private void setupLevelUpUI() {
        if (adventurer.getExpPointsToSpend() > 0) {
            binding.summaryLevelUpGroup.setVisibility(View.GONE);
            return;
        }
        updatePointsText();
        binding.summaryLevelUpGroup.setVisibility(View.VISIBLE);
        //agility
        binding.summaryAgilityLayout.statIncreaseDescription.setText(R.string.summary_agility_increase_text);
        binding.summaryAgilityLayout.statIncreaseImage.setOnClickListener(v -> {
            updatePointsText();
            adventurer.increaseStat(AdventurerStat.AGILITY);
        });
        //strength
        binding.summaryStrengthLayout.statIncreaseDescription.setText(R.string.summary_strength_increase_text);
        binding.summaryStrengthLayout.statIncreaseImage.setOnClickListener(v -> {
            updatePointsText();
            adventurer.increaseStat(AdventurerStat.STRENGTH);
        });
        //speed
        binding.summarySpeedLayout.statIncreaseDescription.setText(R.string.summary_speed_increase_text);
        binding.summarySpeedLayout.statIncreaseImage.setOnClickListener(v -> {
            updatePointsText();
            adventurer.increaseStat(AdventurerStat.SPEED);
        });
    }

    private void updatePointsText() {
        binding.summaryPointsToSpend.setText(getString(R.string.summary_points_remaining_text, adventurer.getExpPointsToSpend()));
    }

    private void setupButtons() {
        binding.summaryContinueExploringButton.setOnClickListener(v -> {
            navigateToView(EncounterFragment.class, null);
        });
        binding.summaryGoToTownButton.setOnClickListener(v -> {
            //todo
        });
    }

    @Override
    protected int getViewModelResId() {
        return BR.summaryVM;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_summary;
    }

    @Override
    protected Class<SummaryViewModel> getViewModelClass() {
        return SummaryViewModel.class;
    }
}
