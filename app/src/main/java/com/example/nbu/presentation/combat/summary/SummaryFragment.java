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
import com.example.nbu.presentation.combat.encounter.EncounterFragment;
import com.example.nbu.presentation.town.TownCenterFragment;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class SummaryFragment extends AbstractFragment<FragmentSummaryBinding, SummaryViewModel> {

    public static final String GOLD_EARNED = "GOLD_EARNED";

    public static final String EXP_EARNED = "EXP_EARNED";

    private int goldReward = 0;

    private int expReward = 0;

    private Adventurer adventurer;

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adventurer = Adventurer.getInstance();
        //if arguments are null, something went wrong with the navigation
        if (getArguments() != null) {
            goldReward = getArguments().getInt(GOLD_EARNED);
            expReward = getArguments().getInt(EXP_EARNED);
        }else{
            goldReward = viewModel.getGoldReward();
            expReward = viewModel.getExpReward();
        }

        binding.summaryRewardsText.setText(getString(R.string.summary_gold_and_exp_earned_text, goldReward, expReward));
        setupLevelUpUI();
        setupButtons();
    }

    private void setupLevelUpUI() {
        if (adventurer.getExpPointsToSpend() < 1) {
            binding.summaryLevelUpGroup.setVisibility(View.GONE);
            return;
        }
        updatePointsText();
        binding.summaryLevelUpGroup.setVisibility(View.VISIBLE);
        //agility
        binding.summaryAgilityLayout.statIncreaseDescription.setText(R.string.summary_agility_increase_text);
        binding.summaryAgilityLayout.statIncreaseImage.setOnClickListener(v -> {
            adventurer.increaseStat(AdventurerStat.AGILITY);
            updatePointsText();
        });
        //strength
        binding.summaryStrengthLayout.statIncreaseDescription.setText(R.string.summary_strength_increase_text);
        binding.summaryStrengthLayout.statIncreaseImage.setOnClickListener(v -> {
            adventurer.increaseStat(AdventurerStat.STRENGTH);
            updatePointsText();
        });
        //speed
        binding.summarySpeedLayout.statIncreaseDescription.setText(R.string.summary_speed_increase_text);
        binding.summarySpeedLayout.statIncreaseImage.setOnClickListener(v -> {
            adventurer.increaseStat(AdventurerStat.SPEED);
            updatePointsText();
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
            navigateToView(TownCenterFragment.class, null);
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        viewModel.saveRewards(goldReward, expReward);
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
