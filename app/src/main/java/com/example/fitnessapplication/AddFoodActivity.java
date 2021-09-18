package com.example.fitnessapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

public class AddFoodActivity extends AppCompatActivity {


    MenuDataSource menuDataSource =  new MenuDataSource(this);
    Food food = new Food();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food);
        initCancel();
        initSave();
        initActionBarLogo();
    }



    private void initCancel(){

        View icon = findViewById(R.id.addFoodCancelButton);

        icon.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {


                finish();

            }

        });

    }

    private void initSave(){

        View icon = findViewById(R.id.addFoodSaveButton);

        icon.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {
                addMenu();
                Intent intent = new Intent(AddFoodActivity.this, NutritionActivity.class);

                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                startActivity(intent);

            }

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

    public void addMenu(){
        EditText foodName = findViewById(R.id.addFoodNameEt);
        EditText foodSerDesc = findViewById(R.id.addFoodServDesc);
        EditText foodCal = findViewById(R.id.calValueEditText);

        food.setFoodName(String.valueOf(foodName.getText()));
        food.setFoodServing(String.valueOf(foodSerDesc.getText()));
        food.setCalories(Double.parseDouble(String.valueOf(foodCal.getText())));
        menuDataSource.open();
        menuDataSource.insertItem(food);
        menuDataSource.close();
    }
}