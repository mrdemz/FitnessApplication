package com.example.fitnessapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

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


        if(!TextUtils.isEmpty(foodName.getText().toString()) && !TextUtils.isEmpty(foodSerDesc.getText().toString()) && !TextUtils.isEmpty(foodCal.getText().toString())) {

            food.setFoodName(String.valueOf(foodName.getText()));
            food.setFoodServing(String.valueOf(foodSerDesc.getText()));
            food.setCalories(Double.parseDouble(String.valueOf(foodCal.getText())));
            menuDataSource.open();
            menuDataSource.insertItem(food);
            menuDataSource.close();
            Toast.makeText(AddFoodActivity.this, "Food is added to the list", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(AddFoodActivity.this, NutritionActivity.class);

            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            startActivity(intent);


        }else{
            Toast.makeText(AddFoodActivity.this, "Please fill in all required info", Toast.LENGTH_SHORT).show();
        }

    }
}