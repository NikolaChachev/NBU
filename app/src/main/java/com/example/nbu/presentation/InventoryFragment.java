package com.example.nbu.presentation;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.nbu.BR;
import com.example.nbu.R;
import com.example.nbu.databinding.FragmentInventoryBinding;
import com.example.nbu.mvvm.fragment.AbstractFragment;
import com.example.nbu.mvvm.vm.EmptyViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class InventoryFragment extends AbstractFragment<FragmentInventoryBinding,
        EmptyViewModel> {

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.e("test", "inventory fragment called");

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


//    @Override
//    protected <VM extends AbstractViewModel>Class<VM> getViewModelClass() {
//        return (Class<T>) EmptyViewModel.class;
//    }
}
