package com.example.fitnessapplication;

public class Food {
    private int foodId;
    private int type;
    private String foodName;
    private String serving;
    private String date;
    private double calories;

    public Food() {
        foodId = -1;
    }

    public int getFoodId() {

        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public String getFoodName() {

        return foodName;
    }

    public void setFoodName(String foodName) {

        this.foodName = foodName;
    }


    public String getFoodServing() {

        return serving;
    }

    public void setFoodServing(String serving) {

        this.serving = serving;
    }



    public double getCalories() {

        return calories;
    }

    public void setCalories(double calories) {

        this.calories = calories;
    }



    public int getType(){
        return type;
    }

    public void setType(int type){
        this.type = type;
    }

    public void setDate(String date) {

        this.date = date;
    }
    public String getDate() {

        return date;
    }
}
