package com.example.medicalapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Mealplan_intro extends AppCompatActivity {
   private Button Diebetics;
   private Button Heart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mealplan_intro);

        Diebetics = (Button) findViewById(R.id.btnDiebetics);
        Diebetics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Mealplan_intro.this, Mealplan_Qlist_Diabetics.class);
                startActivity(intent);
            }
        });

        Heart = (Button) findViewById(R.id.btnHeart);
        Heart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Mealplan_intro.this, Mealplan_Qlist_Diabetics.class);
                startActivity(intent);
            }
        });
    }
}