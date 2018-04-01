package id.my.asadullah.intentimplicitapp;

import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AudioManagerActivity extends AppCompatActivity {

    @BindView(R.id.btnRing)
    Button btnRing;
    @BindView(R.id.btnSilent)
    Button btnSilent;
    @BindView(R.id.btnVibrate)
    Button btnVibrate;
    @BindView(R.id.btnMute)
    Button btnMute;
    @BindView(R.id.btnMode)
    Button btnMode;

    AudioManager audio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_manager);
        ButterKnife.bind(this);
        getSupportActionBar().setHomeButtonEnabled(true);

        audio = (AudioManager) getApplicationContext().getSystemService(Context.AUDIO_SERVICE);
    }

    @OnClick({R.id.btnRing, R.id.btnSilent, R.id.btnVibrate, R.id.btnMute, R.id.btnMode})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnRing:
                audio.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
                break;
            case R.id.btnSilent:
                audio.setRingerMode(AudioManager.RINGER_MODE_SILENT);
                break;
            case R.id.btnVibrate:
                audio.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
                break;
            case R.id.btnMute:
                audio.setRingerMode(AudioManager.RINGER_MODE_SILENT);
                break;
            case R.id.btnMode:

                audio.getRingerMode();
                String status = null;

                if (audio.getRingerMode() == AudioManager.RINGER_MODE_NORMAL){
                    //Toast.makeText(this, "Dalam mode normal", Toast.LENGTH_SHORT).show();
                    status = "Mode normal";
                }
                else if (audio.getRingerMode() == AudioManager.RINGER_MODE_SILENT){
                    //Toast.makeText(this, "Dalam mode silent", Toast.LENGTH_SHORT).show();
                    status = "Mode Silent";
                }
                else if (audio.getRingerMode() == AudioManager.RINGER_MODE_VIBRATE){
                    //Toast.makeText(this, "Dalam mode getar", Toast.LENGTH_SHORT).show();
                    status = "Mode getar";
                }
                Toast.makeText(this, status, Toast.LENGTH_SHORT).show();
                break;
        }
    }

}
