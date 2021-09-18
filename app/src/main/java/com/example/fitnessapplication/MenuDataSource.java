package com.example.fitnessapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class MenuDataSource {
    private SQLiteDatabase database; //variables to hold instances of the database
    private ProfileDBHelper dbHelper; //variable to ref helper class

    public MenuDataSource(Context context) { //instantiating the helper class

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

            initialValues.put("menu_name", c.getFoodName());
            initialValues.put("menu_serving", c.getFoodServing());
            initialValues.put("menu_cal", c.getCalories());

            didSucceed = database.insert("menu", null, initialValues) > 0;
        } catch (Exception e) {
            //Do nothing - will return false if there is no exception
        }
        return didSucceed;
    }

    public int getLastItemID() {

        int lastId;

        try {

            String query = "Select MAX(_ID) from menu";

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
            String query = "SELECT * from menu";
            Cursor cursor = database.rawQuery(query, null);
            Food newItem;
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                newItem = new Food();
                newItem.setFoodId(cursor.getInt(0));
                newItem.setFoodName(cursor.getString(1));
                newItem.setFoodServing(cursor.getString(2));
                newItem.setCalories(cursor.getDouble(3));
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
            didDelete = database.delete("menu", "_id=" + itemID, null) > 0;
        } catch (Exception e) {
            //Do nothing - return value is alread set to false
        }
        return didDelete;
    }

    public int getCount (){
        String count = "SELECT count(*) FROM menu";
        Cursor mcursor = database.rawQuery(count, null);
        mcursor.moveToFirst();
        int icount = mcursor.getInt(0);
        return icount;
    }




}