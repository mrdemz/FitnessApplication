package com.example.fitnessapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class NutritionActivity extends AppCompatActivity implements View.OnClickListener{

    View goalIcon, nutritionIcon, exerciseIcon, profileIcon;
    Intent goalIntent, exerciseIntent, nutritionIntent, profileIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutrition);

        goalIcon = findViewById(R.id.goalView);
        goalIcon.setOnClickListener(this);

        exerciseIcon = findViewById(R.id.workoutView);
        exerciseIcon.setOnClickListener(this);

        nutritionIcon = findViewById(R.id.nutritionView);
        nutritionIcon.setOnClickListener(this);

        profileIcon = findViewById(R.id.meView);
        profileIcon.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        String[] exerciseNames = getResources().getStringArray(R.array.exercise_names);
        String[] exerciseDetails = getResources().getStringArray(R.array.exercise_details);
        String[] exerciseInstructions = getResources().getStringArray(R.array.exercise_instructions);

        switch (v.getId()) {
            case R.id.goalView:
                goalIntent = new Intent(this, HomeActivity.class);
                goalIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(goalIntent);
                break;
            case R.id.workoutView:
                exerciseIntent = new Intent(this, ExerciseActivity.class);
                exerciseIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(exerciseIntent);
                break;
            case R.id.nutritionView:
                nutritionIntent = new Intent(this, NutritionActivity.class);
                nutritionIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(nutritionIntent);
                break;
            case R.id.meView:
                profileIntent = new Intent(this, ProfileActivity.class);
                profileIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(profileIntent);
                break;
            case R.id.breakfastButton:{
                int x = 0;
                Intent back0i = new Intent(this, Exercises.class);
                back0i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(back0i);
                break;}
        }
    }
}