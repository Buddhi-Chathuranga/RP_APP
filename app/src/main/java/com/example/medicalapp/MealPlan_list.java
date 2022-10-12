package com.example.medicalapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MealPlan_list extends AppCompatActivity {
   private  Button Meal1;
    private  Button Meal2;
    private  Button Meal3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_plan_list);

        Meal1 = (Button) findViewById(R.id.btnmeal1);
        Meal1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( MealPlan_list.this, MealPlan.class);
                startActivity(intent);
            }
        });

        Meal2 = (Button) findViewById(R.id.btnmeal2);
        Meal2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( MealPlan_list.this, MealPlan.class);
                startActivity(intent);
            }
        });

        Meal3 = (Button) findViewById(R.id.btnmeal3);
        Meal3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( MealPlan_list.this, MealPlan.class);
                startActivity(intent);
            }
        });
    }
}