package com.dvilson.navigationcomponent;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.navigation.NavDeepLinkBuilder;

import java.util.Random;

class NotificationHelpler extends ContextWrapper {
    public NotificationHelpler(Context base) {
        super(base);

          if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){

              createNotificationChannel();
          }
    }

    private final String CHANNEL_NAME = "HIGH PRIORITY CHANNEL";
    private final String CHANNEL_ID = "com.dvilson.navigationcomponent"+ CHANNEL_NAME;

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void createNotificationChannel(){


        NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID,CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH);
        notificationChannel.enableLights(true);
        notificationChannel.enableVibration(true);
        notificationChannel.setDescription("Bonsoir ceci est une notification de haute priorité");
        notificationChannel.setLightColor(Color.RED);
        notificationChannel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);

        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.createNotificationChannel(notificationChannel);

    }

    public void sendHighPriorityNotification(String title,String body){

        //Intent intent = new Intent(this,fragmentClassName );
        //PendingIntent pendingIntent = PendingIntent.getActivity(this,33,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        PendingIntent pendingIntent = new NavDeepLinkBuilder(this)
                .setGraph(R.navigation.nav_graph)
                .setDestination(R.id.homeFragment)
                .createPendingIntent();


        Notification notification = new NotificationCompat.Builder(this,CHANNEL_ID)
                .setContentTitle(title)
                .setContentText(body)
                .setSmallIcon(R.drawable.ic_baseline_notifications_active_24)
                .setPriority(Notification.PRIORITY_HIGH)
                .setStyle(new NotificationCompat.BigTextStyle().setSummaryText("Summary").setBigContentTitle(title).bigText(body))
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .build();

        NotificationManagerCompat.from(this).notify(new Random().nextInt(),notification);
    }
}
