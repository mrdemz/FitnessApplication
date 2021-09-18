                                                                                                                                                                                                                                                                                                                                                                            package com.example.fitnessapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

public class ExerciseActivity extends AppCompatActivity implements View.OnClickListener {

    View goalIcon, nutritionIcon, exerciseIcon, profileIcon;
    Intent back0i, back1i, back2i, back3i, chest0i, chest1i, chest2i, chest3i, shoulder0i, shoulder1i, shoulder2i, shoulder3i,
            arms0i, arms1i, arms2i, arms3i, legs0i, legs1i, legs2i, legs3i,
            goalIntent, exerciseIntent, nutritionIntent, profileIntent;
    ImageView back0,back1, back2, back3,  chest0, chest1, chest2, chest3,
            shoulder0, shoulder1, shoulder2, shoulder3, arms0, arms1, arms2, arms3, legs0, legs1, legs2, legs3;




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

        chest0 = findViewById(R.id.chest0);
        chest0.setOnClickListener(this);
        chest1 = findViewById(R.id.chest1);
        chest1.setOnClickListener(this);
        chest2 = findViewById(R.id.chest2);
        chest2.setOnClickListener(this);
        chest3 = findViewById(R.id.chest3);
        chest3.setOnClickListener(this);

        shoulder0 = findViewById(R.id.shoulder0);
        shoulder0.setOnClickListener(this);
        shoulder1 = findViewById(R.id.shoulder1);
        shoulder1.setOnClickListener(this);
        shoulder2 = findViewById(R.id.shoulder2);
        shoulder2.setOnClickListener(this);
        shoulder3 = findViewById(R.id.shoulder3);
        shoulder3.setOnClickListener(this);

        arms0 = findViewById(R.id.arms0);
        arms0.setOnClickListener(this);
        arms1 = findViewById(R.id.arms1);
        arms1.setOnClickListener(this);
        arms2 = findViewById(R.id.arms2);
        arms2.setOnClickListener(this);
        arms3 = findViewById(R.id.arms3);
        arms3.setOnClickListener(this);

        legs0 = findViewById(R.id.legs0);
        legs0.setOnClickListener(this);
        legs1 = findViewById(R.id.legs1);
        legs1.setOnClickListener(this);
        legs2 = findViewById(R.id.legs2);
        legs2.setOnClickListener(this);
        legs3 = findViewById(R.id.legs3);
        legs3.setOnClickListener(this);
        initActionBarLogo();


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

            case R.id.back0:{
                int x = 0;
                back0i = new Intent(this, Exercises.class);
                back0i.putExtra("exerciseImage", R.drawable.a0);
                back0i.putExtra("exerciseName", exerciseNames[x]);
                back0i.putExtra("exerciseDetail", exerciseDetails[x]);
                back0i.putExtra("exerciseInstruction", exerciseInstructions[x]);
                back0i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                Bundle bundle = new Bundle();
                bundle.putInt("workout", x);
                back0i.putExtras(bundle);
                startActivity(back0i);
                break;}
            case R.id.back1:{
                int x = 1;
                back1i = new Intent(this, Exercises.class);
                back1i.putExtra("exerciseImage", R.drawable.a1);
                back1i.putExtra("exerciseName", exerciseNames[x]);
                back1i.putExtra("exerciseDetail", exerciseDetails[x]);
                back1i.putExtra("exerciseInstruction", exerciseInstructions[x]);
                back1i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                Bundle bundle = new Bundle();
                bundle.putInt("workout", x);
                back1i.putExtras(bundle);
                startActivity(back1i);
                break;}
            case R.id.back2:{
                int x = 2;
                back2i = new Intent(this, Exercises.class);
                back2i.putExtra("exerciseImage", R.drawable.a2);
                back2i.putExtra("exerciseName", exerciseNames[x]);
                back2i.putExtra("exerciseDetail", exerciseDetails[x]);
                back2i.putExtra("exerciseInstruction", exerciseInstructions[x]);
                back2i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                Bundle bundle = new Bundle();
                bundle.putInt("workout", x);
                back2i.putExtras(bundle);
                startActivity(back2i);
                break;}
            case R.id.back3:{
                int x = 3;
                back3i = new Intent(this, Exercises.class);
                back3i.putExtra("exerciseImage", R.drawable.a3);
                back3i.putExtra("exerciseName", exerciseNames[x]);
                back3i.putExtra("exerciseDetail", exerciseDetails[x]);
                back3i.putExtra("exerciseInstruction", exerciseInstructions[x]);
                back3i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                Bundle bundle = new Bundle();
                bundle.putInt("workout", x);
                back3i.putExtras(bundle);
                startActivity(back3i);
                break;}

