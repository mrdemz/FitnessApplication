package com.example.fitnessapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class ExerciseList_Activity extends AppCompatActivity {
    ExerciseDataSource exerciseDataSource = new ExerciseDataSource(this);
    ArrayList<View> contentview = new ArrayList<View>();
    private ArrayList<Exercise> exerciseArrayList = new ArrayList<Exercise>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_list);
        listExerciseData();
        initActionBarLogo();
        initBackButton();
    }

    public void listExerciseData() {


        LinearLayout contents = findViewById(R.id.exercise_data_content);
        LayoutInflater inflater = LayoutInflater.from(this);
        exerciseDataSource.open();
        if (exerciseDataSource.getCount() > 0) {

            exerciseArrayList = exerciseDataSource.getExercise();
            exerciseDataSource.close();
            for (int i = 0; i < exerciseArrayList.size(); i++) {
                int num = i + 1;




                contentview.add(inflater.inflate(R.layout.exercise_data_layout, contents, false));

                TextView nameView = contentview.get(i).findViewById(R.id.exercise_data);
                TextView dateView = contentview.get(i).findViewById(R.id.exercise_date_data);


                nameView.setText(exerciseArrayList.get(i).getExerciseName());
                dateView.setText(exerciseArrayList.get(i).getDate());
                contents.addView(contentview.get(i));


            }


        }


    }
    public void initBackButton(){
        Button icon = findViewById(R.id.exercise_data_back_button);
        icon.setOnClickListener(v -> {
            Intent intent = new Intent(ExerciseList_Activity.this, HomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
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
}