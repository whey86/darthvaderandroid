package com.erikle2.darthvadersmartclock.views;

import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextClock;

import com.erikle2.darthvadersmartclock.R;
import com.erikle2.darthvadersmartclock.presenters.MainPresenter;
import com.erikle2.darthvadersmartclock.service.NetworkService;
import com.wdullaer.materialdatetimepicker.time.RadialPickerLayout;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.util.Calendar;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements ITalkToMainActivity {

    @Bind(R.id.switchAlarm)
    Switch alarmSwitch;
    @Bind(R.id.switchLight)
    Switch lightSwitch;
    @Bind(R.id.ivDarthVader)
    ImageView vaderHead;
    @Bind(R.id.btnAlarm)
    Button btnAlarm;
    @Bind(R.id.textClock)
    TextClock tcAlarmtime;
    private MainPresenter presenter;
    Boolean alarmOn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        presenter = new MainPresenter(this, new NetworkService());
        lightSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    presenter.lightsOn();
                } else {

                    presenter.lightOff();
                }
            }
        });

        alarmSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {

                    alarmOn = true;
                } else {

                    alarmOn = false;
                }
            }
        });
    }
    @Override
    public void lightOn() {

        Log.d("Switch", " ON");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            vaderHead.setImageDrawable(getResources().getDrawable(R.drawable.dv2));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            vaderHead.setImageDrawable(getResources().getDrawable(R.drawable.dv2,null));
        }
    }

    @Override
    public void lightOff() {
        Log.d("Switch", " OFF");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            vaderHead.setImageDrawable(getResources().getDrawable(R.drawable.dv));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            vaderHead.setImageDrawable(getResources().getDrawable(R.drawable.dv, null));
        }

    }

    @Override
    public void setTime(String time) {

    }
    @OnClick(R.id.btnAlarm)
    public void setAlarm(){

        Calendar now = Calendar.getInstance();
        TimePickerDialog tpd = TimePickerDialog.newInstance(
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(RadialPickerLayout view, int hourOfDay, int minute, int second) {
                        tcAlarmtime.setText(hourOfDay + ":" +minute);
                      //  presenter.setAlarm(hourOfDay + "%3A" +  minute );
                        Log.d("TIMEPICKER", "TIME SET");
                    }
                },
                now.get(Calendar.HOUR),
                now.get(Calendar.MINUTE),
                true
        );
        tpd.setThemeDark(true);
        tpd.setAccentColor(Color.RED);
        tpd.show(getFragmentManager(), "Datepickerdialog");
    }
}
