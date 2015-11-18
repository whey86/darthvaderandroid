package com.erikle2.darthvadersmartclock.presenters;


import com.erikle2.darthvadersmartclock.models.TimeData;
import com.erikle2.darthvadersmartclock.service.NetworkService;
import com.erikle2.darthvadersmartclock.views.ITalkToMainActivity;

import java.sql.Time;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Erik on 18/11/2015.
 */
public class MainPresenter implements ITalkToMainPresenter {

     NetworkService mService;
    ITalkToMainActivity mView;
    public MainPresenter(ITalkToMainActivity view, NetworkService service) {
        mView = view;
        mService = service;
    }

    @Override
    public void setTime() {

    }

    @Override
    public void setAlarm() {

    }

    @Override
    public void lightsOn() {

        Observable<TimeData> light = mService.getAPI().lightSwitch("1");

                    light.subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer() {

                        @Override
                        public void onCompleted() {
                            mView.lightOn();
                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onNext(Object o) {

                        }
                    });

    }

    @Override
    public void lightOff() {
        mService.getAPI()
                .lightSwitch("2")
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer(){

                    @Override
                    public void onCompleted() {
                        mView.lightOff();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Object o) {

                    }
                });
    }
}
