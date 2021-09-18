package com.example.fitnessapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class FoodSelectionActivity extends AppCompatActivity {
    private String dateNow;
    ArrayList<View> contentview = new ArrayList<View>();
    private Food food = new Food();
    private ArrayList<Food> foodArrayList = new ArrayList<Food>();
    private int foodType;
    MenuDataSource menuDataSource =  new MenuDataSource(this);
   FoodDataSource foodDataSource =  new FoodDataSource(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_selection);
        Bundle bundle = getIntent().getExtras();
        String stuff = bundle.getString("type");
        TextView type = findViewById(R.id.mealTypeView);
        type.setText(stuff);

        setType(stuff);
        initDate();
        listMenu();
        initBack();
        initDone();
        initAddFood();
        initActionBarLogo();
    }




    private void initBack(){

        View icon = findViewById(R.id.backButton);

        icon.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                finish();

            }

        });

    }

    private void initDone(){

        View icon = findViewById(R.id.doneButton);

        icon.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                menuDataSource.open();
                int x = menuDataSource.getCount();
                menuDataSource.close();
                foodDataSource.open();

                for (int i = 0; i< x;i++ ){
                    CheckBox chbox = contentview.get(i).findViewById(R.id.checkBox);
                    if(chbox.isChecked()){
                        food.setFoodName(foodArrayList.get(i).getFoodName());
                        food.setCalories(foodArrayList.get(i).getCalories());
                        food.setDate(dateNow);
                        food.setType(foodType);
                        foodDataSource.insertItem(food);
                    }
                }
                foodDataSource.close();

                Intent intent = new Intent(FoodSelectionActivity.this, NutritionActivity.class);

                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                startActivity(intent);
            }

        });

    }



    private void initAddFood(){

        View icon = findViewById(R.id.addFoodButton);

        icon.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                Intent intent = new Intent(FoodSelectionActivity.this, AddFoodActivity.class);

                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                startActivity(intent);

            }

        });

    }


    public void listMenu(){


        LinearLayout contents = findViewById(R.id.contents);
        LayoutInflater inflater = LayoutInflater.from(this);
        menuDataSource.open();
if (menuDataSource.getCount()==0) {

    for (int i = 0; i < 10; i++) {
        int num = i + 1;

        String uri = "@drawable/item" + Integer.toString(num);
        String nameArr[] = getResources().getStringArray(R.array.food_name);
        String sizeArr[] = getResources().getStringArray(R.array.serving_size);
        String calArr[] = getResources().getStringArray(R.array.food_calories);

        int imageResource = getResources().getIdentifier(uri, null, getPackageName());

        contentview.add(inflater.inflate(R.layout.item_layout, contents, false));

        TextView nameView = contentview.get(i).findViewById(R.id.food_name);
        TextView servingView = contentview.get(i).findViewById(R.id.serving_amount);
        TextView calView = contentview.get(i).findViewById(R.id.cal_amount);

        nameView.setText(nameArr[i]);
        servingView.setText(sizeArr[i]);
        calView.setText(calArr[i]+" Cal");
        contents.addView(contentview.get(i));


        food.setFoodName(nameArr[i]);
        food.setCalories(Double.parseDouble(calArr[i]));
        food.setFoodServing(sizeArr[i]);

        menuDataSource.insertItem(food);

    }

}else{

    foodArrayList = menuDataSource.getFood();
    for(int i = 0; i < foodArrayList.size(); i++){
        contentview.add(inflater.inflate(R.layout.item_layout, contents, false));

        TextView nameView = contentview.get(i).findViewById(R.id.food_name);
        TextView servingView = contentview.get(i).findViewById(R.id.serving_amount);
        TextView calView = contentview.get(i).findViewById(R.id.cal_amount);
        nameView.setText(foodArrayList.get(i).getFoodName());
        servingView.setText(foodArrayList.get(i).getFoodServing());
        calView.setText(String.valueOf(foodArrayList.get(i).getCalories())+" Cal");
        contents.addView(contentview.get(i));

    }
menuDataSource.close();
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

    public void initDate(){
        Date currentDate = Calendar.getInstance().getTime();
        DateFormat dateFormat = android.text.format.DateFormat.getDateFormat(getApplicationContext());
        dateNow = dateFormat.format(currentDate);

    }

    public void setType(String type){
        if (type.equals("Breakfast")){
            foodType = 1;
        }
        else if (type.equals("Lunch")){
            foodType = 2;
        }else if (type.equals("Dinner")){
            foodType = 3;
        }else if (type.equals("Snacks")){
            foodType = 4;
        }

    }



}