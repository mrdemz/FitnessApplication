package com.example.fitnessapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;


import java.util.ArrayList;

public class ExerciseDataSource {
    private SQLiteDatabase database; //variables to hold instances of the database
    private ProfileDBHelper dbHelper; //variable to ref helper class

    public ExerciseDataSource(Context context) { //instantiating the helper class

        dbHelper = new ProfileDBHelper(context);
    }

    public void open() throws SQLException { //open db
        database = dbHelper.getWritableDatabase();
    }

    public void close() { //close db

        dbHelper.close();
    }


    public boolean insertItem(Exercise c) {
        boolean didSucceed = false;
        try {
            ContentValues initialValues = new ContentValues();

            initialValues.put("exercise_name", c.getExerciseName());
            initialValues.put("exercise_date", c.getDate());

            didSucceed = database.insert("exercise", null, initialValues) > 0;
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

    public ArrayList<Exercise> getExercise() {
        ArrayList<Exercise> exerciseList = new ArrayList<Exercise>();
        try {
            String query = "SELECT * from exercise";
            Cursor cursor = database.rawQuery(query, null);
            Exercise newItem;
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                newItem = new Exercise();
                newItem.setExerciseId(cursor.getInt(0));
                newItem.setExerciseName(cursor.getString(1));
                newItem.setDate(cursor.getString(2));
                exerciseList.add(newItem);
                cursor.moveToNext();
            }
            cursor.close();
        } catch (Exception exception) {
            exerciseList = new ArrayList<Exercise>();
        }
        return exerciseList;
    }



    public boolean deleteItem(int itemID) {
        boolean didDelete = false;
        try {
            /*A delete method requires the table to delete from and the where clause*/
            didDelete = database.delete("exercise", "_id=" + itemID, null) > 0;
        } catch (Exception e) {
            //Do nothing - return value is alread set to false
        }
        return didDelete;
    }

    public int getCount (){
        String count = "SELECT count(*) FROM exercise";
        Cursor mcursor = database.rawQuery(count, null);
        mcursor.moveToFirst();
        int icount = mcursor.getInt(0);
        return icount;
    }

    public boolean updateProfile(Profile c) {
        boolean didSucceed = false;
        try {
            Long rowId = (long) c.getProfileId();

            ContentValues updateValues = new ContentValues();
            updateValues.put("name", c.getName());
            updateValues.put("gender", c.getGender());
            updateValues.put("age", c.getAge());
            updateValues.put("weight", c.getWeight());
            updateValues.put("goalWeight", c.getGoalWeight());
            updateValues.put("height", c.getHeight());


            didSucceed = database.update("profile", updateValues, "_id=" + rowId, null) > 0;


        }
        catch (Exception e) {

        }
        return didSucceed;
    }


}
