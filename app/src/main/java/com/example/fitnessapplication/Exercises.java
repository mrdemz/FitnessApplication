package com.example.fitnessapplication;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class Exercises extends AppCompatActivity {

    ProfileDataSource dataSource;
    private String selectedWorkout;
    private String dateNow;
    private ExerciseModel exerciseModel;
    ImageView image;
    TextView name, details, instructions;
    Button startB, endB, cancelB, backB;
    Exercise exercise = new Exercise();
    ExerciseDataSource exerciseDataSource = new ExerciseDataSource(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bench_press);
        Bundle workoutBundle = getIntent().getExtras();
        int workout = workoutBundle.getInt("workout");
        String workoutArr[] = getResources().getStringArray(R.array.fitness_workout);
        selectedWorkout = workoutArr[workout];
        image = findViewById(R.id.exercisePic);
        name = findViewById(R.id.textViewName);
        details = findViewById(R.id.details);
        instructions = findViewById(R.id.instructions);
        Bundle bundle = getIntent().getExtras();

        if (bundle != null){
            int exImage = bundle.getInt("exerciseImage");
            image.setImageResource(exImage);
        }

        String exName = bundle.getString("exerciseName");
        String exDetails = bundle.getString("exerciseDetail");
        String exInstructions = bundle.getString("exerciseInstruction");

        name.setText(exName);
        details.setText(exDetails);
        instructions.setText(exInstructions);

        endButton();
        cancelButton();
        initActionBarLogo();
        initDate();
    }

    public void endButton(){
        backB = findViewById(R.id.startButton);
        backB.setOnClickListener(v->{
            exercise.setExerciseName(selectedWorkout);
            exercise.setDate(dateNow);
            exerciseDataSource.open();
            exerciseDataSource.insertItem(exercise);
            exerciseDataSource.close();

           finish();
        });
    }

    public void cancelButton(){
        backB = findViewById(R.id.cancelButton);
        backB.setOnClickListener(v->{
        finish();
        });
    }

    public void initActionBarLogo(){


        androidx.appcompat.app.ActionBar actionBar = getSupportActionBar();
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        actionBar.setDisplayShowCustomEnabled(true);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.custom_logo, null);

        actionBar.setCustomView(view);
    }
    public void initDate(){
        Date currentDate = Calendar.getInstance().getTime();
        DateFormat dateFormat = android.text.format.DateFormat.getDateFormat(getApplicationContext());
        dateNow = dateFormat.format(currentDate);

    }

}