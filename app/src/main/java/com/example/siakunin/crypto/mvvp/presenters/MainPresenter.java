package com.example.siakunin.crypto.mvvp.presenters;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.siakunin.crypto.CryptoApp;
import com.example.siakunin.crypto.mvvp.views.MainView;
import com.example.siakunin.crypto.rest.Api;
import com.example.siakunin.crypto.rest.Crypto;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

/**
 * Created by siakunin on 09.01.2018.
 */
@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> {
    private final String TAG = "Crypto";

    Api mApi = CryptoApp.getNetComponent().getApi();

    public void setStart(int start) {
        this.start = start;
    }

    private int start = 0;
    private int limit = 10;
    private boolean isLoading = false;
    private boolean noError = true;

    public void setConvert(String convert) {
        this.convert = convert;
    }

    private String convert = "USD";

    public boolean isLoading() {
        return isLoading;
    }

    public void setLoading(boolean loading) {
        isLoading = loading;
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        loadMoreTenCryptos();
    }

    public void loadCryptos(){
        mApi.getCurrencyList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Crypto>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        getViewState().showMessage("Идет загрузка");
                    }

                    @Override
                    public void onNext(List<Crypto> cryptos) {
                        getViewState().setCryptoList(cryptos);
                    }

                    @Override
                    public void onError(Throwable e) {
                        getViewState().showMessage("Произошла ошибка");
                        Log.v("Crypto",e.getMessage());
                        Log.v("Crypto",e.getClass().toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    public void loadMoreTenCryptos(){

        if(noError){
            mApi.getCurrencyList(limit,start,convert)
                    .timeout(10, TimeUnit.SECONDS)
                    .delay(1,TimeUnit.SECONDS)
                    .flatMap(cryptos -> Observable.fromIterable(cryptos))
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<Crypto>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                            Log.i(TAG,"Идет загрузка!");
                            setLoading(true);
                            getViewState().showProgressBar();
                        }

                        @Override
                        public void onNext(Crypto crypto) {
                            Log.i(TAG,"Добавляем новый элемент");
                            if(crypto.getError()== null) getViewState().addCrypto(crypto);
                            else {
                                noError = false;
                                Log.e(TAG,crypto.getError());
                                getViewState().showMessage(crypto.getError());
                            }
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.e(TAG,"Произошла ошибка!",e);
                            if (e instanceof TimeoutException) {
                                getViewState().showMessage("Истекло время ожидания!");
                            }
                            else {
                                getViewState().showMessage("Couldn't load data");
                                getViewState().hideProgressBar();
                            }
                            setLoading(false);
                        }

                        @Override
                        public void onComplete() {
                            Log.i(TAG,"Загрузка завершена!");
                            start+=limit;
                            getViewState().hideProgressBar();
                            setLoading(false);
                        }
                    });
        }

    }


}
