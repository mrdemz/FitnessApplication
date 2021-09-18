package com.example.fitnessapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
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
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

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
    private int STORAGE_PERMISSION_CODE = 1;
    private SensorManager sensorManager;
    private Sensor mStepCounter;
    private boolean isCounterSensorPresent;
    private int stepCount = -1;
    private int activityLevel = 1;
    private int fitness_goal = 1;
    private String dateNow;
    private String lastDate;
    private String answer = "true";
    private String lastAnswer;
    private int totalSteps = 0;

    //========================================calorie related  attributes
    private double calorieProgressPercentile;
    private double dailyCalorieIntake;
    private double dailyCalorieGoal;
    //========================================end of calorie related attributes



    private double stepProgressPercentile;


    TextView textViewStepCounter;
    TextView textViewDistaceCounter;
    TextView textViewBmiCounter;
    TextView calPercentileTv;
    TextView calValRatioTv;


    private Pedometer pedometer = new Pedometer ();
    ProfileDataSource profileDataSource = new ProfileDataSource(this);
    PedometerDataSource pedometerDataSource = new PedometerDataSource(this);
    FoodDataSource foodDataSource = new FoodDataSource(this);
    ExerciseDataSource exerciseDataSource = new ExerciseDataSource(this);
    ArrayList<Exercise>exer = new ArrayList<Exercise>();
    ArrayList<Profile> prof = new ArrayList<>();
    ArrayList<Food> foodToday = new ArrayList<Food>();
    ArrayList<Pedometer> ped = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initDate();
        initActionBarLogo();


if(ContextCompat.checkSelfPermission(HomeActivity.this,
        Manifest.permission.ACTIVITY_RECOGNITION) == PackageManager.PERMISSION_GRANTED){

        }else {
        requestActivityPermission();
        }
        ProgressBar progress = findViewById(R.id.progressBar);
        ProgressBar stepProgress  = new ProgressBar(this );
        stepProgress = findViewById(R.id.stepProgressBar);
        stepProgress.setProgress(50);

        textViewStepCounter = findViewById(R.id.stepsValueLabel);
        textViewDistaceCounter = findViewById(R.id.todayMilesVal);
        textViewBmiCounter = findViewById(R.id.bmiValue);
        calPercentileTv = findViewById(R.id.progressTV);
        calValRatioTv = findViewById(R.id.calRatioVal);


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



            TextView tg = findViewById(R.id.curWeightVal);

            tg.setText("-- lb");
            textViewBmiCounter.setText("--");

            //====Setting Daily Calories TextView when 0 data====//

            calPercentileTv.setText("0%");
            calValRatioTv.setText("-- Cal/-- Cal");
            progress.setProgress(0);
            //=====================================================//




            //====Asking the user if he/she wants to edit his/her profile===========//
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
            //=================================================================================//


        }
        else if(profileDataSource.getCount()==1){




            prof =  profileDataSource.getProfile();
            TextView tw = findViewById(R.id.weightLabelVal);
            TextView tg = findViewById(R.id.curWeightVal);



            //=================================Setting Fitness Goal TextView============================//

            if (prof.get(0).getGoalWeight()==1){ tw.setText("Mild Weight Loss"); fitness_goal =1; }
            else if (prof.get(0).getGoalWeight()==2){ tw.setText("Weight Loss"); fitness_goal = 2;}
            else if (prof.get(0).getGoalWeight()==3){ tw.setText("Extreme Weight Loss"); fitness_goal =3;}
            else if (prof.get(0).getGoalWeight()==4){ tw.setText("Mild Weight Gain"); fitness_goal = 4;}
            else if (prof.get(0).getGoalWeight()==5){ tw.setText("Weight Gain"); fitness_goal = 5;}
            else if (prof.get(0).getGoalWeight()==6){ tw.setText("Extreme Weight Gain"); fitness_goal = 6; }
            else if (prof.get(0).getGoalWeight()==7){ tw.setText("Maintain Weight"); fitness_goal = 7; }
            tg.setText(String.valueOf((int)prof.get(0).getWeight())+" lb");

            double bmiTemp = (prof.get(0).getWeight()/(prof.get(0).getHeight()*prof.get(0).getHeight()))* 703;
            textViewBmiCounter.setText(String.valueOf((int)bmiTemp));

        }
            //=========================================================================================//


        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        if(sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)!=null){
            mStepCounter = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
            isCounterSensorPresent = true;
        }else{
            textViewStepCounter.setText("Sensor Not Present");
            isCounterSensorPresent = false;
        }


        foodDataSource.open();
