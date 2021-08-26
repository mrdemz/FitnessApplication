                                                                                                                                                                                                                                                                                                                                                                            package com.example.fitnessapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class ExerciseActivity extends AppCompatActivity implements View.OnClickListener {

    View goalIcon, nutritionIcon, exerciseIcon, profileIcon;
    Intent back0i, back1i, back2i, back3i, back4i, back5i, chest0i, chest1i, chest2i,
            goalIntent, exerciseIntent, nutritionIntent, profileIntent;
    ImageView back0,back1, back2, back3, back4, back5, chest0, chest1, chest2,
            latePulldown, benchPress, dumbbellPress, cableFly, militaryPress;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);



        goalIcon = findViewById(R.id.goalView);
        goalIcon.setOnClickListener(this);

        exerciseIcon = findViewById(R.id.workoutView);
        exerciseIcon.setOnClickListener(this);

        nutritionIcon = findViewById(R.id.nutritionView);
        nutritionIcon.setOnClickListener(this);

        profileIcon = findViewById(R.id.meView);
        profileIcon.setOnClickListener(this);

        back0 = findViewById(R.id.back0);
        back0.setOnClickListener(this);
        back1 = findViewById(R.id.back1);
        back1.setOnClickListener(this);
        back2 = findViewById(R.id.back2);
        back2.setOnClickListener(this);
        back3 = findViewById(R.id.back3);
        back3.setOnClickListener(this);
        back4 = findViewById(R.id.back4);
        back4.setOnClickListener(this);
        back5 = findViewById(R.id.back5);
        back5.setOnClickListener(this);

        chest0 = findViewById(R.id.chest0);
        chest0.setOnClickListener(this);
        chest1 = findViewById(R.id.chest1);
        chest1.setOnClickListener(this);
        chest2 = findViewById(R.id.chest2);
        chest2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        String[] exerciseNames = getResources().getStringArray(R.array.exercise_names);
        String[] exerciseDetails = getResources().getStringArray(R.array.exercise_details);
        String[] exerciseInstructions = getResources().getStringArray(R.array.exercise_instructions);

        switch (v.getId()){
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

            case R.id.back0:
                int a0 = 0;
                back0i = new Intent(this, Exercises.class);
                back0i.putExtra("exerciseImage", R.drawable.a0);
                back0i.putExtra("exerciseName", exerciseNames[a0]);
                back0i.putExtra("exerciseDetail", exerciseDetails[a0]);
                back0i.putExtra("exerciseInstruction", exerciseInstructions[a0]);
                back0i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(back0i);
                break;
            case R.id.back1:
                int a1 = 1;
                back1i = new Intent(this, Exercises.class);
                back1i.putExtra("exerciseImage", R.drawable.a1);
                back1i.putExtra("exerciseName", exerciseNames[a1]);
                back1i.putExtra("exerciseDetail", exerciseDetails[a1]);
                back1i.putExtra("exerciseInstruction", exerciseInstructions[a1]);
                back1i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(back1i);
                break;
            case R.id.back2:
                int a2 = 2;
                back2i = new Intent(this, Exercises.class);
                back2i.putExtra("exerciseImage", R.drawable.a2);
                back2i.putExtra("exerciseName", exerciseNames[a2]);
                back2i.putExtra("exerciseDetail", exerciseDetails[a2]);
                back2i.putExtra("exerciseInstruction", exerciseInstructions[a2]);
                back2i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(back2i);
                break;
            case R.id.back3:
                int a3 = 3;
                back3i = new Intent(this, Exercises.class);
                back3i.putExtra("exerciseImage", R.drawable.a3);
                back3i.putExtra("exerciseName", exerciseNames[a3]);
                back3i.putExtra("exerciseDetail", exerciseDetails[a3]);
                back3i.putExtra("exerciseInstruction", exerciseInstructions[a3]);
                back3i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(back3i);
                break;
            case R.id.back4:
                int a4 = 4;
                back4i = new Intent(this, Exercises.class);
                back4i.putExtra("exerciseImage", R.drawable.a4);
                back4i.putExtra("exerciseName", exerciseNames[a4]);
                back4i.putExtra("exerciseDetail", exerciseDetails[a4]);
                back4i.putExtra("exerciseInstruction", exerciseInstructions[a4]);
                back4i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(back4i);
                break;
            case R.id.back5:
                int a5 = 5;
                back5i = new Intent(this, Exercises.class);
                back5i.putExtra("exerciseImage", R.drawable.a5);
                back5i.putExtra("exerciseName", exerciseNames[a5]);
                back5i.putExtra("exerciseDetail", exerciseDetails[a5]);
                back5i.putExtra("exerciseInstruction", exerciseInstructions[a5]);
                back5i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(back5i);
                break;


            case R.id.chest0:
                int a6 = 6;
                chest0i = new Intent(this, Exercises.class);
                chest0i.putExtra("exerciseImage", R.drawable.b0);
                chest0i.putExtra("exerciseName", exerciseNames[a6]);
                chest0i.putExtra("exerciseDetail", exerciseDetails[a6]);
                chest0i.putExtra("exerciseInstruction", exerciseInstructions[a6]);
                chest0i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(chest0i);
                break;
            case R.id.chest1:
                int a7 = 7;
                chest1i = new Intent(this, Exercises.class);
                chest1i.putExtra("exerciseImage", R.drawable.b1);
                chest1i.putExtra("exerciseName", exerciseNames[a7]);
                chest1i.putExtra("exerciseDetail", exerciseDetails[a7]);
                chest1i.putExtra("exerciseInstruction", exerciseInstructions[a7]);
                chest1i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(chest1i);
                break;
            case R.id.chest2:
                int a8 = 8;
                chest2i = new Intent(this, Exercises.class);
                chest2i.putExtra("exerciseImage", R.drawable.b2);
                chest2i.putExtra("exerciseName", exerciseNames[a8]);
                chest2i.putExtra("exerciseDetail", exerciseDetails[a8]);
                chest2i.putExtra("exerciseInstruction", exerciseInstructions[a8]);
                chest2i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(chest2i);
                break;

            }
    }


}