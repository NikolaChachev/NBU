package com.example.nbu.service.coroutines;

import androidx.annotation.NonNull;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;

public class ContextProvider extends ACoroutineContextProvider {
     public ContextProvider(){
         Main = (CoroutineContext) Dispatchers.getMain();
         IO = (CoroutineContext) Dispatchers.getIO();
         Default = (CoroutineContext) Dispatchers.getDefault();
         backgroundScope = () -> Default;
     }

}
