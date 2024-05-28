package com.example.nbu.presentation.combat.summary;

import com.example.nbu.mvvm.AbstractViewModel;
import com.example.nbu.service.coroutines.ACoroutineContextProvider;
import dagger.hilt.android.lifecycle.HiltViewModel;
import javax.inject.Inject;

@HiltViewModel
public class SummaryViewModel extends AbstractViewModel {

    private int goldReward = 0;
    private int expReward = 0;

    public void saveRewards(int gold, int exp){
        goldReward = gold;
        expReward = exp;
    }

    public int getGoldReward(){
        return goldReward;
    }
    public int getExpReward(){
        return expReward;
    }

    @Inject
    public SummaryViewModel(ACoroutineContextProvider provider) {
        super(provider);
    }
}
