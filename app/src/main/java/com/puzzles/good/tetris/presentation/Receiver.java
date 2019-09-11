package com.puzzles.good.tetris.presentation;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;

import com.puzzles.good.R;


public class Receiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        int type = intent.getIntExtra(Messages.TYPE_EXTRA, 0);

        Intent intentToRepeat = new Intent(context, MainActivity.class);
        intentToRepeat.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(context, type, intentToRepeat, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationManager nm = new Messages().getNotificationManager(context);
        Notification notification = buildNotification(context, pendingIntent, nm, type).build();
        nm.notify(type, notification);

    }


    public NotificationCompat.Builder buildNotification(Context context, PendingIntent pendingIntent, NotificationManager nm,int type) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("default",
                    "Daily Notification",
                    NotificationManager.IMPORTANCE_HIGH);
            channel.setDescription("Daily Notification");
            if (nm != null) {
                nm.createNotificationChannel(channel);
            }
        }


        return new NotificationCompat.Builder(context, "default")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(pendingIntent)
                .setContentTitle(getPushMessage(() -> {
                    if (type == 0) return context.getResources().getString(R.string.push);
                    if (type == 1) return context.getResources().getString(R.string.push1);
                    if (type == 2) return context.getResources().getString(R.string.push2);
                    if (type == 3) return context.getResources().getString(R.string.push3);
                    else  return context.getResources().getString(R.string.push);
                })).setAutoCancel(true);
    }
    private String getPushMessage(WhichDay whichDay){
        return whichDay.push();
    }
}
interface WhichDay{
    String push();
}