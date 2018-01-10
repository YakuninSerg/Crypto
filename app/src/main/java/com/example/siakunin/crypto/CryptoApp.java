package com.example.siakunin.crypto;

import android.app.Application;

import com.example.siakunin.crypto.di.components.DaggerNetComponent;
import com.example.siakunin.crypto.di.components.NetComponent;
import com.example.siakunin.crypto.di.modules.AppModule;
import com.example.siakunin.crypto.di.modules.RetrofitModule;
import com.example.siakunin.crypto.rest.Api;

/**
 * Created by siakunin on 09.01.2018.
 */

public class CryptoApp extends Application {
    private final static String BASE_URL = "https://api.coinmarketcap.com/v1/";
    private static NetComponent mNetComponent;


    @Override
    public void onCreate() {
        super.onCreate();
        mNetComponent = DaggerNetComponent.builder()
                .appModule(new AppModule(this))
                .retrofitModule(new RetrofitModule(BASE_URL))
                .build();


    }

    public static NetComponent getNetComponent() {
        return mNetComponent;
    }



}
