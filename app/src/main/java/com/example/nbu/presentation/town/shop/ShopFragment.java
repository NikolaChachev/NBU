package com.example.nbu.presentation.town.shop;

import androidx.databinding.library.baseAdapters.BR;
import com.example.nbu.R;
import com.example.nbu.databinding.ShopFragmentBinding;
import com.example.nbu.mvvm.fragment.AbstractFragment;
import com.example.nbu.mvvm.vm.EmptyViewModel;

public class ShopFragment extends AbstractFragment<ShopFragmentBinding, EmptyViewModel> {

    public static final String SHOP_TYPE_KEY = "shop_type";

    public static final int SHOP_ARMOR_SMITH = 0;
    public static final int SHOP_WEAPON_SMITH = 1;

    @Override
    protected int getViewModelResId() {
        return BR.EmptyVM;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.shop_fragment;
    }

    @Override
    protected Class<EmptyViewModel> getViewModelClass() {
        return EmptyViewModel.class;
    }
}
