package com.example.siakunin.crypto.mvvp.views;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.example.siakunin.crypto.rest.Crypto;

import java.util.List;

/**
 * Created by siakunin on 09.01.2018.
 */

public interface MainView extends MvpView{
    @StateStrategyType(SkipStrategy.class)
    void showMessage(String message);

    void setCryptoList(List<Crypto> cryptos);
    void deleteCrypto(Crypto crypto);
    void addCrypto (Crypto crypto);


   @StateStrategyType(SkipStrategy.class)
    void showProgressBar();

   @StateStrategyType(SkipStrategy.class)
    void hideProgressBar();


}
