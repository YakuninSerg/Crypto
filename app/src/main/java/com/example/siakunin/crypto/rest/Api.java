package com.example.siakunin.crypto.rest;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by siakunin on 09.01.2018.
 */

public interface Api {
    @GET("ticker")
    Observable<List<Crypto>> getCurrencyList(@Query("limit")int limit);

    @GET("ticker")
    Observable<List<Crypto>> getCurrencyList();

    @GET("ticker")
    Observable<List<Crypto>> getCurrencyList(@Query("start") int start,@Query("limit")int limit);

    @GET("ticker")
    Observable<List<Crypto>> getCurrencyList(@Query("limit")int limit,@Query("convert")String convert);

    @GET("ticker")
    Observable<List<Crypto>> getCurrencyList(@Query("limit")int limit,@Query("start") int start,
                                                     @Query("convert")String convert);
    @GET("ticker")
    Observable<List<Crypto>> getCurrencyList(@Query("convert")String convert);

    @GET("ticker/{id}/")
    Observable<List<Crypto>> getCurrency(@Path("id")String currency);

    @GET("ticker/{id}/")
    Observable<List<Crypto>> getCurrency(@Path("id")String currency,@Query("convert") String convert);


}
