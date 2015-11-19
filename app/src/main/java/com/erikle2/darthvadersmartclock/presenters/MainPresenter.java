package com.erikle2.darthvadersmartclock.presenters;


import com.erikle2.darthvadersmartclock.models.TimeData;
import com.erikle2.darthvadersmartclock.service.NetworkService;
import com.erikle2.darthvadersmartclock.views.ITalkToMainActivity;

import java.sql.Time;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
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
    public void setTime(final String time) {

    }

    @Override
    public void setAlarm(String time) {
            mService.getAPI()
                    .setAlarm(time)
                    .doOnCompleted(new Action0() {
                        @Override
                        public void call() {

                        }
                    });
    }

    @Override
    public void lightsOn() {
        mView.lightOn();
        mService.getAPI()
                .lightSwitch("1")
                .take(1)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<TimeData>() {

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(TimeData timeData) {

                    }
                });

    }


    @Override
    public void lightOff() {
        mView.lightOff();
        mService.getAPI()
                .lightSwitch("2")
                .take(1)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<TimeData>(){

                    @Override
                    public void onCompleted() {
                        mView.lightOff();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(TimeData timeData) {

                    }
                });

    }

    @Override
    public void alarmStatus(Boolean status) {

    }
}
