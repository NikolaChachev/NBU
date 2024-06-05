package com.example.nbu.presentation.town;

import android.os.Bundle;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import com.example.nbu.BR;
import com.example.nbu.R;
import com.example.nbu.databinding.FragmentTownCenterBinding;
import com.example.nbu.mvvm.fragment.AbstractFragment;
import com.example.nbu.mvvm.vm.EmptyViewModel;
import com.example.nbu.presentation.combat.encounter.EncounterFragment;
import com.example.nbu.presentation.town.shop.ShopFragment;
import com.example.nbu.presentation.town.tavern.TavernFragment;
import com.example.nbu.service.data.SharedCharacterViewModel;

public class TownCenterFragment extends AbstractFragment<FragmentTownCenterBinding, EmptyViewModel> {


    @Override
    public void onViewCreated(@NonNull final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.townCenterTavernButton.setOnClickListener(v -> {
            navigateToView(TavernFragment.class, null);
        });

        binding.townCenterArmorSmithButton.setOnClickListener(v -> {
            Bundle args = new Bundle();
            args.putInt(ShopFragment.SHOP_TYPE_KEY, ShopFragment.SHOP_ARMOR_SMITH);
            navigateToView(ShopFragment.class, args);
        });

        binding.townCenterWeaponSmithButton.setOnClickListener(v -> {
            Bundle args = new Bundle();
            args.putInt(ShopFragment.SHOP_TYPE_KEY, ShopFragment.SHOP_WEAPON_SMITH);
            navigateToView(ShopFragment.class, args);
        });

        binding.townCenterExitTownButton.setOnClickListener(v -> {
            navigateToView(EncounterFragment.class, null);
        });
        SharedCharacterViewModel sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedCharacterViewModel.class);
        sharedViewModel.updateCurrentBackground(R.drawable.town_art);
    }

    @Override
    protected int getViewModelResId() {
        return BR.EmptyVM;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_town_center;
    }

    @Override
    protected Class<EmptyViewModel> getViewModelClass() {
        return EmptyViewModel.class;
    }
}
