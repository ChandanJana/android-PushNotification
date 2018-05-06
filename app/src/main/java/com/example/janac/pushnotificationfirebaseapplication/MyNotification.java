package com.example.janac.pushnotificationfirebaseapplication;

import android.annotation.SuppressLint;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

public class MyNotification {

    Context context;
    static MyNotification myNotification;

    MyNotification(Context context){
        this.context = context;

    }

    public static synchronized MyNotification getInstance(Context context){

        if (myNotification == null){
            myNotification = new MyNotification(context);
        }
        return myNotification;
    }

    public void displayNotification(String title, String body){

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, Constants.CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_info_black_24dp)
                .setContentTitle(title)
                .setContentText(body);

        Intent intent = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent);
        @SuppressLint("ServiceCast")
        NotificationManager myNotification = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        if (myNotification != null){
            myNotification.notify(1, builder.build());
        }
    }
}
