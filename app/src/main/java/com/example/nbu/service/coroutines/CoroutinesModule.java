package com.example.nbu.service.coroutines;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import javax.inject.Singleton;

@Module
@InstallIn(SingletonComponent.class)
public class CoroutinesModule {
    @Provides
    @Singleton
    ACoroutineContextProvider provideContextProvider() {
        return  new ContextProvider();
    }
}
