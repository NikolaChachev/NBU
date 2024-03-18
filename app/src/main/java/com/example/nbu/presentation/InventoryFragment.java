package com.example.nbu.presentation;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.example.nbu.BR;
import com.example.nbu.R;
import com.example.nbu.mvvm.AbstractViewModel;
import com.example.nbu.mvvm.fragment.AbstractFragment;
import com.example.nbu.mvvm.vm.EmptyViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class InventoryFragment extends AbstractFragment {

    EmptyViewModel viewModel;


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.e("test", "inventory fragment called");
        viewModel = new ViewModelProvider(this).get(EmptyViewModel.class);
    }

    @Override
    protected int getViewModelResId() {
        return BR.EmptyVM;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_inventory;
    }

    @SuppressWarnings("unchecked")
    @Override
    protected <T extends AbstractViewModel>Class<T> getViewModelClass() {
        return (Class<T>) EmptyViewModel.class;
    }
}
