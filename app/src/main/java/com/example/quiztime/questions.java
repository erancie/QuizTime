package com.example.quiztime;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.graphics.Color;
import android.widget.Toast;

public class questions extends AppCompatActivity {

    String selectedAnswerString, nameWelcome;
    Button selectedButton;
    Integer score = 0;
    TextView textWelcome, questionTitle, questionBody;
    Button[] answers;
    Button submitButton;
    Boolean answerSelected = false;
    String[] titles,bodies;
    String[][] choices = new String[5][3];
    String[] correct_answers;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        //progress bar
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setMax(5);
        progressBar.setProgress(+1);
        //Question number
        int progressValue = progressBar.getProgress();

        //put string-arrays into local array objects
        titles = getResources().getStringArray(R.array.questionTitlesArray);
        bodies = getResources().getStringArray(R.array.questionBodiesArray);

        choices[0] = getResources().getStringArray(R.array.question1Answers);
        choices[1] = getResources().getStringArray(R.array.question2Answers);
        choices[2] = getResources().getStringArray(R.array.question3Answers);
        choices[3] = getResources().getStringArray(R.array.question4Answers);
        choices[4] = getResources().getStringArray(R.array.question5Answers);

        correct_answers = getResources().getStringArray(R.array.correctAnswers);

        //Activity fields and actions
        textWelcome = findViewById(R.id.textWelcome);
        questionTitle = findViewById(R.id.questionTitle);
        questionBody = findViewById(R.id.questionBody);

        answers = new Button[]{findViewById(R.id.answer1), findViewById(R.id.answer2), findViewById(R.id.answer3)};
        submitButton = findViewById(R.id.submitButton);

        //get players name from playerName intent
        nameWelcome = getIntent().getStringExtra("playerName");
        //Populate welcome field with user's name
        textWelcome.setText("Welcome " + nameWelcome);
        createQuestion();
    }

    //refresh fields and create new question and increment progress
    public void createQuestion(){
        int progress = progressBar.getProgress();

        questionTitle.setText(titles[progress-1]);
        questionBody.setText(bodies[progress-1]);

        for (int i=0;i<3;i++) {
            answers[i].setText(choices[progress-1][i]);
        }
        answerSelected = false;
        submitButton.setText("Submit");


        for (int i = 0; i < answers.length; i++) {
            Button a = answers[i];

            a.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!answerSelected) {
                        //make button color grey after selection
                        a.setBackgroundColor(Color.parseColor("#e6e6e6"));
                        //get the string from the user's selection
                        selectedAnswerString = a.getText().toString();
                        //assign selected button
                        selectedButton = a;
                        //--> make other buttons un-clickable
                        answerSelected = true;
                    }
                }
            });
        }

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(submitButton.getText().toString().equals("Submit")) {
                    submitButton.setText("Next");
                    //check if the button text equals the correct answer text
                    if (selectedAnswerString.equals(correct_answers[progress-1])){ //this will work when assigned as per question
                        // --> make button bg green
                        selectedButton.setBackgroundColor(Color.parseColor("#00cc66"));
                        Log.d("clickTag", "CORRECT ANSWER!!");
                        //add 1 to score
                        score++;
                    }else{
                        Log.d("clickTag", "WRONG ANSWER..");
                        //make correct button bg green & incorrect button bg red
                        selectedButton.setBackgroundColor(Color.parseColor("#ff5050"));
                        for (int i=0;i<3;i++) {
                            if(answers[i].getText().toString().equals(correct_answers[progress-1])) {
                                answers[i].setBackgroundColor(Color.parseColor("#00cc66"));
                            }
                        }
                    }
                }
                else if(submitButton.getText().toString().equals("Next")) {
                    if(progress<5) {
                        progressBar.setProgress(progress+1);
                        for (int i=0;i<3;i++) {
                            answers[i].setBackgroundColor(Color.parseColor("#ffffff"));
                        }
                        createQuestion();
                    }
                    else {
//                        Toast.makeText(getApplicationContext(), "New Activity", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(questions.this, result.class);
                        intent.putExtra("finalScore", score.toString());
                        intent.putExtra("playerName", nameWelcome);
                        startActivity(intent);
                        finish();
                    }
                }
            }
        });
    }
}