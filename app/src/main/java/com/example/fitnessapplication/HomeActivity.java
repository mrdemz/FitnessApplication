package com.example.fitnessapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.text.DateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class HomeActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor mStepCounter;
    private boolean isCounterSensorPresent;
    private int stepCount = -1;
    private String dateNow;
    private String lastDate;
    private String answer = "true";
    private String lastAnswer;
    private int totalSteps = 0;



    TextView textViewStepCounter;
    TextView textViewDistaceCounter;
    TextView textViewBmiCounter;


    private Pedometer pedometer = new Pedometer ();
    ProfileDataSource profileDataSource = new ProfileDataSource(this);
    PedometerDataSource pedometerDataSource = new PedometerDataSource(this);
    ArrayList<Profile> prof = new ArrayList<>();
    ArrayList<Pedometer> ped = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initDate();


        textViewStepCounter = findViewById(R.id.stepsValueLabel);
        textViewDistaceCounter = findViewById(R.id.todayMilesVal);
        textViewBmiCounter = findViewById(R.id.bmiValue);


        pedometerDataSource.open();
        if(pedometerDataSource.getCount() == 0){
            pedometer.setDate(dateNow);
            pedometer.setStepCount(stepCount);
            pedometer.setAnswer(answer);
            pedometerDataSource.insertItem(pedometer);

        }
        else if (pedometerDataSource.getCount() >0){
            ped = pedometerDataSource.getPedList();
            for (int i = 0 ; i < ped.size(); i++){
                totalSteps += ped.get(i).getstepCount();
            }
        }
        lastDate = pedometerDataSource.getLastDate();
        lastAnswer= pedometerDataSource.getLastAnswer();
        pedometerDataSource.close();





        profileDataSource.open();
        if(profileDataSource.getCount() == 0) {
            if (lastAnswer.equals("true")) {
                AlertDialog.Builder newUserDialog = new AlertDialog.Builder(HomeActivity.this);
                newUserDialog.setMessage("User Profile not found, would you like to make a new profile for better experience?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(HomeActivity.this, ProfileActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                            }
                        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        pedometerDataSource.open();
                        pedometer.setAnswer("false");
                        pedometer.setPedId(pedometerDataSource.getLastItemID());
                        pedometerDataSource.updatePedometer(pedometer);
                        pedometerDataSource.close();



                    }
                });
                AlertDialog alert = newUserDialog.create();
                alert.show();

            }
        }
        else if(profileDataSource.getCount()==1){
            prof =  profileDataSource.getProfile();
            TextView tw = findViewById(R.id.weightLabelVal);
            TextView tg = findViewById(R.id.curWeightVal);
            tw.setText(String.valueOf(prof.get(0).getGoalWeight()));
            tg.setText(String.valueOf(prof.get(0).getWeight()));
            textViewBmiCounter.setText(String.format("%.2f",((prof.get(0).getWeight()/(prof.get(0).getHeight()*prof.get(0).getHeight())))* 703));

        }



        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        if(sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)!=null){
            mStepCounter = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
            isCounterSensorPresent = true;
        }else{
            textViewStepCounter.setText("Sensor Not Present");
            isCounterSensorPresent = false;
        }


        initMeActivity();
        initHomeActivity();
        initExerciseActivity();
        initNutritionActivity();
        initGraph();
        ProgressBar progress = findViewById(R.id.progressBar);
        progress.setProgress(10);


    }

    private void initDate(){
        Date currentDate = Calendar.getInstance().getTime();
        DateFormat dateFormat = android.text.format.DateFormat.getDateFormat(getApplicationContext());
        TextView date = findViewById(R.id.dateLabel);
        date.setText(dateFormat.format(currentDate));
        dateNow = dateFormat.format(currentDate);

    }

    private void initGraph(){
        BarChart barChart = findViewById(R.id.barChart);

        ArrayList<BarEntry> steps = new ArrayList<>();
        for(int i = 0; i < ped.size(); i++) {
            String str[] = ped.get(i).getDate().split("/");
            int day = Integer.parseInt(str[1]);

            steps.add(new BarEntry(day, ped.get(i).getstepCount()));

        }
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


    @Override
    public void onSensorChanged(SensorEvent event) {
        if (!lastDate.equals(dateNow)){
            pedometerDataSource.open();
            pedometer.setPedId(pedometerDataSource.getLastItemID());
            pedometer.setStepCount(stepCount);
            pedometer.setAnswer(answer);
            pedometer.setDate(lastDate);
            pedometerDataSource.updatePedometer(pedometer);
            pedometer.setPedId(-1);
            stepCount = 0;
            pedometer.setStepCount(stepCount);
            pedometer.setDate(dateNow);
            pedometer.setAnswer(answer);
            pedometerDataSource.insertItem(pedometer);
            lastDate = pedometerDataSource.getLastDate();
            pedometerDataSource.close();
        }
        if(event.sensor == mStepCounter){

            pedometerDataSource.open();
            pedometer.setPedId(pedometerDataSource.getLastItemID());
            stepCount++;
            textViewStepCounter.setText(String.valueOf(stepCount));
            textViewDistaceCounter.setText(String.format("%.2f",((float)stepCount*25)/63360)+"miles");
            pedometer.setStepCount(stepCount);
            pedometer.setAnswer(answer);
            pedometer.setDate(dateNow);
            pedometerDataSource.updatePedometer(pedometer);
            pedometerDataSource.close();
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        stepCount = sharedPreferences.getInt("stepCount", stepCount);
        if (sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)!= null)
            sensorManager.registerListener(this, mStepCounter, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.putInt("stepCount", stepCount);
        editor.apply();

    }

    @Override
    protected void onStop() {
        super.onStop();
        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.putInt("stepCount", stepCount);
        editor.apply();
    }
}
