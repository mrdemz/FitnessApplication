package com.example.fitnessapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class FoodDataSource {
    private SQLiteDatabase database; //variables to hold instances of the database
    private ProfileDBHelper dbHelper; //variable to ref helper class

    public FoodDataSource(Context context) { //instantiating the helper class

        dbHelper = new ProfileDBHelper(context);
    }

    public void open() throws SQLException { //open db
        database = dbHelper.getWritableDatabase();
    }

    public void close() { //close db

        dbHelper.close();
    }


    public boolean insertItem(Food c) {
        boolean didSucceed = false;
        try {
            ContentValues initialValues = new ContentValues();
            initialValues.put("food_name", c.getFoodName());
            initialValues.put("food_type", c.getType());
            initialValues.put("food_date", c.getDate());
            initialValues.put("calories", c.getCalories());

            didSucceed = database.insert("food", null, initialValues) > 0;
        } catch (Exception e) {
            //Do nothing - will return false if there is no exception
        }
        return didSucceed;
    }

    public int getLastItemID() {

        int lastId;

        try {

            String query = "Select MAX(_ID) from item";

            Cursor cursor = database.rawQuery(query, null);

            cursor.moveToFirst();

            lastId = cursor.getInt(0);

            cursor.close();

        } catch (Exception e) {

            lastId = -1;

        }

        return lastId;

    }

    public ArrayList<Food> getFood() {
        ArrayList<Food> foodList = new ArrayList<Food>();
        try {
            String query = "SELECT * from food";
            Cursor cursor = database.rawQuery(query, null);
            Food newItem;
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                newItem = new Food();
                newItem.setFoodId(cursor.getInt(0));
                newItem.setFoodName(cursor.getString(1));
                newItem.setType(cursor.getInt(2));
                newItem.setDate(cursor.getString(3));
                newItem.setCalories(cursor.getDouble(4));
                foodList.add(newItem);
                cursor.moveToNext();
            }
            cursor.close();
        } catch (Exception exception) {
            foodList = new ArrayList<Food>();
        }
        return foodList;
    }



    public boolean deleteItem(int itemID) {
        boolean didDelete = false;
        try {
            /*A delete method requires the table to delete from and the where clause*/
            didDelete = database.delete("food", "_id=" + itemID, null) > 0;
        } catch (Exception e) {
            //Do nothing - return value is alread set to false
        }
        return didDelete;
    }

    public int getCount (){
        String count = "SELECT count(*) FROM food";
        Cursor mcursor = database.rawQuery(count, null);
        mcursor.moveToFirst();
        int icount = mcursor.getInt(0);
        return icount;
    }




}