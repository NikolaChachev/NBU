package com.example.nbu.service.data;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.nbu.mvvm.AbstractViewModel;
import com.example.nbu.presentation.character.Adventurer;
import com.example.nbu.service.coroutines.ACoroutineContextProvider;
import dagger.hilt.android.lifecycle.HiltViewModel;
import javax.inject.Inject;

@HiltViewModel
public class SharedCharacterViewModel extends AbstractViewModel {

    private final MutableLiveData<Double> characterHealth = new MutableLiveData<>();

    public LiveData<Double> _characterHealth = characterHealth;

    private final MutableLiveData<Boolean> gameStarted = new MutableLiveData<>();

    public LiveData<Boolean> _gameStarted = gameStarted;

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

    public void notifyGameStarted() {
        gameStarted.setValue(true);
    }

    public void updateCurrentHealth() {
        double currentHealth = (adventurer.getCurrentHealth() / adventurer.getMaxHealth()) * 100;
        characterHealth.setValue(currentHealth);
    }


}
