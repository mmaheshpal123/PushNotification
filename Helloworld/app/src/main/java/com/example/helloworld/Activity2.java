package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Activity2 extends AppCompatActivity {

    Button button;
    TextView textViewTitle, textViewMessage;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        button = findViewById(R.id.button_two);
        textViewTitle = findViewById(R.id.text_view_title2);
        textViewMessage = findViewById(R.id.text_view_body2);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Activity2.this,MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        Intent intent = getIntent();
        if (intent != null) {
            String title = intent.getStringExtra("title");
            String message = intent.getStringExtra("body");
            textViewTitle.setText(title);
            textViewMessage.setText(message);
        }
    }
}