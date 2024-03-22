package com.example.histoquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class gameover extends AppCompatActivity {

    Button btnPlayAgain;
    TextView txtGameOver;
    TextView txtTime,txtScore;
    int score, wrongAnswers;
    long remainingTimeMillis;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gameover);

        txtTime = findViewById(R.id.txtTime);
        txtScore = findViewById(R.id.txtScore);
        txtGameOver = findViewById(R.id.txtGameOver);
        btnPlayAgain = findViewById(R.id.btnPlayAgain);

        btnPlayAgain.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent=new Intent(gameover.this,QuestionPackage.class);
                startActivity(intent);
            }
        });

        // Create ObjectAnimator to scale the text up and down
        ObjectAnimator scaleXAnimator = ObjectAnimator.ofFloat(txtGameOver, "scaleX", 1.0f, 1.2f);
        scaleXAnimator.setRepeatMode(ObjectAnimator.REVERSE);
        scaleXAnimator.setRepeatCount(ObjectAnimator.INFINITE);
        scaleXAnimator.setDuration(500);

        ObjectAnimator scaleYAnimator = ObjectAnimator.ofFloat(txtGameOver, "scaleY", 1.0f, 1.2f);
        scaleYAnimator.setRepeatMode(ObjectAnimator.REVERSE);
        scaleYAnimator.setRepeatCount(ObjectAnimator.INFINITE);
        scaleYAnimator.setDuration(500);

        // Start both animators
        scaleXAnimator.start();
        scaleYAnimator.start();


        // Retrieve data from intent
        Intent intent = getIntent();
        score = intent.getIntExtra("score", 0);
        wrongAnswers = intent.getIntExtra("wrongAnswers", 0);
        remainingTimeMillis = intent.getLongExtra("remainingTimeMillis", 0);

        // Update UI with the retrieved data
        txtScore.setText("Your Total Score: " + score +"/5");
        txtTime.setText("Remaining time left: " +  formatTime(remainingTimeMillis));
    }

    // Method to format time from milliseconds to "00:00" format
    private String formatTime(long millis) {
        int seconds = (int) (millis / 1000) % 60;
        int minutes = (int) ((millis / (1000 * 60)) % 60);
        return String.format("%02d:%02d", minutes, seconds);
    }
}