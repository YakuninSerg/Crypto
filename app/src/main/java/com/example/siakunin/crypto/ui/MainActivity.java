package com.example.siakunin.crypto.ui;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.PresenterType;
import com.example.siakunin.crypto.R;
import com.example.siakunin.crypto.mvvp.presenters.MainPresenter;
import com.example.siakunin.crypto.mvvp.views.MainView;
import com.example.siakunin.crypto.rest.Crypto;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends MvpAppCompatActivity implements MainView{

    @InjectPresenter (type = PresenterType.GLOBAL, tag = "1001")
    MainPresenter mPresenter;

    private SharedPreferences sPref;

    private final String SAVED_TEXT = "save_settings";

    List<Crypto> mCryptos = new ArrayList<>();;

    RecyclerView mRecyclerView;
    CryptoAdapter mCryptoAdapter;
    LinearLayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        mCryptoAdapter = new CryptoAdapter(mCryptos);
        loadSettings();

        mRecyclerView = findViewById(R.id.recyclerView);

        layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        ((SimpleItemAnimator) mRecyclerView.getItemAnimator()).setSupportsChangeAnimations(false);

        mRecyclerView.setAdapter(mCryptoAdapter);

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int visibleItemCount = layoutManager.getChildCount();//смотрим сколько элементов на экране
                int totalItemCount = layoutManager.getItemCount();//сколько всего элементов
                int firstVisibleItems = layoutManager.findFirstVisibleItemPosition();//какая позиция первого элемента

                if (!mPresenter.isLoading() &&
                        (visibleItemCount + firstVisibleItems) >= totalItemCount) {
                    mPresenter.loadMoreTenCryptos();
                }
            }
        });

        if(savedInstanceState == null){

            mPresenter.attachView(this);


        }
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setCryptoList(List<Crypto> cryptos) {
        mCryptoAdapter.setCryptos(cryptos);
        mCryptoAdapter.notifyDataSetChanged();
    }

    @Override
    public void deleteCrypto(Crypto crypto) {
        int position = mCryptos.indexOf(crypto);
        mCryptos.remove(crypto);
        mCryptoAdapter.notifyItemRemoved(position);
    }

    @Override
    public void addCrypto(Crypto crypto) {
        mCryptos.add(crypto);
        mCryptoAdapter.notifyItemInserted(mCryptos.size()-1);
    }

    @Override
    public void showProgressBar() {
            System.out.println("Show Progress Bar");
            mCryptoAdapter.startLoading();

    }


    @Override
    public void hideProgressBar() {
        mCryptoAdapter.stopLoading();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        String oldConvert = mCryptoAdapter.getConvert();
        switch (id){
            case R.id.USD :
                mCryptoAdapter.setConvert("USD");
                mPresenter.setConvert("USD");
                break;
            case R.id.RUB :
                mCryptoAdapter.setConvert("RUB");
                mPresenter.setConvert("RUB");
                break;
            case R.id.EUR :
                mCryptoAdapter.setConvert("EUR");
                mPresenter.setConvert("EUR");
                break;
            default: return super.onOptionsItemSelected(item);
        }
        saveSettings();
        if(!mCryptoAdapter.getConvert().equals(oldConvert)){
            reloadCrypto();
        }
        return true;

    }

    public void saveSettings(){
        sPref = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        ed.putString(SAVED_TEXT, mCryptoAdapter.getConvert());
        ed.apply();

    }

    public void loadSettings(){
        sPref = getPreferences(MODE_PRIVATE);
        String savedText = sPref.getString(SAVED_TEXT, "");
        if (!"".equals(savedText)) {
            mPresenter.setConvert(savedText);
            mCryptoAdapter.setConvert(savedText);
        }
    }


    public void reloadCrypto() {
        mCryptos.clear();
        setCryptoList(mCryptos);
        mPresenter.setStart(0);
        mPresenter.loadMoreTenCryptos();
    }
}
