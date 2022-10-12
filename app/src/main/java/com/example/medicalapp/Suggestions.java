package com.example.medicalapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.navigation.NavigationView;


import org.json.JSONException;
import org.json.JSONObject;

public class Suggestions extends AppCompatActivity {
    private ImageView imgBtn , imageViewHeart, imageViewDiabetes, imageViewCommon;
    private TextView txtHeart, txtDiabetes;
    String Test;
    String ID;
    private Button btnLogout, btnStress;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle drawerToggle;
    private Button mealplan;


    public static final String SHARED_PREFS = "shared_prefs";

    SharedPreferences sharedpreferences;
    String id;

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else{
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected( MenuItem item) {
        if(drawerToggle.onOptionsItemSelected(item))
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggestions);

        try {

            drawerLayout = findViewById(R.id.drawer_layout);
            navigationView= findViewById(R.id.nav_view);
            drawerToggle = new ActionBarDrawerToggle(this,drawerLayout, R.string.open,R.string.close);
            drawerLayout.addDrawerListener(drawerToggle);
            drawerToggle.syncState();
            navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    if(item.getItemId()==R.id.profile){
                        Log.d("JSON", "profile ");

                        Intent intent = new Intent(Suggestions.this, CommonDetails.class);
                        intent.putExtra("ID",ID);
                        startActivity(intent);

                    }
                    else if(item.getItemId()==R.id.logout){
                        Log.d("JSON", "logout ");

                        SharedPreferences.Editor editor = sharedpreferences.edit();
                        editor.clear();
                        editor.commit();

                        Intent i = new Intent(Suggestions.this, Login.class);
                        startActivity(i);
                        finish();

                    }

                    return false;
                }


            });
        }
        catch (Exception e){
            e.printStackTrace();
        }
        mealplan = (Button) findViewById(R.id.btnmealplan);
        mealplan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Suggestions.this, Mealplan_intro.class);
                startActivity(intent);
            }
        });


        sharedpreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        id = sharedpreferences.getString("id", null);

        final LoadingDialog loadingDialog = new LoadingDialog(Suggestions.this);

        Intent myIntent = getIntent();
        ID = myIntent.getStringExtra("ID");
        imageViewHeart = findViewById(R.id.imageViewHeart);
        imageViewDiabetes = findViewById(R.id.imageViewDiabetes);
        imageViewCommon = findViewById(R.id.imageViewCommon);
        txtHeart = findViewById(R.id.textHeart);
        txtDiabetes = findViewById(R.id.txtDiabetes);
        txtHeart.setVisibility(View.INVISIBLE);
        txtDiabetes.setVisibility(View.INVISIBLE);

        btnStress = findViewById(R.id.btnStress);
        btnStress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent(Suggestions.this, StressMain.class);
                    intent.putExtra("ID", ID);
                    startActivity(intent);
                }
                catch (Exception e){
                    Toast.makeText(Suggestions.this, "Error = " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        /////
        loadingDialog.StartLoadingDialog();
        String URL = "https://rpprojectapp.herokuapp.com/getRisks";
        JSONObject object = new JSONObject();
        try {
            object.put("id", ID);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, URL, object,new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("State", response.toString());
                try {
                    if(response.getString("heartRisk").toString().equals("0")) {
                        imageViewHeart.setImageResource(R.drawable.low);
                        txtHeart.setVisibility(View.VISIBLE);
                        txtDiabetes.setVisibility(View.VISIBLE);
                    }
                    if(response.getString("heartRisk").toString().equals("1")){
                        imageViewHeart.setImageResource(R.drawable.high);
                        txtHeart.setVisibility(View.VISIBLE);
                        txtDiabetes.setVisibility(View.VISIBLE);
                    }
                    if(response.getString("diabetesRisk").toString().equals("0.0")){
                        imageViewDiabetes.setImageResource(R.drawable.low);
                        txtHeart.setVisibility(View.VISIBLE);
                        txtDiabetes.setVisibility(View.VISIBLE);
                    }
                    if(response.getString("diabetesRisk").toString().equals("1.0")){
                        imageViewDiabetes.setImageResource(R.drawable.medium);
                        txtHeart.setVisibility(View.VISIBLE);
                        txtDiabetes.setVisibility(View.VISIBLE);
                    }
                    if(response.getString("diabetesRisk").toString().equals("2.0")){
                        imageViewDiabetes.setImageResource(R.drawable.high);
                        txtHeart.setVisibility(View.VISIBLE);
                        txtDiabetes.setVisibility(View.VISIBLE);
                    }
                    if(response.getString("diabetesRisk").toString().equals("error") && response.getString("heartRisk").toString().equals("error")){
                        imageViewCommon.setImageResource(R.drawable.please_complete_the_profile);
                    }
                    loadingDialog.dismissDialog();
                } catch (Exception e) {
                    Log.d("JSON", e.toString());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("JSON", error.toString());
                VolleyLog.d("Error", "Error: " + error.getMessage());
                Log.d("JSON", error.toString());
                loadingDialog.dismissDialog();
                Toast.makeText(Suggestions.this, "Error :::: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                ////////////////////////////////////////////////////
            }
        });
        Log.d("JSON", object.toString());
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(jsonObjectRequest);
        ///

    }
}