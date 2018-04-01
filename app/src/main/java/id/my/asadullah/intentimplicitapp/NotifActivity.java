package id.my.asadullah.intentimplicitapp;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.AudioManager;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NotifActivity extends AppCompatActivity {

    @BindView(R.id.notif)
    Button notif;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notif);
        ButterKnife.bind(this);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    @OnClick(R.id.notif)
    public void onViewClicked() {

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.mipmap.ic_launcher_round)
                        .setContentTitle("Notifikasi")
                        .setContentText("Hello !!!")
                        .setAutoCancel(true);

        long [] getar = {500, 500, 500, 500, 500, 500, 500, 500, 500};
        Uri sound = RingtoneManager.getDefaultUri(AudioManager.STREAM_NOTIFICATION);

        //todo -- event klik notif
        Intent resultIntent = new Intent(this, NotifActivity.class);
        PendingIntent pending =
                PendingIntent.getActivity(
                        this,
                        0,
                        resultIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
        //todo -- eksekusi pending intent
        mBuilder.setContentIntent(pending);
        mBuilder.setSound(sound);
        mBuilder.setVibrate(getar);

        int mNotificationId = 001;
        // Gets an instance of the NotificationManager service
        NotificationManager mNotifyMgr = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        // Builds the notification and issues it.
        mNotifyMgr.notify(mNotificationId, mBuilder.build());
    }

}