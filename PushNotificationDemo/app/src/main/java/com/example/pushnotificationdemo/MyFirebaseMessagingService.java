package com.example.pushnotificationdemo;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(@NonNull RemoteMessage message) {
        super.onMessageReceived(message);
        sendNotification(message);
        Log.d("val",message.getData().get("message1"));
    }

    @Override
    public void onNewToken(@NonNull String token) {
        super.onNewToken(token);
        Log.d("Token","Token: "+token);
        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(task -> {
            if (!task.isComplete()){
                Log.w("TAG", "getInstanceId failed",
                        task.getException());
                return;
            }
             String token1 = task.getResult();
            Log.d("Val", token1);
        });
    }

    private void sendNotification(RemoteMessage remoteMessage){
        Intent intent = new Intent(this,MainActivity.class);
        if (remoteMessage.getData().size()>0){
            intent.putExtra("title",remoteMessage.getData().get("title1"));
            intent.putExtra("body",remoteMessage.getData().get("message1"));
            intent.addCategory(Intent.CATEGORY_LAUNCHER);
            intent.setAction(Intent.ACTION_MAIN);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        }
        PendingIntent pendingIntent = PendingIntent.
                getActivity(this,0,intent,PendingIntent.FLAG_ONE_SHOT);
        String CHANNEL_ID = getString(R.string.notification_Id);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,CHANNEL_ID)
                .setContentTitle(remoteMessage.getData().get("title1"))
                .setContentText(remoteMessage.getData().get("message1"))
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setAutoCancel(true)
                .setVibrate(new long[] {1000,1000,2000,2000})
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID,
                    "Channel human readable title",
                    NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
        }
        notificationManager.notify(0, builder.build());
    }


}
