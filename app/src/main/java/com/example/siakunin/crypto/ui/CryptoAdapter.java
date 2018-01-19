package com.example.siakunin.crypto.ui;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.siakunin.crypto.R;
import com.example.siakunin.crypto.rest.Crypto;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by siakunin on 09.01.2018.
 */

public class CryptoAdapter extends RecyclerView.Adapter <RecyclerView.ViewHolder> {
    private final String TAG = "Crypto";
    private int progressBarPosition;
    private final int PROGRESS_TYPE = 0;
    private final int ITEM_TYPE = 1;
    private List<Crypto> mCryptos;

    private String convert;

    public String getConvert() {
        return convert;
    }

    public void setConvert(String convert) {
        this.convert = convert;
    }

    public CryptoAdapter(List<Crypto> cryptos){
        this.mCryptos = cryptos;
    }

    public void setCryptos(List<Crypto> cryptos) {
        mCryptos = cryptos;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_TYPE){
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
            return new ItemViewHolder(v);}
        else {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.progress_layout, parent, false);
            return new LoadingViewHolder(v);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder abstractHolder, int position) {
        if (abstractHolder instanceof ItemViewHolder) {
            ItemViewHolder holder = (ItemViewHolder) abstractHolder;
            Crypto crypto = mCryptos.get(position);
            holder.cryptoNameTv.setText(crypto.getName());

            Double _1hChange = crypto.getPercentChange1h();
            Double _24hChange = crypto.getPercentChange24h();
            Double _7dChange = crypto.getPercentChange7d();
            Double price = 0.0;
            if ("USD".equals(convert)){
                price = crypto.getPriceUsd();
                if(price!=null){
                price = BigDecimal.valueOf(price).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
                holder.usdTv.setText("USD");
                }
            }

            if ("RUB".equals(convert)){
                price = crypto.getPriceRub();
                if(price!=null){
                price = BigDecimal.valueOf(price).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
                holder.usdTv.setText("RUB");
                }
            }
            if ("EUR".equals(convert)){
                price = crypto.getPriceEur();
                if(price!=null) {
                    price = BigDecimal.valueOf(price).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                    holder.usdTv.setText("EUR");
                }
            }


            if (_1hChange!=null && _1hChange < 0) holder._1hChangeTv.setTextColor(Color.RED);
            else holder._1hChangeTv.setTextColor(Color.GREEN);

            if (_24hChange!=null && _24hChange < 0) holder._24hChangeTv.setTextColor(Color.RED);
            else holder._24hChangeTv.setTextColor(Color.GREEN);

            if (_7dChange!=null &&_7dChange < 0) holder._7dChangeTv.setTextColor(Color.RED);
            else holder._7dChangeTv.setTextColor(Color.GREEN);

            holder._1hChangeTv.setText(String.format("%s%%", _1hChange));
            holder._24hChangeTv.setText(String.format("%s%%", _24hChange));
            holder._7dChangeTv.setText(String.format("%s%%", _7dChange));

            SimpleDateFormat format = new SimpleDateFormat("hh:mm dd.MM.yy");
            String date = format.format(new Date(crypto.getLastUpdated() * 1000));
            holder.lastUpdatedTv.setText(date);
            if(price!=null) holder.priceTv.setText(Double.toString(price));
            else holder.priceTv.setText("ERR");
        }

        else if(abstractHolder instanceof LoadingViewHolder){
            LoadingViewHolder loadingViewHolder = (LoadingViewHolder) abstractHolder;
            loadingViewHolder.progressBar.setVisibility(ProgressBar.VISIBLE);
        }

    }

    @Override
    public int getItemCount() {
        return mCryptos.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder{
        TextView cryptoNameTv;
        TextView priceTv;
        TextView usdTv;
        TextView _1hChangeTv;
        TextView _24hChangeTv;
        TextView _7dChangeTv;
        TextView lastUpdatedTv;

        public ItemViewHolder(View itemView) {
            super(itemView);
            cryptoNameTv = itemView.findViewById(R.id.crypto_name_tv);
            priceTv = itemView.findViewById(R.id.price_tv);
            usdTv = itemView.findViewById(R.id.usd_tv);
            _1hChangeTv = itemView.findViewById(R.id._1h_change_tv);
            _24hChangeTv = itemView.findViewById(R.id._24h_change_tv);
            _7dChangeTv = itemView.findViewById(R.id._7d_change_view);
            lastUpdatedTv = itemView.findViewById(R.id.last_updated_tv);
        }
    }

    class LoadingViewHolder extends RecyclerView.ViewHolder {
        ProgressBar progressBar;
        public LoadingViewHolder(View itemView) {
            super(itemView);
            progressBar = itemView.findViewById(R.id.progressBar);
        }
    }


    @Override public int getItemViewType(int position) {
        return mCryptos.get(position) == null ? PROGRESS_TYPE : ITEM_TYPE;
    }

    public void startLoading(){
        mCryptos.add(null);
        progressBarPosition = mCryptos.size() - 1;
        notifyItemInserted(progressBarPosition);
    }

    public void stopLoading(){

      mCryptos.remove(progressBarPosition);
      notifyItemRemoved(progressBarPosition);
    }


}