            case R.id.chest0:{
                int x = 4;
                chest0i = new Intent(this, Exercises.class);
                chest0i.putExtra("exerciseImage", R.drawable.b0);
                chest0i.putExtra("exerciseName", exerciseNames[x]);
                chest0i.putExtra("exerciseDetail", exerciseDetails[x]);
                chest0i.putExtra("exerciseInstruction", exerciseInstructions[x]);
                chest0i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                Bundle bundle = new Bundle();
                bundle.putInt("workout", x);
                chest0i.putExtras(bundle);
                startActivity(chest0i);
                break;}
            case R.id.chest1:{
                int x = 5;
                chest1i = new Intent(this, Exercises.class);
                chest1i.putExtra("exerciseImage", R.drawable.b1);
                chest1i.putExtra("exerciseName", exerciseNames[x]);
                chest1i.putExtra("exerciseDetail", exerciseDetails[x]);
                chest1i.putExtra("exerciseInstruction", exerciseInstructions[x]);
                chest1i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                Bundle bundle = new Bundle();
                bundle.putInt("workout", x);
                chest1i.putExtras(bundle);
                startActivity(chest1i);
                break;}
            case R.id.chest2:{
                int x = 6;
                chest2i = new Intent(this, Exercises.class);
                chest2i.putExtra("exerciseImage", R.drawable.b2);
                chest2i.putExtra("exerciseName", exerciseNames[x]);
                chest2i.putExtra("exerciseDetail", exerciseDetails[x]);
                chest2i.putExtra("exerciseInstruction", exerciseInstructions[x]);
                chest2i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                Bundle bundle = new Bundle();
                bundle.putInt("workout", x);
                chest2i.putExtras(bundle);
                startActivity(chest2i);
                break;}
            case R.id.chest3:{
                int x = 7;
                chest3i = new Intent(this, Exercises.class);
                chest3i.putExtra("exerciseImage", R.drawable.b3);
                chest3i.putExtra("exerciseName", exerciseNames[x]);
                chest3i.putExtra("exerciseDetail", exerciseDetails[x]);
                chest3i.putExtra("exerciseInstruction", exerciseInstructions[x]);
                chest3i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                Bundle bundle = new Bundle();
                bundle.putInt("workout", x);
                chest3i.putExtras(bundle);
                startActivity(chest3i);
                break;}

            case R.id.shoulder0:{
                int x = 8;
                shoulder0i = new Intent(this, Exercises.class);
                shoulder0i.putExtra("exerciseImage", R.drawable.c0);
                shoulder0i.putExtra("exerciseName", exerciseNames[x]);
                shoulder0i.putExtra("exerciseDetail", exerciseDetails[x]);
                shoulder0i.putExtra("exerciseInstruction", exerciseInstructions[x]);
                shoulder0i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                Bundle bundle = new Bundle();
                bundle.putInt("workout", x);
                shoulder0i.putExtras(bundle);
                startActivity(shoulder0i);
                break;}
            case R.id.shoulder1:{
                int x = 9;
                shoulder1i = new Intent(this, Exercises.class);
                shoulder1i.putExtra("exerciseImage", R.drawable.c1);
                shoulder1i.putExtra("exerciseName", exerciseNames[x]);
                shoulder1i.putExtra("exerciseDetail", exerciseDetails[x]);
                shoulder1i.putExtra("exerciseInstruction", exerciseInstructions[x]);
                shoulder1i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                Bundle bundle = new Bundle();
                bundle.putInt("workout", x);
                shoulder1i.putExtras(bundle);
                startActivity(shoulder1i);
                break;}
            case R.id.shoulder2:{
                int x = 10;
                shoulder2i = new Intent(this, Exercises.class);
                shoulder2i.putExtra("exerciseImage", R.drawable.c2);
                shoulder2i.putExtra("exerciseName", exerciseNames[x]);
                shoulder2i.putExtra("exerciseDetail", exerciseDetails[x]);
                shoulder2i.putExtra("exerciseInstruction", exerciseInstructions[x]);
                shoulder2i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                Bundle bundle = new Bundle();
                bundle.putInt("workout", x);
               shoulder2i.putExtras(bundle);
                startActivity(shoulder2i);
                break;}
            case R.id.shoulder3:{
                int x = 11;
                shoulder3i = new Intent(this, Exercises.class);
                shoulder3i.putExtra("exerciseImage", R.drawable.c3);
                shoulder3i.putExtra("exerciseName", exerciseNames[x]);
                shoulder3i.putExtra("exerciseDetail", exerciseDetails[x]);
                shoulder3i.putExtra("exerciseInstruction", exerciseInstructions[x]);
                shoulder3i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                Bundle bundle = new Bundle();
                bundle.putInt("workout", x);
                shoulder3i.putExtras(bundle);
                startActivity(shoulder3i);
                break;}

