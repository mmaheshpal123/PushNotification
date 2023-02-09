package com.example.helloworld;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.core.app.NotificationCompat;
import androidx.core.app.TaskStackBuilder;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.io.Serializable;
import java.util.Map;

public class MyFireBaseMessagingService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(@NonNull RemoteMessage message) {
        super.onMessageReceived(message);
        if (message.getNotification() != null) {
            showNotification(message.getNotification().getTitle()
                    , message.getNotification().getBody());
           // Log.d("val",message.getNotification().getBody());

        }
        if (message.getNotification()!=null) {
            String title = message.getNotification().getTitle();
            String body = message.getNotification().getBody();
            Log.d("val",message.getNotification().getBody());

            Intent intent = new Intent(this, Activity2.class);
            intent.putExtra("title", title);
            intent.putExtra("body",body);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }

    @Override
    public void onNewToken(@NonNull String token) {
        super.onNewToken(token);
        Log.d("value","Refreshed Token: " + token);
    }

    public void showNotification(String title, String message){
        Intent intent = new Intent(this,Activity2.class);
        String channel_Id = getString(R.string.notification_Id);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        stackBuilder.addNextIntentWithParentStack(intent);

        PendingIntent pendingIntent = PendingIntent.
                getActivity(this,0,intent,PendingIntent.FLAG_ONE_SHOT);

        NotificationCompat.Builder builder = new
                NotificationCompat.Builder(getApplicationContext(),channel_Id).
                setAutoCancel(true)
                .setVibrate(new long[]{1000,1000,1000,1000,2000})
                .setOnlyAlertOnce(true)
                .setPriority(Notification.PRIORITY_MAX)
                .setContentTitle(title)
                .setContentText(message)
                .setSmallIcon(R.drawable.ic_baseline_anchor_24)
                .setContentIntent(pendingIntent);

        builder = builder.setContentTitle(title).setContentText(message);

        NotificationManager notificationManager =
                (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel notificationChannel = new NotificationChannel
                    (channel_Id,"web_app",NotificationManager.IMPORTANCE_HIGH);

            notificationManager.createNotificationChannel(notificationChannel);
        }
        notificationManager.notify(0,builder.build());
       // NotificationManagerCompat.from(this).notify(0,builder.build());
    }

}
