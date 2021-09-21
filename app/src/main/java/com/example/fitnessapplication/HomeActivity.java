package com.example.fitnessapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.animation.ValueAnimator;
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
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;



import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import android.net.Uri;

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
    ProgressBar stepProgress;
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
        initBarChart();
        initFoodData();
        initExerciseData();
        initBMIHelp();
        initStepsHelp();
        initCaloriesHelp();


if(ContextCompat.checkSelfPermission(HomeActivity.this,
        Manifest.permission.ACTIVITY_RECOGNITION) == PackageManager.PERMISSION_GRANTED){

        }else {
        requestActivityPermission();
        }
        ProgressBar progress = findViewById(R.id.progressBar);

        stepProgress = findViewById(R.id.stepProgressBar);


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
            TextView profileName = findViewById(R.id.profileName);



            //=================================Setting Fitness Goal TextView============================//

            if (prof.get(0).getGoalWeight()==1){ tw.setText("Mild Weight Loss"); fitness_goal =1; }
            else if (prof.get(0).getGoalWeight()==2){ tw.setText("Weight Loss"); fitness_goal = 2;}
            else if (prof.get(0).getGoalWeight()==3){ tw.setText("Extreme Weight Loss"); fitness_goal =3;}
            else if (prof.get(0).getGoalWeight()==4){ tw.setText("Mild Weight Gain"); fitness_goal = 4;}
            else if (prof.get(0).getGoalWeight()==5){ tw.setText("Weight Gain"); fitness_goal = 5;}
            else if (prof.get(0).getGoalWeight()==6){ tw.setText("Extreme Weight Gain"); fitness_goal = 6; }
            else if (prof.get(0).getGoalWeight()==7){ tw.setText("Maintain Weight"); fitness_goal = 7; }




            tg.setText(String.valueOf((int)prof.get(0).getWeight())+" lb");
            profileName.setText("Hello "+prof.get(0).getName()+"!");
            double bmiTemp = (prof.get(0).getWeight()/(prof.get(0).getHeight()*prof.get(0).getHeight()))* 703;
            String bmiString = String.format("%.1f",bmiTemp);
            textViewBmiCounter.setText(bmiString);

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

        exerciseUpdate();


        profileDataSource.open();
                if(profileDataSource.getCount()==1) {
                    caloricUpdate();
                    //====Setting Daily Calories TextView when there is data====//
                    ValueAnimator animator = new ValueAnimator();
                    ValueAnimator animator2 = new ValueAnimator();
                    int x  = (int)dailyCalorieIntake;
                    int y = (int)calorieProgressPercentile;
                    animator.setObjectValues(0, x);
                    animator2.setObjectValues(0, y);
                    animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        public void onAnimationUpdate(ValueAnimator animation) {
                            calValRatioTv.setText(String.valueOf(animation.getAnimatedValue()) + " Cal / " + (int) dailyCalorieGoal + " Cal");
                        }
                    });
                    animator.setDuration(2000); // here you set the duration of the anim
                    animator.start();
                    animator2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        public void onAnimationUpdate(ValueAnimator animation) {
                            calPercentileTv.setText(String.valueOf(animation.getAnimatedValue()) + "%");
                            progress.setProgress((int)animation.getAnimatedValue());
                        }
                    });
                    animator2.setDuration(2000); // here you set the duration of the anim
                    animator2.start();




                    calValRatioTv.setText((int) dailyCalorieIntake + " Cal / " + (int) dailyCalorieGoal + " Cal");

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
            textViewStepCounter.setText(String.valueOf(stepCount)+" steps");


            //Miles conversion formula here, if the gender is female then multiplier is changed to 0.413
            profileDataSource.open();
            if(profileDataSource.getCount()!=0) {
                if (prof.get(0).getGender() == 2)
                    x = 0.413;
                    double y = prof.get(0).getStepsGoal();
                profileDataSource.close();
                textViewDistaceCounter.setText(String.format("%.2f", ((float) stepCount * (x * prof.get(0).getHeight())) / 63360) + "miles");
                textViewStepCounter.setText(String.valueOf(stepCount)+" / "+(int)y+" steps");

                stepProgress = findViewById(R.id.stepProgressBar);
                double p = (stepCount/y)*100;
                stepProgress.setProgress((int)p);

            }
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
                    workoutList = workoutList +" - "+ exer.get(i).getExerciseName();
                   workoutList = workoutList + "\n\n";
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
        double equation = (10*(weight*0.453592))+(6.25*(height*2.54));
        double bmrM = equation-(5*age)+5;
        double bmrF = equation -(5*age)-161;


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



    public void initBarChart(){
       ImageButton icon = findViewById(R.id.barChartButton);
        icon.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, BarGraph_Activity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });


    }
    public void initFoodData(){
        ImageButton icon = findViewById(R.id.food_data_button);
        icon.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, FoodList_Activity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });


    }
    public void initExerciseData(){
        ImageButton icon = findViewById(R.id.exercise_data_button);
        icon.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, ExerciseList_Activity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });


    }



    public void initBMIHelp(){
        ImageButton icon = findViewById(R.id.bmiHelpButton);
        icon.setOnClickListener(v -> {
         bmiHelp();
        });


    }
    public void initStepsHelp(){
        ImageButton icon = findViewById(R.id.stepshelpButton);
        icon.setOnClickListener(v -> {
           stepsHelp();
        });


    }
    public void initCaloriesHelp(){
        ImageButton icon = findViewById(R.id.caloriesHelpButton);
        icon.setOnClickListener(v -> {
            caloriesHelp();
        });


    }
    public void caloriesHelp(){
        goToUrl("https://www.calculator.net/calorie-calculator.html");

    }
    public void stepsHelp(){
        goToUrl("https://www.verywellfit.com/how-many-walking-steps-are-in-a-mile-3435916");

    }

    public void bmiHelp(){
        goToUrl("https://www.cdc.gov/healthyweight/assessing/bmi/adult_bmi/english_bmi_calculator/bmi_calculator.html");

    }
    private void goToUrl(String url){
        Uri uriUrl = Uri.parse(url);
        Intent launchBrowser = new Intent (Intent.ACTION_VIEW, uriUrl);
        startActivity(launchBrowser);
    }

}
