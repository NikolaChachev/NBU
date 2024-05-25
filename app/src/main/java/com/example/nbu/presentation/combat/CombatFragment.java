package com.example.nbu.presentation.combat;

import android.os.Bundle;
import android.util.Log;
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
import com.example.nbu.service.data.SharedCharacterViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class CombatFragment extends AbstractFragment<FragmentCombatBinding, CombatViewModel> {

    private CombatAdapter combatAdapter;

    private SharedCharacterViewModel sharedViewModel;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.setCombatVM(viewModel);
        viewModel.runNextCombatRound();
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedCharacterViewModel.class);
        combatAdapter = new CombatAdapter(viewModel.getLogs(), getContext());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        binding.combatLogRecycleView.setAdapter(combatAdapter);
        binding.combatLogRecycleView.setLayoutManager(layoutManager);
        setupButtons();
        setupObservers();
    }

    private void setupButtons() {
        binding.combatButtonNextRound.setOnClickListener(view -> {
            viewModel.runNextCombatRound();
            combatAdapter.insertItems(viewModel.getLogs());
            binding.combatLogRecycleView.scrollToPosition(combatAdapter.getItemCount() - 1);
            sharedViewModel.updateCurrentHealth();

        });

        binding.combatButtonSkipBattle.setOnClickListener(view -> {
            viewModel.runCombatUntilEnd();
            sharedViewModel.updateCurrentHealth();
            combatAdapter.insertItems(viewModel.getLogs());
            binding.combatLogRecycleView.scrollToPosition(combatAdapter.getItemCount() - 1);
        });
    }

    private void setupObservers(){
        viewModel._isCombatOver.observe(this.getViewLifecycleOwner(), obs -> {
            if(obs){
                binding.combatButtonNextRound.setEnabled(false);
                binding.combatButtonSkipBattle.setEnabled(false);
            }
        });

        viewModel._combatStatus.observe(this.getViewLifecycleOwner(), value -> {
            switch (value){
                case ENCOUNTER:
                    Log.e("test", "encounter started");
                    //todo
                    break;
                case IN_PROGRESS:
                    Log.e("test", "encounter in progress");
                    //todo
                    break;
                case VICTORY:
                    Log.e("test", "Victory");
                    //todo
                    break;
                case DEFEAT:
                    Log.e("test", "Defeat");
                    //todo
                    break;
            }
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
