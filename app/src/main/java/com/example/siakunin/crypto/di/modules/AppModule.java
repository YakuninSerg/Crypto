package com.example.siakunin.crypto.di.modules;

import android.app.Application;

import dagger.Module;

/**
 * Created by siakunin on 09.01.2018.
 */
@Module
public class AppModule {
    Application mApplication;

    public AppModule(Application application) {
        this.mApplication = application;
    }


}
