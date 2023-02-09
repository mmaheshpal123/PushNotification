package com.example.helloworld;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Map;

public class MainActivity extends AppCompatActivity {

    Button button;
    TextView textViewTitle,textViewBody;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button_one);
        textViewTitle = findViewById(R.id.text_view_title);
        textViewBody = findViewById(R.id.text_view_body);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Activity2.class);
                startActivity(intent);
            }
        });


       /* Intent intent = getIntent();
        if (intent != null) {
            String title = intent.getStringExtra("title");
            String message = intent.getStringExtra("body");
            textViewTitle.setText(title);
            textViewBody.setText(message);*/
        //}
    }

}