package com.example.quiztime;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;

public class result extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        String score = getIntent().getStringExtra("finalScore");
        String playerName = getIntent().getStringExtra("playerName");

        TextView player = findViewById(R.id.player);
        player.setText(playerName);

        TextView result = findViewById(R.id.scoreResult);
        result.setText(score + "/5");

        Button newQuiz = findViewById(R.id.newQuiz);

        newQuiz.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(result.this, MainActivity.class);
                intent.putExtra("playerName", playerName);
                startActivity(intent);
                finish();
            }
        });

        Button finish = findViewById(R.id.finish);

        finish.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                finish();
            }
        });


    }
}