package com.viluvasa.fcm;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Binder;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

/**
 * Created by ASA on 12/14/2016.
 */

public class BackgroundService extends Service {
    private NotificationManager mNM;
    private final IBinder mBinder = new LocalBinder();
    private String newtext;
    NotificationManager notificationManager;

    public class LocalBinder extends Binder {
        BackgroundService getService() {
            return BackgroundService.this;
        }
    }

    @Override
    public void onCreate() {
        //Log.e("Service", "Service Mulai");
        mNM = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);

        newtext = "BackGroundApp Service Running";

//        Notification notification = new Notification(R.mipmap.ic_launcher, newtext,System.currentTimeMillis());
//        PendingIntent contentIntent = PendingIntent.getActivity(BackgroundService.this, 0, new Intent(BackgroundService.this, MainActivity.class), 0);
//        notification.setLatestEventInfo(BackgroundService.this,"BackgroundAppExample", newtext, contentIntent);
//        mNM.notify(R.string.local_service_started, notification);
//        notificationIntent = new Intent(this, MainActivity.class);
        sendNotification("Background Sample App",newtext);
        //showNotification();
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }
    public void onDestroy() {
        //mNM.cancel(R.string.local_service_started);
        stopSelf();
    }
    private void showNotification() {
//        CharSequence text = getText(R.string.local_service_started);
//
//        Notification notification = new Notification(R.mipmap.ic_launcher, text, System.currentTimeMillis());
//        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,new Intent(this, BackgroundAppExample.class), 0);
//        notification.setLatestEventInfo(this, "BackgroundAppExample",newtext, contentIntent);
//        notification.flags = Notification.FLAG_ONGOING_EVENT | Notification.FLAG_NO_CLEAR;
//        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
//
//        mNM.notify(R.string.local_service_started, notification);
    }
    private void sendNotification(String Title,String messageBody) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent,
                PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(Title)
                .setContentText(messageBody)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0, notificationBuilder.build());
        Log.e("Service Notification", "Service Started");
    }
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }
}