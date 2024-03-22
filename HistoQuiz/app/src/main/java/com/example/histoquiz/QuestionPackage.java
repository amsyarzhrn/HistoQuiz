package com.example.histoquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class QuestionPackage extends AppCompatActivity {

    Button btnPilihanRaya,btnSistemPersekutuan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_package);

        btnPilihanRaya = findViewById(R.id.btnPilihanRaya);
        btnSistemPersekutuan = findViewById(R.id.btnSistemPersekutuan);

        btnSistemPersekutuan.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent=new Intent(QuestionPackage.this, q1_sistempersekutuan.class);
                startActivity(intent);
            }
        });

        btnPilihanRaya.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent=new Intent(QuestionPackage.this, q1_pilihanraya.class);
                startActivity(intent);
            }
        });



    }
}