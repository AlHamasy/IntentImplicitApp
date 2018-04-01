package id.my.asadullah.intentimplicitapp;

import android.Manifest;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AudioRecordActivity extends AppCompatActivity {

    @BindView(R.id.btnRecord)
    Button btnRecord;
    @BindView(R.id.btnStopRecord)
    Button btnStopRecord;
    @BindView(R.id.btnPlay)
    Button btnPlay;
    MediaRecorder mRecorder;
    String mFileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_record);
        ButterKnife.bind(this);
        getSupportActionBar().setHomeButtonEnabled(true);

        mFileName = getExternalCacheDir().getAbsolutePath();
        mFileName += "/audiorecordtest.3gp";

        btnRecord.setEnabled(true);
        btnStopRecord.setEnabled(false);
        btnPlay.setEnabled(false);

    }

    @OnClick({R.id.btnRecord, R.id.btnStopRecord, R.id.btnPlay})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnRecord:

                //todo -- check apabila android diatas versi Marshmello
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    requestPermissions(new String [] {Manifest.permission.RECORD_AUDIO}, 1);
                } else {

                    initMediaRecord();

                    try {
                        mRecorder.prepare();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    mRecorder.start();
                }

                btnRecord.setEnabled(false);
                btnStopRecord.setEnabled(true);
                btnPlay.setEnabled(false);

                break;

            case R.id.btnStopRecord:
                //todo -- stop record
                try{
                    mRecorder.stop();
                }
                catch (IllegalStateException e){
                    e.printStackTrace();
                }
                //todo -- di release agar bisa di start kembali
                mRecorder.release();
                btnPlay.setEnabled(true);
                btnRecord.setEnabled(true);
                break;

            case R.id.btnPlay:
                //todo -- untuk putar hasil record
                MediaPlayer mediaPlayer = new MediaPlayer();
                try{
                    //todo -- untuk menentukan source audio yang diputar
                    mediaPlayer.setDataSource(mFileName);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                try {
                    mediaPlayer.prepare();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    mediaPlayer.start();
                }
                catch (IllegalStateException e ){
                    e.printStackTrace();
                }
                break;
        }
    }

    private void initMediaRecord() {
        //TODO -- inisialisasi media recorder
        mRecorder = new MediaRecorder();
        //TODO -- menentukan sumber record
        mRecorder.setAudioSource(MediaRecorder.AudioSource.DEFAULT);
        //TODO -- set tipe format
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        //TODO -- penyimpana file record
        mRecorder.setOutputFile(mFileName);
        //TODO -- di encode untuk play audio
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
    }

}