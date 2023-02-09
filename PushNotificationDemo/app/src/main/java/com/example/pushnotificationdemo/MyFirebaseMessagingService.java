package com.example.pushnotificationdemo;

import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(@NonNull RemoteMessage message) {
        super.onMessageReceived(message);

    }

    @Override
    public void onNewToken(@NonNull String token) {
        super.onNewToken(token);
        Log.d("Token","Token: "+token);
        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(task -> {
            if (task.isComplete()){
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
    }


}
