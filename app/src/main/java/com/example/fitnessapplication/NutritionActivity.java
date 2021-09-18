package com.example.fitnessapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class NutritionActivity extends AppCompatActivity {



    private String dateNow;
    private int activityLevel = 1;
    private int fitness_goal = 1;

    ProfileDataSource profileDataSource = new ProfileDataSource(this);
    FoodDataSource foodDataSource = new FoodDataSource(this);
    ArrayList<Profile> prof = new ArrayList<Profile>();
    ArrayList<Food> foodToday = new ArrayList<Food>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutrition);
        initDate();
        initHomeActivity();
        initExerciseActivity();
        initNutritionActivity();
        initMeActivity();
        initAddMeal();
        initActionBarLogo();
        caloricUpdate();


    }

    private void initMeActivity(){

        View icon = findViewById(R.id.meView);

        icon.setOnClickListener(v -> {

            Intent intent = new Intent(NutritionActivity.this, ProfileActivity.class);

            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            startActivity(intent);

        });

    }

    private void initHomeActivity(){

        View icon = findViewById(R.id.goalView);

        icon.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                Intent intent = new Intent(NutritionActivity.this, HomeActivity.class);

                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                startActivity(intent);

            }

        });

    }

    private void initNutritionActivity(){
        View icon = findViewById(R.id.nutritionView);
        icon.setOnClickListener(v -> {
            Intent intent = new Intent(NutritionActivity.this, NutritionActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });
    }

    private void initExerciseActivity(){
        View icon = findViewById(R.id.workoutView);
        icon.setOnClickListener(v -> {
            Intent intent = new Intent(NutritionActivity.this, ExerciseActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });
    }


    private void initAddMeal(){
        View bfastBut = findViewById(R.id.addBreakfastButton);
        View lunchBut = findViewById(R.id.addLunchButton);
        View dinnerBut = findViewById(R.id.addDinnerButton);
        View snacksBut = findViewById(R.id.addSnacksButton);
        bfastBut.setOnClickListener(v -> {
            Intent intent = new Intent(NutritionActivity.this, FoodSelectionActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);


            String title = "Breakfast";
            Bundle bundle = new Bundle();
            bundle.putString("type", title);
            intent.putExtras(bundle);
            startActivity(intent);
        });
        lunchBut.setOnClickListener(v -> {
            Intent intent = new Intent(NutritionActivity.this, FoodSelectionActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            String title = "Lunch";
            Bundle bundle = new Bundle();
            bundle.putString("type", title);
            intent.putExtras(bundle);
            startActivity(intent);
        });
        dinnerBut.setOnClickListener(v -> {
            Intent intent = new Intent(NutritionActivity.this, FoodSelectionActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            String title = "Dinner";
            Bundle bundle = new Bundle();
            bundle.putString("type", title);
            intent.putExtras(bundle);
            startActivity(intent);
        });
        snacksBut.setOnClickListener(v -> {
            Intent intent = new Intent(NutritionActivity.this, FoodSelectionActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            String title = "Snacks";
            Bundle bundle = new Bundle();
            bundle.putString("type", title);
            intent.putExtras(bundle);
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



    public void caloricUpdate() {
        ProgressBar caloricPercentPb = findViewById(R.id.calorieProgressBarNutrition);
        TextView caloricRatioTv = findViewById(R.id.calRatioVal2);
        TextView percentTv = findViewById(R.id.percentTV);
        TextView type1 = findViewById(R.id.bfastValLabel);
        TextView type2 = findViewById(R.id.lunchValLabel);
        TextView type3 = findViewById(R.id.dinnerValLabel);
        TextView type4 = findViewById(R.id.snacksValLabel);
        profileDataSource.open();
        if (profileDataSource.getCount() > 0) {
            prof = profileDataSource.getProfile();
            if (prof.get(0).getGoalWeight()==1){  fitness_goal =1; }
            else if (prof.get(0).getGoalWeight()==2){  fitness_goal = 2;}
            else if (prof.get(0).getGoalWeight()==3){ fitness_goal =3;}
            else if (prof.get(0).getGoalWeight()==4){ fitness_goal = 4;}
            else if (prof.get(0).getGoalWeight()==5){  fitness_goal = 5;}
            else if (prof.get(0).getGoalWeight()==6){  fitness_goal = 6; }
            else if (prof.get(0).getGoalWeight()==7){  fitness_goal = 7; }
            profileDataSource.close();
            foodDataSource.open();
            if (foodDataSource.getCount() != 0) {
                foodToday = foodDataSource.getFood();
            }


                String bfoodList = "";
                String lfoodList = "";
                String dfoodList = "";
                String sfoodList = "";
                double progress;
                double calories = 0;
                double calorieGoal = 0;
                double actMultiplier = 1;
                double goalMultiplier = 0;
                double weight = prof.get(0).getWeight();
                double height = prof.get(0).getHeight();
                double age = prof.get(0).getAge();
                double gender = prof.get(0).getGender();
                double bmrM = 66 + (6.23 * weight) + (12.7 * height) - (6.8 * age);
                double bmrF = 655 + (4.35 * weight) + (4.7 * height) - (4.7 * age);

                foodDataSource.close();
                int foodCount = foodToday.size();
                for (int i = 0; i < foodCount; i++) {
                    if (foodToday.get(i).getDate().equals(dateNow)) {
                        calories += foodToday.get(i).getCalories();
                        if(foodToday.get(i).getType()==1) {
                            bfoodList = bfoodList + foodToday.get(i).getFoodName();
                            if(i != foodCount-1){ bfoodList = bfoodList + ", ";}
                        } else if(foodToday.get(i).getType()==2) {
                            lfoodList = lfoodList + foodToday.get(i).getFoodName();
                            if(i != foodCount-1){ lfoodList = lfoodList + ", ";}
                        } else if(foodToday.get(i).getType()==3) {
                            dfoodList = dfoodList + foodToday.get(i).getFoodName();
                            if(i != foodCount-1){ dfoodList = dfoodList + ", ";}
                        } else if(foodToday.get(i).getType()==4) {
                            sfoodList = sfoodList + foodToday.get(i).getFoodName();
                            if(i != foodCount-1){ sfoodList = sfoodList + ", ";}
                        }

                    }

                }
                activityLevel = prof.get(0).getActivity();
                if (activityLevel == 1) {
                    actMultiplier = 1;
                } else if (activityLevel == 2) {
                    actMultiplier = 1.2;
                } else if (activityLevel == 3) {
                    actMultiplier = 1.375;
                } else if (activityLevel == 4) {
                    actMultiplier = 1.55;
                } else if (activityLevel == 5) {
                    actMultiplier = 1.725;
                } else if (activityLevel == 6) {
                    actMultiplier = 1.9;
                }

                if (fitness_goal == 1) {
                    goalMultiplier = -1750 / 7;
                } else if (fitness_goal == 2) {
                    goalMultiplier = -3500 / 7;
                } else if (fitness_goal == 3) {
                    goalMultiplier = -7000 / 7;
                } else if (fitness_goal == 4) {
                    goalMultiplier = 1750 / 7;
                } else if (fitness_goal == 5) {
                    goalMultiplier = 3500 / 7;
                } else if (fitness_goal == 6) {
                    goalMultiplier = 7000 / 7;
                } else if (fitness_goal == 7) {
                    goalMultiplier = 0;
                }

                if (gender == 1) {
                    calorieGoal = bmrM * actMultiplier + goalMultiplier;
                } else if (gender == 2) {
                    calorieGoal = bmrF * actMultiplier + goalMultiplier;
                }
                caloricRatioTv.setText((int)calories+" / "+(int)calorieGoal);
                progress = (calories / calorieGoal) * 100;
                caloricPercentPb.setProgress((int)progress);
                percentTv.setText(((int)progress)+"%");


                type1.setText(bfoodList);
                type2.setText(lfoodList);
                type3.setText(dfoodList);
                type4.setText(sfoodList);






        }else{
            foodDataSource.open();

            if (foodDataSource.getCount() > 0) {
                int foodCount = foodToday.size();
                double calories = 0;
                foodToday = foodDataSource.getFood();
                foodDataSource.close();
                for (int i = 0; i < foodCount; i++) {
                    if (foodToday.get(i).getDate() == dateNow) {
                        calories += foodToday.get(i).getCalories();
                    }

                }

                caloricPercentPb.setProgress(0);
                percentTv.setText("0%");
                caloricRatioTv.setText(String.valueOf((int)calories)+"Cal / -- Cal");
                foodDataSource.close();
            }

        }


    }

    private void initDate(){
        Date currentDate = Calendar.getInstance().getTime();
        DateFormat dateFormat = android.text.format.DateFormat.getDateFormat(getApplicationContext());
        dateNow = dateFormat.format(currentDate);

    }

}