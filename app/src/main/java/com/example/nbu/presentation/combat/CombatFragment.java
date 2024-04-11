package com.example.nbu.presentation.combat;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nbu.BR;
import com.example.nbu.R;
import com.example.nbu.databinding.FragmentCombatBinding;
import com.example.nbu.mvvm.fragment.AbstractFragment;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class CombatFragment extends AbstractFragment<FragmentCombatBinding, CombatViewModel> {

    private CombatAdapter combatAdapter;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel.runNextCombatRound();
        combatAdapter = new CombatAdapter(viewModel.getLogs(), getContext());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        binding.combatLogRecycleView.setAdapter(combatAdapter);
        binding.combatLogRecycleView.setLayoutManager(layoutManager);
        Log.e("test", combatAdapter.getItemCount() + " count");
        setupButtons();
    }

    private void setupButtons() {
        binding.combatButtonNextRound.setOnClickListener(view -> {
            viewModel.runNextCombatRound();
            combatAdapter.insertItems(viewModel.getLogs());
            binding.combatLogRecycleView.scrollToPosition(combatAdapter.getItemCount() - 1);
        });

        binding.combatButtonSkipBattle.setOnClickListener(view -> {
            viewModel.runCombatUntilEnd();
            combatAdapter.insertItems(viewModel.getLogs());
            binding.combatLogRecycleView.scrollToPosition(combatAdapter.getItemCount() - 1);
        });
    }

    @Override
    protected int getViewModelResId() {
        return BR.EmptyVM;
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
