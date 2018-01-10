package com.example.siakunin.crypto.di.components;

import com.example.siakunin.crypto.di.modules.AppModule;
import com.example.siakunin.crypto.di.modules.RetrofitModule;
import com.example.siakunin.crypto.mvvp.presenters.MainPresenter;
import com.example.siakunin.crypto.rest.Api;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by siakunin on 09.01.2018.
 */

@Singleton
@Component(modules = {RetrofitModule.class, AppModule.class})
public interface NetComponent {
    void inject(MainPresenter mainPresenter);
    Api getApi();
}
