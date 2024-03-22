package com.example.histoquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;

public class q5_pilihanraya extends AppCompatActivity {

    Button btnChoiceA, btnChoiceB, btnChoiceC, btnChoiceD, btnNextQuestion, correctAnswer;
    TextView txtTimer, txtLives;
    int score, wrongAnswers;
    long remainingTimeMillis;
    CountDownTimer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.q5_pilihanraya);
        // Initialize views
        btnChoiceA = findViewById(R.id.btnChoiceA);
        btnChoiceB = findViewById(R.id.btnChoiceB);
        btnChoiceC = findViewById(R.id.btnChoiceC);
        btnChoiceD = findViewById(R.id.btnChoiceD);
        btnNextQuestion = findViewById(R.id.btnNextQuestion);
        txtTimer = findViewById(R.id.txtTimer);
        txtLives = findViewById(R.id.txtLives);


        // Retrieve data from intent
        Intent intent = getIntent();
        score = intent.getIntExtra("score", 0);
        wrongAnswers = intent.getIntExtra("wrongAnswers", 0);
        remainingTimeMillis = intent.getLongExtra("remainingTimeMillis", 0);

        // Update UI with the retrieved data
        txtLives.setText("Lives: " + wrongAnswers);
        // Set the correct answer button after initializing buttons
        correctAnswer = btnChoiceC;




        // Set click listeners for all choice buttons
        btnChoiceA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(btnChoiceA);
            }
        });

        btnChoiceB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(btnChoiceB);
            }
        });

        btnChoiceC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(btnChoiceC);
            }
        });

        btnChoiceD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(btnChoiceD);
            }
        });



        // Set up the countdown timer for 1 minute (60000 milliseconds)
        timer = new CountDownTimer(remainingTimeMillis, 1000) {
            public void onTick(long millisUntilFinished) {
                // Update the timer TextView with the remaining time
                txtTimer.setText("Time: " + formatTime(millisUntilFinished));
                // Store the remaining time in milliseconds
                remainingTimeMillis = millisUntilFinished;
            }

            public void onFinish() {
                // If the timer finishes, start the game over activity
                Intent intent = new Intent(q5_pilihanraya.this, gameover.class);
                // Pass score and wrongAnswers as extras
                intent.putExtra("score", score);
                intent.putExtra("wrongAnswers", wrongAnswers);
                intent.putExtra("remainingTimeMillis", remainingTimeMillis);
                startActivity(intent);
            }
        }.start();

    }

    // Method to format the remaining time in minutes and seconds
    private String formatTime(long millis) {
        int seconds = (int) (millis / 1000) % 60;
        int minutes = (int) ((millis / (1000 * 60)) % 60);
        return String.format("%02d:%02d", minutes, seconds);
    }


    // Method to check the answer
    private void checkAnswer(Button selectedButton) {

        // Stop the countdown timer
        timer.cancel();

        if (selectedButton == correctAnswer) {
            // Correct answer, make button green and others default
            correctAnswer.setBackgroundColor(getResources().getColor(R.color.green));
            score++; // Increment the score
        } else {
            // Wrong answer, make button red and correct one green
            selectedButton.setBackgroundColor(getResources().getColor(R.color.red));
            correctAnswer.setBackgroundColor(getResources().getColor(R.color.green));
            wrongAnswers--; // Increment wrong answers
            txtLives.setText("Lives: " + wrongAnswers);
        }
        // Disable all buttons to prevent further selection
        btnChoiceA.setEnabled(false);
        btnChoiceB.setEnabled(false);
        btnChoiceC.setEnabled(false);
        btnChoiceD.setEnabled(false);

        btnNextQuestion.setVisibility(View.VISIBLE);

        // Set click listener for Next Question button
        btnNextQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (wrongAnswers <=0) {
                    // If wrongAnswers reaches 2, redirect to game over activity
                    Intent intent = new Intent(q5_pilihanraya.this, gameover.class);
                    // Pass score and wrongAnswers as extras
                    intent.putExtra("score", score);
                    intent.putExtra("wrongAnswers", wrongAnswers);
                    intent.putExtra("remainingTimeMillis", remainingTimeMillis);
                    startActivity(intent);
                } else {
                    // Otherwise, proceed to the next question activity
                    Intent intent = new Intent(q5_pilihanraya.this, congrats_page.class);
                    // Pass score and wrongAnswers as extras
                    intent.putExtra("score", score);
                    intent.putExtra("wrongAnswers", wrongAnswers);
                    intent.putExtra("remainingTimeMillis", remainingTimeMillis);
                    startActivity(intent);
                }
            }
        });
    }



}