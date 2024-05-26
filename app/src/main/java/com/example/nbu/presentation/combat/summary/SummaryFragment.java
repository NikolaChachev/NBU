package com.example.nbu.presentation.combat.summary;

import com.example.nbu.BR;
import com.example.nbu.R;
import com.example.nbu.databinding.FragmentSummaryBinding;
import com.example.nbu.mvvm.fragment.AbstractFragment;

public class SummaryFragment extends AbstractFragment<FragmentSummaryBinding, SummaryViewModel> {
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
