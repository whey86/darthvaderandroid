package com.erikle2.darthvadersmartclock.views;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.ImageView;
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
    @Bind(R.id.ivDarthVader)
    ImageView vaderHead;
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

                    presenter.lightsOn();
                } else {

                    presenter.lightOff();
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
}
