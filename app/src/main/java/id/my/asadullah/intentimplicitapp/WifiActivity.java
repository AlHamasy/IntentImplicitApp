package id.my.asadullah.intentimplicitapp;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WifiActivity extends AppCompatActivity {

    @BindView(R.id.switchwifi)
    Switch switchwifi;
    @BindView(R.id.textwifi)
    TextView textwifi;

    //TODO -- deklarasi global
    WifiManager mWifi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wifi);
        ButterKnife.bind(this);
        getSupportActionBar().setHomeButtonEnabled(true);

        mWifi = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);

        if (mWifi.isWifiEnabled()){
            switchwifi.setChecked(true);
        }
        else {
            switchwifi.setChecked(false);
        }

        // TODO -- action listener dari switch
        switchwifi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if (b == true){
                    // Todo -- aktif wifi
                    wifi(true);
                }
                else {
                    wifi(false);
                }
            }
        });

    }

    private void wifi(boolean b) {

        //TODO -- check wifi aktif apa tidak
        if (b == true && !mWifi.isWifiEnabled()){

            //TODO -- mengaktifkan wifi kalau tidak aktif
            mWifi.setWifiEnabled(true);
            Toast.makeText(this, "Wifi aktif", Toast.LENGTH_SHORT).show();
        }

        else if (b == false && mWifi.isWifiEnabled()){

            mWifi.setWifiEnabled(false);
            Toast.makeText(this, "Wifi dimatikan", Toast.LENGTH_SHORT).show();
        }
    }


}