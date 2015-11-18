package com.erikle2.darthvadersmartclock.presenters;

/**
 * Created by Erik on 18/11/2015.
 */
public interface ITalkToMainPresenter {
    void setTime(String time);
    void setAlarm(String time);
    void lightsOn();
    void lightOff();
}
