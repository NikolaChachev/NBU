package com.example.nbu.mvvm.vm;

import com.example.nbu.mvvm.AbstractViewModel;
import com.example.nbu.service.coroutines.ACoroutineContextProvider;
import dagger.hilt.android.lifecycle.HiltViewModel;
import javax.inject.Inject;

@HiltViewModel
public class EmptyViewModel extends AbstractViewModel {

    @Inject
    public EmptyViewModel(ACoroutineContextProvider provider){
        super(provider);

    }

}
