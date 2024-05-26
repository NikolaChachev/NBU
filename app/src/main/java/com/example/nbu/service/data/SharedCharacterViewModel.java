package com.example.nbu.service.data;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.nbu.mvvm.AbstractViewModel;
import com.example.nbu.presentation.character.Adventurer;
import com.example.nbu.service.coroutines.ACoroutineContextProvider;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class SharedCharacterViewModel extends AbstractViewModel {

    private final MutableLiveData<Double> characterHealth = new MutableLiveData<>();
    public LiveData<Double> _characterHealth = characterHealth;

    private final Adventurer adventurer;

    @Inject
    public SharedCharacterViewModel(ACoroutineContextProvider provider) {
        super(provider);
        adventurer = Adventurer.getInstance();
        if (adventurer == null) {
            throw new IllegalStateException("Adventurer instance should not be null at this point");
        }
        double currentHealth = (adventurer.getCurrentHealth() / adventurer.getMaxHealth()) * 100;
        characterHealth.setValue(currentHealth);
    }

    public void refreshHealth() {
        adventurer.heal();
    }

    public void updateCurrentHealth() {
        double currentHealth = (adventurer.getCurrentHealth() / adventurer.getMaxHealth()) * 100;
        characterHealth.setValue(currentHealth);
    }


}
