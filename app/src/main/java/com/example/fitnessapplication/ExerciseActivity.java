package com.example.fitnessapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ExerciseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);
        initHomeActivity();
        initExerciseActivity();
        initNutritionActivity();
        initMeActivity();
    }

    private void initMeActivity(){

        View icon = findViewById(R.id.meView);

        icon.setOnClickListener(v -> {

            Intent intent = new Intent(ExerciseActivity.this, ProfileActivity.class);

            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            startActivity(intent);

        });

    }

    private void initHomeActivity(){

        View icon = findViewById(R.id.goalView);

        icon.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                Intent intent = new Intent(ExerciseActivity.this, HomeActivity.class);

                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                startActivity(intent);

            }

        });

    }

    private void initNutritionActivity(){
        View icon = findViewById(R.id.nutritionView);
        icon.setOnClickListener(v -> {
            Intent intent = new Intent(ExerciseActivity.this, NutritionActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });
    }

    private void initExerciseActivity(){
        View icon = findViewById(R.id.workoutView);
        icon.setOnClickListener(v -> {
            Intent intent = new Intent(ExerciseActivity.this, ExerciseActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });
    }

}