package com.example.medicalapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Mealplan_Qlist_Diabetics extends AppCompatActivity {
    private Button GetMeal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mealplan_qlist);

        GetMeal = (Button) findViewById(R.id.btnGetMealPlan);
        GetMeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Mealplan_Qlist_Diabetics.this, MealPlan_list.class);
                startActivity(intent);
            }
        });
    }
}