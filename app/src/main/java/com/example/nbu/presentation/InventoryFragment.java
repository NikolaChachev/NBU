package com.example.nbu.presentation;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nbu.BR;
import com.example.nbu.R;
import com.example.nbu.databinding.FragmentInventoryBinding;
import com.example.nbu.mvvm.fragment.AbstractFragment;
import com.example.nbu.mvvm.vm.EmptyViewModel;
import com.example.nbu.presentation.inventory.CustomAdapter;
import com.example.nbu.presentation.inventory.Inventory;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class InventoryFragment extends AbstractFragment<FragmentInventoryBinding,
        EmptyViewModel> {

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Inventory inventory = Inventory.getInstance();
        CustomAdapter adapter = new CustomAdapter(inventory.getItems());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        binding.inventoryRecycleView.setAdapter(adapter);
        binding.inventoryRecycleView.setLayoutManager(layoutManager);
    }

    @Override
    protected int getViewModelResId() {
        return BR.EmptyVM;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_inventory;
    }

    @Override
    protected Class<EmptyViewModel> getViewModelClass() {
        return EmptyViewModel.class;
    }
}
