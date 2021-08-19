package com.example.fitnessapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class HomeActivity extends AppCompatActivity {

    ProfileDataSource profileDataSource = new ProfileDataSource(this);
    ArrayList<Profile> prof = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        profileDataSource.open();
        if(profileDataSource.getCount() == 0){
            AlertDialog.Builder newUserDialog = new AlertDialog.Builder(HomeActivity.this);
            newUserDialog.setMessage("User Profile not found, would you like to make a new profile for better experience?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(HomeActivity.this, ProfileActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                        }
                    }).setNegativeButton("No",null);
            AlertDialog alert = newUserDialog.create();
            alert.show();

        }
        else if(profileDataSource.getCount()==1){
          prof =  profileDataSource.getProfile();
          TextView tw = findViewById(R.id.weightLabelVal);
            TextView tg = findViewById(R.id.curWeightVal);
            tw.setText(String.valueOf(prof.get(0).getWeight()));
            tg.setText(String.valueOf(prof.get(0).getGoalWeight()));

        }


    initMeActivity();
    initHomeActivity();
    initExerciseActivity();
    initNutritionActivity();
    initGraph();
    initDate();
    ProgressBar progress = findViewById(R.id.progressBar);
    progress.setProgress(10);


    }

    private void initDate(){
        Date currentDate = Calendar.getInstance().getTime();
        DateFormat dateFormat = android.text.format.DateFormat.getDateFormat(getApplicationContext());
        TextView date = findViewById(R.id.dateLabel);
        date.setText(dateFormat.format(currentDate));


    }

    private void initGraph(){
        BarChart barChart = findViewById(R.id.barChart);

        ArrayList<BarEntry> steps = new ArrayList<>();
        steps.add(new BarEntry(01, 2111));
        steps.add(new BarEntry(02, 4200));
        steps.add(new BarEntry(03, 3000));
        steps.add(new BarEntry(04, 2113));
        steps.add(new BarEntry(05, 1345));
        steps.add(new BarEntry(06, 678));
        steps.add(new BarEntry(07, 1067));

        BarDataSet barDataSet = new BarDataSet(steps, "Number of Steps");
        barDataSet.setColors(Color.CYAN);
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(11f);

        BarData barData = new BarData (barDataSet);

        barChart.setFitBars(true);
        barChart.setData(barData);
        barChart.getDescription().setText("Steps Progress");
        barChart.animateY(2000);


    }


    private void initMeActivity(){

        View icon = findViewById(R.id.meView);

        icon.setOnClickListener(v -> {

            Intent intent = new Intent(HomeActivity.this, ProfileActivity.class);

            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            startActivity(intent);

        });

    }

    private void initHomeActivity(){

        View icon = findViewById(R.id.goalView);

        icon.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                Intent intent = new Intent(HomeActivity.this, HomeActivity.class);

                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                startActivity(intent);

            }

        });

    }

    private void initNutritionActivity(){
        View icon = findViewById(R.id.nutritionView);
        icon.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, NutritionActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });
    }

    private void initExerciseActivity(){
        View icon = findViewById(R.id.workoutView);
        icon.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, ExerciseActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });
    }


}