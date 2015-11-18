package com.erikle2.darthvadersmartclock.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.erikle2.darthvadersmartclock.R;
import com.erikle2.darthvadersmartclock.models.TimeData;
import com.erikle2.darthvadersmartclock.presenters.MainPresenter;
import com.erikle2.darthvadersmartclock.service.NetworkService;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.Callback;

public class MainActivity extends AppCompatActivity implements ITalkToMainActivity {

    @Bind(R.id.switchLight)
    Switch lightSwitch;
    private MainPresenter presenter;


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
                    Log.d("Switch", " ON");
                    presenter.lightsOn();
                } else {
                    Log.d("Switch", " OFF");
                    presenter.lightOff();
                }
            }
        });




    }


    @Override
    public void lightOn() {
        Toast.makeText(this, "Light is ON", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void lightOff() {
        Toast.makeText(this, "Light is OFF", Toast.LENGTH_LONG).show();

    }

    @Override
    public void setTime() {

    }
}
