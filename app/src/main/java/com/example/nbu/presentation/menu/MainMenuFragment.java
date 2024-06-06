package com.example.nbu.presentation.menu;

import android.os.Bundle;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.library.baseAdapters.BR;
import androidx.lifecycle.ViewModelProvider;
import com.example.nbu.R;
import com.example.nbu.databinding.FragmentMainMenuBinding;
import com.example.nbu.mvvm.fragment.AbstractFragment;
import com.example.nbu.mvvm.vm.EmptyViewModel;
import com.example.nbu.presentation.combat.encounter.EncounterFragment;
import com.example.nbu.service.data.SharedCharacterViewModel;
import com.example.nbu.service.prefs.NbuSharedPrefs;
import dagger.hilt.android.AndroidEntryPoint;
import javax.inject.Inject;

@AndroidEntryPoint
public class MainMenuFragment extends AbstractFragment<FragmentMainMenuBinding, EmptyViewModel> {

    @Inject
    NbuSharedPrefs sharedPrefs;

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.mainMenuQuitButton.setOnClickListener(v -> {
            requireActivity().finishAffinity();
            System.exit(0);
        });
        SharedCharacterViewModel sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedCharacterViewModel.class);

        sharedViewModel.updateCurrentBackground(R.drawable.start_menu);
        binding.mainMenuNewGameButton.setOnClickListener(v -> {
            sharedViewModel.notifyGameStarted();
            navigateToView(EncounterFragment.class, null);
        });
        if (sharedPrefs.containsSavedGame()) {
            binding.mainMenuContinueGameButton.setEnabled(true);
            binding.mainMenuContinueGameButton.setTextColor(requireContext().getColor(R.color.yellow));
            binding.mainMenuContinueGameButton.setOnClickListener(v -> {
                sharedPrefs.loadGameProgress();
                sharedViewModel.notifyGameStarted();
                navigateToView(EncounterFragment.class, null);
            });
        } else {
            binding.mainMenuContinueGameButton.setEnabled(false);
            binding.mainMenuContinueGameButton.setTextColor(requireContext().getColor(R.color.grey_medium_transparency));
        }
    }

    @Override
    protected int getViewModelResId() {
        return BR.EmptyVM;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_main_menu;
    }

    @Override
    protected Class<EmptyViewModel> getViewModelClass() {
        return EmptyViewModel.class;
    }
}
