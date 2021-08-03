package com.example.quiztime;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView playerName;
    Button startButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        playerName = findViewById(R.id.playerName);
        startButton = findViewById(R.id.startButton);

        if (getIntent() != null){
            playerName.setText(getIntent().getStringExtra("playerName"));
        }

        startButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this, questions.class);
                intent.putExtra("playerName", playerName.getText().toString());
                startActivity(intent);
                finish();
            }
        });
    }
}