package com.erikle2.darthvadersmartclock.service;

import android.util.Log;

import com.erikle2.darthvadersmartclock.models.TimeData;


import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by Erik on 17/11/2015.
 */
public class NetworkService {
    protected final String TAG = getClass().getSimpleName();
    protected Retrofit mRestAdapter;
     VaderAPI mApi;
    static final String FORUM_SERVER_URL="http://192.168.1.50";

    public NetworkService() {

        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(FORUM_SERVER_URL)
                .build();


        mApi = retrofit.create(VaderAPI.class);
    }

    public VaderAPI getAPI(){
        return mApi;
    }


    public interface VaderAPI {

        @GET("/")
        Observable<TimeData>
        lightSwitch( @Query("led") String string);

        @GET("/")
        Observable
        setAlarm(@Query("alarm") String time);

        @GET("/")
        Observable
        setAlarsetTime(@Query("alarm") String time);


    }
}
