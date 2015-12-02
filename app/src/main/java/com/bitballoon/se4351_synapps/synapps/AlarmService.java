package com.bitballoon.se4351_synapps.synapps;

import android.app.*;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.v7.app.NotificationCompat;
import android.widget.Toast;

import java.security.Provider;

/**
 * Created by Bontavy on 12/1/2015.
 */
public class AlarmService extends Service
{

    @Override
    public IBinder onBind(Intent arg0)
    {
        return null;
    }

    @Override
    public void onCreate()
    {
        super.onCreate();
    }

    @Override
    public void onStart(Intent intent, int startId)
    {
        super.onStart(intent, startId);
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        android.app.Notification notification = new NotificationCompat.Builder(this)
                .setTicker("Notification")
                .setContentTitle(intent.getStringExtra("date"))
                .setContentText(intent.getStringExtra("hour") + ":" + intent.getStringExtra("minute") + " " + intent.getStringExtra("ampm")
                        + " - " + intent.getStringExtra("notification"))
                .setSmallIcon(R.mipmap.daily_routine)
                .setContentIntent(PendingIntent.getActivity(this, 0, new Intent(this, DisplayNotificationsActivity.class), PendingIntent.FLAG_UPDATE_CURRENT))
                .setAutoCancel(true)
                .build();

        notificationManager.notify(0, notification);
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
    }
}
