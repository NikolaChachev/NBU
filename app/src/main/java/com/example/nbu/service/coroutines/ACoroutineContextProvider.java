package com.example.nbu.service.coroutines;

import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.CoroutineScope;

public class ACoroutineContextProvider {
    CoroutineContext Main;
    CoroutineContext IO;
    CoroutineContext Default;
    CoroutineScope backgroundScope;


    public CoroutineContext getMain() {
        return Main;
    }

    public CoroutineContext getIO() {
        return IO;
    }

    public CoroutineContext getDefault() {
        return Default;
    }

    public CoroutineScope getBackgroundScope() {
        return backgroundScope;
    }

}
