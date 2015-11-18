package com.erikle2.darthvadersmartclock.service;

import com.erikle2.darthvadersmartclock.models.TimeData;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by Erik on 17/11/2015.
 */
public interface GetSmartclockAPI {

    @GET("/")
    void lightSwitch( @Query("led") String string, Callback<TimeData> callback);

    @GET("/posts")
    public Observable<List<TimeData>>
    getPosts();

}