if (foodDataSource.getCount()>0){
    foodToday = foodDataSource.getFood();
}
        foodDataSource.close();


        initMeActivity();
        initHomeActivity();
        initExerciseActivity();
        initNutritionActivity();
        initGraph();
        exerciseUpdate();


        profileDataSource.open();
                if(profileDataSource.getCount()==1) {
                    caloricUpdate();
                    //====Setting Daily Calories TextView when there is data====//
                    calPercentileTv.setText((int) calorieProgressPercentile + "%");
                    calValRatioTv.setText((int) dailyCalorieIntake + " Cal / " + (int) dailyCalorieGoal + " Cal");
                    progress.setProgress((int) calorieProgressPercentile);
                    //=====================================================//

                }
                profileDataSource.close();


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
            double x = 0.415;
            pedometerDataSource.open();
            pedometer.setPedId(pedometerDataSource.getLastItemID());
            stepCount++;
            textViewStepCounter.setText(String.valueOf(stepCount));


            //Miles conversion formula here, if the gender is female then multiplier is changed to 0.413
            if (prof.get(0).getGender() == 2)
                x = 0.413;
            textViewDistaceCounter.setText(String.format("%.2f",((float)stepCount*(x*prof.get(0).getHeight()))/63360)+"miles");

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



    private void requestActivityPermission(){
        if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.ACTIVITY_RECOGNITION)){
            new AlertDialog.Builder(this)
                    .setTitle("Permission Reqiuired")
                    .setMessage("The App requires activity permission for step sensor")
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(HomeActivity.this, new String[]{Manifest.permission.ACTIVITY_RECOGNITION}, STORAGE_PERMISSION_CODE);
                        }
                    })
                    .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .create().show();

        }else{
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACTIVITY_RECOGNITION}, STORAGE_PERMISSION_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == STORAGE_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "permission granted", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "permission denied", Toast.LENGTH_SHORT).show();
            }
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



    public void exerciseUpdate(){
        TextView tvWorkout = findViewById(R.id.workoutListTv);
        String workoutList = "";
        int count = 0;
        exerciseDataSource.open();
        if(exerciseDataSource.getCount()>0){
            exer = exerciseDataSource.getExercise();
            exerciseDataSource.close();
            for(int i  = 0; i < exer.size();i++){
                if(exer.get(i).getDate().equals(dateNow)) {
                    workoutList = workoutList +" -- "+ exer.get(i).getExerciseName()+" --";
                    if ((i+1)%2==0){workoutList = workoutList + "\n\n";}
                    tvWorkout.setText(workoutList);
                    count++;
                }
            }
        }
        else if(count == 0){
            tvWorkout.setText("No workout has been done for today");
        }



    }

    public void caloricUpdate(){
        double progress;
        double calories = 0;
        double calorieGoal = 0;
        double actMultiplier = 1;
        double goalMultiplier = 0;
        double weight = prof.get(0).getWeight();
        double height = prof.get(0).getHeight();
        double age = prof.get(0).getAge();
        double gender = prof.get(0).getGender();
        double bmrM = 66 + (6.23*weight)+(12.7*height)-(6.8*age);
        double bmrF = 655 + (4.35*weight)+(4.7*height)-(4.7*age);


        int foodCount = foodToday.size();
        for (int i = 0; i< foodCount; i++){
           if(foodToday.get(i).getDate().equals(dateNow)) {
               calories += foodToday.get(i).getCalories();
           }
      
        }
        activityLevel = prof.get(0).getActivity();
        if(activityLevel == 1){ actMultiplier = 1; }
        else if(activityLevel == 2){actMultiplier = 1.2;}
        else if(activityLevel == 3){actMultiplier = 1.375;}
        else if(activityLevel == 4){actMultiplier = 1.55;}
        else if(activityLevel == 5){actMultiplier = 1.725;}
        else if(activityLevel == 6){actMultiplier = 1.9;}

        if(fitness_goal == 1){ goalMultiplier = -1750/7; }
        else if(fitness_goal == 2){goalMultiplier = -3500/7;}
        else if(fitness_goal == 3){goalMultiplier = -7000/7;}
        else if(fitness_goal == 4){goalMultiplier = 1750/7;}
        else if(fitness_goal == 5){goalMultiplier = 3500/7;}
        else if(fitness_goal == 6){goalMultiplier = 7000/7;}
        else if(fitness_goal == 7){goalMultiplier = 0;}

        if (gender==1){calorieGoal = bmrM*actMultiplier+goalMultiplier;}
        else if(gender == 2){calorieGoal = bmrF*actMultiplier+goalMultiplier;}

        progress = (calories/calorieGoal)*100;
        calorieProgressPercentile =  progress;
        dailyCalorieIntake = calories;
        dailyCalorieGoal = calorieGoal;


    }
}
