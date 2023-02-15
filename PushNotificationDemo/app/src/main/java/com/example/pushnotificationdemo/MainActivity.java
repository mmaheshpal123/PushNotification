package com.example.pushnotificationdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.crashlytics.FirebaseCrashlytics;
import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity {

    TextView textViewTitle, textViewMessage;
    Button buttonCrash;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewTitle = findViewById(R.id.text_view_title);
        textViewMessage= findViewById(R.id.text_view_body);
        buttonCrash = findViewById(R.id.crash_button);

        buttonCrash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                throw new RuntimeException("Test Crash");
            }
        });

        FirebaseMessaging.getInstance().subscribeToTopic("TopicName");

        FirebaseMessaging.getInstance().unsubscribeFromTopic("TopicName");
        onNewIntent(getIntent());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Bundle extras = intent.getExtras() ;
        if (extras != null ) {
            if (extras.containsKey( "title" ) & extras.containsKey("body")) {
                String title = extras.getString( "title" ) ;
                String message = extras.getString( "body" ) ;
                textViewTitle.setText(getString(R.string.title)+title);
                textViewMessage.setText(getString(R.string.message) +message);
                Log.d("type1",message+"");
            }
        }
    }
}