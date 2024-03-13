package com.example.nbu.mvvm;

import androidx.annotation.NonNull;
import com.example.nbu.mvvm.vm.BaseViewModel;
import com.example.nbu.service.coroutines.ACoroutineContextProvider;
import dagger.hilt.android.lifecycle.HiltViewModel;
import javax.inject.Inject;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.CoroutineScope;

@HiltViewModel
public class AbstractViewModel extends BaseViewModel implements CoroutineScope {

    ACoroutineContextProvider contextProvider;

    @Inject
    public AbstractViewModel(ACoroutineContextProvider provider){
        contextProvider = provider;
    }
    @NonNull
    @Override
    public CoroutineContext getCoroutineContext() {
        return contextProvider.getMain();
    }

    protected CoroutineScope getBackgroundScope(){
        return contextProvider.getBackgroundScope();
    }
}