            case R.id.arms0:{
                int x = 12;
                arms0i = new Intent(this, Exercises.class);
                arms0i.putExtra("exerciseImage", R.drawable.d0);
                arms0i.putExtra("exerciseName", exerciseNames[x]);
                arms0i.putExtra("exerciseDetail", exerciseDetails[x]);
                arms0i.putExtra("exerciseInstruction", exerciseInstructions[x]);
                arms0i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                Bundle bundle = new Bundle();
                bundle.putInt("workout", x);
               arms0i.putExtras(bundle);
                startActivity(arms0i);
                break;}
            case R.id.arms1:{
                int x = 13;
                arms1i = new Intent(this, Exercises.class);
                arms1i.putExtra("exerciseImage", R.drawable.d1);
                arms1i.putExtra("exerciseName", exerciseNames[x]);
                arms1i.putExtra("exerciseDetail", exerciseDetails[x]);
                arms1i.putExtra("exerciseInstruction", exerciseInstructions[x]);
                arms1i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                Bundle bundle = new Bundle();
                bundle.putInt("workout", x);
               arms1i.putExtras(bundle);
                startActivity(arms1i);
                break;}
            case R.id.arms2:{
                int x = 14;
                arms2i = new Intent(this, Exercises.class);
                arms2i.putExtra("exerciseImage", R.drawable.d2);
                arms2i.putExtra("exerciseName", exerciseNames[x]);
                arms2i.putExtra("exerciseDetail", exerciseDetails[x]);
                arms2i.putExtra("exerciseInstruction", exerciseInstructions[x]);
                arms2i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                Bundle bundle = new Bundle();
                bundle.putInt("workout", x);
                arms2i.putExtras(bundle);
                startActivity(arms2i);
                break;}
            case R.id.arms3:{
                int x = 15;
                arms3i = new Intent(this, Exercises.class);
                arms3i.putExtra("exerciseImage", R.drawable.d3);
                arms3i.putExtra("exerciseName", exerciseNames[x]);
                arms3i.putExtra("exerciseDetail", exerciseDetails[x]);
                arms3i.putExtra("exerciseInstruction", exerciseInstructions[x]);
                arms3i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                Bundle bundle = new Bundle();
                bundle.putInt("workout", x);
                arms3i.putExtras(bundle);
                startActivity(arms3i);
                break;}

            case R.id.legs0:{
                int x = 16;
                legs0i = new Intent(this, Exercises.class);
                legs0i.putExtra("exerciseImage", R.drawable.e0);
                legs0i.putExtra("exerciseName", exerciseNames[x]);
                legs0i.putExtra("exerciseDetail", exerciseDetails[x]);
                legs0i.putExtra("exerciseInstruction", exerciseInstructions[x]);
                legs0i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                Bundle bundle = new Bundle();
                bundle.putInt("workout", x);
                legs0i.putExtras(bundle);
                startActivity(legs0i);
                break;}
            case R.id.legs1:{
                int x = 17;
                legs1i = new Intent(this, Exercises.class);
                legs1i.putExtra("exerciseImage", R.drawable.e1);
                legs1i.putExtra("exerciseName", exerciseNames[x]);
                legs1i.putExtra("exerciseDetail", exerciseDetails[x]);
                legs1i.putExtra("exerciseInstruction", exerciseInstructions[x]);
                legs1i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                Bundle bundle = new Bundle();
                bundle.putInt("workout", x);
                legs1i.putExtras(bundle);
                startActivity(legs1i);
                break;}
            case R.id.legs2:{
                int x = 18;
                legs2i = new Intent(this, Exercises.class);
                legs2i.putExtra("exerciseImage", R.drawable.e2);
                legs2i.putExtra("exerciseName", exerciseNames[x]);
                legs2i.putExtra("exerciseDetail", exerciseDetails[x]);
                legs2i.putExtra("exerciseInstruction", exerciseInstructions[x]);
                legs2i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                Bundle bundle = new Bundle();
                bundle.putInt("workout", x);
                legs2i.putExtras(bundle);
                startActivity(legs2i);
                break;}
            case R.id.legs3:{
                int x = 19;
                legs3i = new Intent(this, Exercises.class);
                legs3i.putExtra("exerciseImage", R.drawable.e3);
                legs3i.putExtra("exerciseName", exerciseNames[x]);
                legs3i.putExtra("exerciseDetail", exerciseDetails[x]);
                legs3i.putExtra("exerciseInstruction", exerciseInstructions[x]);
                legs3i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                Bundle bundle = new Bundle();
                bundle.putInt("workout", x);
                legs3i.putExtras(bundle);
                startActivity(legs3i);
                break;}

            }
    }





    public void initActionBarLogo(){


        androidx.appcompat.app.ActionBar actionBar = getSupportActionBar();
        getSupportActionBar().setDisplayShowTitleEnabled(false);



        actionBar.setDisplayShowCustomEnabled(true);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.custom_logo, null);

        actionBar.setCustomView(view);
    }


}