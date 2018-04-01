package id.my.asadullah.intentimplicitapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btnbluetooth)
    Button btnbluetooth;
    @BindView(R.id.btnwifi)
    Button btnwifi;
    @BindView(R.id.btnaudiomanager)
    Button btnaudiomanager;
    @BindView(R.id.btnaudiorecord)
    Button btnaudiorecord;
    @BindView(R.id.btntexttospeech)
    Button btntexttospeech;
    @BindView(R.id.btnnotif)
    Button btnnotif;
    @BindView(R.id.btnadmob)
    Button btnadmob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btnbluetooth, R.id.btnwifi, R.id.btnaudiomanager, R.id.btnaudiorecord, R.id.btntexttospeech, R.id.btnnotif})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnbluetooth:
                startActivity(new Intent(this, BluetoothActivity.class));
                break;
            case R.id.btnwifi:
                startActivity(new Intent(this, WifiActivity.class));
                break;
            case R.id.btnaudiomanager:
                startActivity(new Intent(this, AudioManagerActivity.class));
                break;
            case R.id.btnaudiorecord:
                startActivity(new Intent(this, AudioRecordActivity.class));
                break;
            case R.id.btntexttospeech:
                startActivity(new Intent(this, TTSActivity.class));
                break;
            case R.id.btnnotif:
                startActivity(new Intent(this, NotifActivity.class));
                break;
        }
    }

    @OnClick(R.id.btnadmob)
    public void onViewClicked() {
        startActivity(new Intent(this, AdmobActivity.class));
    }
}