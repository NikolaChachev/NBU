package com.example.nbu.presentation.combat.summary;

import com.example.nbu.mvvm.AbstractViewModel;
import com.example.nbu.service.coroutines.ACoroutineContextProvider;

import dagger.hilt.android.lifecycle.HiltViewModel;
import javax.inject.Inject;

@HiltViewModel
public class SummaryViewModel extends AbstractViewModel {
    @Inject
    public SummaryViewModel(ACoroutineContextProvider provider) {
        super(provider);
    }
}
