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

public class FoodList_Activity extends AppCompatActivity {
    FoodDataSource foodDataSource = new FoodDataSource(this);
    ArrayList<View> contentview = new ArrayList<View>();
    private Food food = new Food();
    private ArrayList<Food> foodArrayList = new ArrayList<Food>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_list);
        listFoodData();
        initBackButton();
        initActionBarLogo();
    }


    public void listFoodData() {
        String type = "";

        LinearLayout contents = findViewById(R.id.food_data_content);
        LayoutInflater inflater = LayoutInflater.from(this);
        foodDataSource.open();
        if (foodDataSource.getCount() > 0) {

            foodArrayList = foodDataSource.getFood();
            foodDataSource.close();
            for (int i = 0; i < foodArrayList.size(); i++) {
                int num = i + 1;

                String uri = "@drawable/item" + Integer.toString(num);

                if (foodArrayList.get(i).getType() == 1) {
                    type = "Breakfast";
                } else if (foodArrayList.get(i).getType() == 2) {
                    type = "Lunch";
                } else if (foodArrayList.get(i).getType() == 3) {
                    type = "Dinner";
                } else if (foodArrayList.get(i).getType() == 4) {
                    type = "Snacks";
                }


                contentview.add(inflater.inflate(R.layout.data_layout, contents, false));

                TextView nameView = contentview.get(i).findViewById(R.id.exercise_data);
                TextView servingView = contentview.get(i).findViewById(R.id.serving_data);
                TextView calView = contentview.get(i).findViewById(R.id.cal_data);
                TextView typeView = contentview.get(i).findViewById(R.id.type_data);
                TextView dateView = contentview.get(i).findViewById(R.id.exercise_date_data);


                nameView.setText(foodArrayList.get(i).getFoodName());
                servingView.setText(foodArrayList.get(i).getFoodServing());
                calView.setText(foodArrayList.get(i).getCalories() + " Cal");
                typeView.setText(type);
                dateView.setText(foodArrayList.get(i).getDate());
                contents.addView(contentview.get(i));


            }


        }


    }
    public void initBackButton(){
        Button icon = findViewById(R.id.food_data_back_button);
        icon.setOnClickListener(v -> {
            Intent intent = new Intent(FoodList_Activity.this, HomeActivity.class);
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
