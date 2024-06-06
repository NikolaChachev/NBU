package com.example.nbu.presentation.town.tavern;

import android.os.Bundle;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.library.baseAdapters.BR;
import androidx.lifecycle.ViewModelProvider;
import com.example.nbu.R;
import com.example.nbu.databinding.FragmentTavernBinding;
import com.example.nbu.mvvm.fragment.AbstractFragment;
import com.example.nbu.mvvm.vm.EmptyViewModel;
import com.example.nbu.presentation.town.TownCenterFragment;
import com.example.nbu.service.data.SharedCharacterViewModel;
import com.example.nbu.service.player.Adventurer;

public class TavernFragment extends AbstractFragment<FragmentTavernBinding, EmptyViewModel> {

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SharedCharacterViewModel sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedCharacterViewModel.class);
        sharedViewModel.updateCurrentBackground(R.drawable.tavern_art);
        binding.tavernRestButton.setOnClickListener(v -> {
            Adventurer adventurer = Adventurer.getInstance();
            boolean restSuccessful = adventurer.performRest();
            if (restSuccessful){
                sharedViewModel.updateCurrentHealth();
                createShortToast("You managed to rest and heal!");
            }else{
                createShortToast("Not enough gold to afford a meal and a bad!");
            }
        });
        binding.tavernLeaveButton.setOnClickListener(v -> {
            navigateToView(TownCenterFragment.class, null);
        });

    }

    @Override
    protected int getViewModelResId() {
        return BR.EmptyVM;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_tavern;
    }

    @Override
    protected Class<EmptyViewModel> getViewModelClass() {
        return EmptyViewModel.class;
    }
}
