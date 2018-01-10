package com.example.siakunin.crypto.rest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by siakunin on 09.01.2018.
 */

public class Crypto {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("symbol")
    @Expose
    private String symbol;
    @SerializedName("rank")
    @Expose
    private Integer rank;
    @SerializedName("price_usd")
    @Expose
    private Double priceUsd;
    @SerializedName("price_btc")
    @Expose
    private Double priceBtc;
    @SerializedName("24h_volume_usd")
    @Expose
    private Double _24hVolumeUsd;
    @SerializedName("market_cap_usd")
    @Expose
    private Double marketCapUsd;
    @SerializedName("available_supply")
    @Expose
    private Double availableSupply;
    @SerializedName("total_supply")
    @Expose
    private Double totalSupply;
    @SerializedName("percent_change_1h")
    @Expose
    private Double percentChange1h;
    @SerializedName("percent_change_24h")
    @Expose
    private Double percentChange24h;
    @SerializedName("percent_change_7d")
    @Expose
    private Double percentChange7d;
    @SerializedName("last_updated")
    @Expose
    private long lastUpdated;
    @SerializedName("error")
    @Expose
    private String error;

    //For EUR
    @SerializedName("price_eur")
    @Expose
    public Double priceEur;
    @SerializedName("24h_volume_eur")
    @Expose
    public Double _24hVolumeEur;
    @SerializedName("market_cap_eur")
    @Expose
    public Double marketCapEur;

    //For RUB

    @SerializedName("price_rub")
    @Expose
    public Double priceRub;
    @SerializedName("24h_volume_rub")
    @Expose
    public Double _24hVolumeRub;
    @SerializedName("market_cap_rub")
    @Expose
    public Double marketCapRub;

    public Double getPriceEur() {
        return priceEur;
    }

    public void setPriceEur(Double priceEur) {
        this.priceEur = priceEur;
    }

    public Double get_24hVolumeEur() {
        return _24hVolumeEur;
    }

    public void set_24hVolumeEur(Double _24hVolumeEur) {
        this._24hVolumeEur = _24hVolumeEur;
    }

    public Double getMarketCapEur() {
        return marketCapEur;
    }

    public void setMarketCapEur(Double marketCapEur) {
        this.marketCapEur = marketCapEur;
    }

    public Double getPriceRub() {
        return priceRub;
    }

    public void setPriceRub(Double priceRub) {
        this.priceRub = priceRub;
    }

    public Double get_24hVolumeRub() {
        return _24hVolumeRub;
    }

    public void set_24hVolumeRub(Double _24hVolumeRub) {
        this._24hVolumeRub = _24hVolumeRub;
    }

    public Double getMarketCapRub() {
        return marketCapRub;
    }

    public void setMarketCapRub(Double marketCapRub) {
        this.marketCapRub = marketCapRub;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Double getPriceUsd() {
        return priceUsd;
    }

    public void setPriceUsd(Double priceUsd) {
        this.priceUsd = priceUsd;
    }

    public Double getPriceBtc() {
        return priceBtc;
    }

    public void setPriceBtc(Double priceBtc) {
        this.priceBtc = priceBtc;
    }

    public Double get_24hVolumeUsd() {
        return _24hVolumeUsd;
    }

    public void set_24hVolumeUsd(Double _24hVolumeUsd) {
        this._24hVolumeUsd = _24hVolumeUsd;
    }

    public Double getMarketCapUsd() {
        return marketCapUsd;
    }

    public void setMarketCapUsd(Double marketCapUsd) {
        this.marketCapUsd = marketCapUsd;
    }

    public Double getAvailableSupply() {
        return availableSupply;
    }

    public void setAvailableSupply(Double availableSupply) {
        this.availableSupply = availableSupply;
    }

    public Double getTotalSupply() {
        return totalSupply;
    }

    public void setTotalSupply(Double totalSupply) {
        this.totalSupply = totalSupply;
    }

    public Double getPercentChange1h() {
        return percentChange1h;
    }

    public void setPercentChange1h(Double percentChange1h) {
        this.percentChange1h = percentChange1h;
    }

    public Double getPercentChange24h() {
        return percentChange24h;
    }

    public void setPercentChange24h(Double percentChange24h) {
        this.percentChange24h = percentChange24h;
    }

    public Double getPercentChange7d() {
        return percentChange7d;
    }

    public void setPercentChange7d(Double percentChange7d) {
        this.percentChange7d = percentChange7d;
    }

    public long getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(long lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}
