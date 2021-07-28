package com.example.fitnessapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class ProfileDataSource {
    private SQLiteDatabase database; //variables to hold instances of the database
    private ProfileDBHelper dbHelper; //variable to ref helper class

    public ProfileDataSource(Context context) { //instantiating the helper class

        dbHelper = new ProfileDBHelper(context);
    }

    public void open() throws SQLException { //open db
        database = dbHelper.getWritableDatabase();
    }

    public void close() { //close db

        dbHelper.close();
    }


    public boolean insertItem(Profile c) {
        boolean didSucceed = false;
        try {
            ContentValues initialValues = new ContentValues();

            initialValues.put("name", c.getName());
            initialValues.put("age", c.getAge());
            initialValues.put("weight", c.getWeight());
            initialValues.put("height", c.getHeight());
            didSucceed = database.insert("profile", null, initialValues) > 0;
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

    public ArrayList<Profile> getProfile() {
        ArrayList<Profile> profileList = new ArrayList<Profile>();
        try {
            String query = "SELECT * from profile";
            Cursor cursor = database.rawQuery(query, null);
            Profile newItem;
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                newItem = new Profile();
                newItem.setProfileId(cursor.getInt(0));
                newItem.setName(cursor.getString(1));
                newItem.setAge(cursor.getInt(2));
                newItem.setWeight(cursor.getDouble(3));
                newItem.setHeight(cursor.getDouble(4));
                profileList.add(newItem);
                cursor.moveToNext();
            }
            cursor.close();
        } catch (Exception exception) {
            profileList = new ArrayList<Profile>();
        }
        return profileList;
    }

    public ArrayList<Double> getPrices() {
        ArrayList<Double> itemList = new ArrayList<Double>();
        try {
            String query = "SELECT * from item";
            Cursor cursor = database.rawQuery(query, null);
            double newItem;
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {

                newItem = cursor.getDouble(2);
                itemList.add(newItem);
                cursor.moveToNext();
            }
            cursor.close();
        } catch (Exception exception) {
            itemList = new ArrayList<Double>();
        }
        return itemList;
    }

    public boolean deleteItem(int itemID) {
        boolean didDelete = false;
        try {
            /*A delete method requires the table to delete from and the where clause*/
            didDelete = database.delete("item", "_id=" + itemID, null) > 0;
        } catch (Exception e) {
            //Do nothing - return value is alread set to false
        }
        return didDelete;
    }

    public int getCount (){
        String count = "SELECT count(*) FROM profile";
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
            updateValues.put("age", c.getAge());
            updateValues.put("weight", c.getWeight());
            updateValues.put("height", c.getHeight());


            didSucceed = database.update("profile", updateValues, "_id=" + rowId, null) > 0;


        }
        catch (Exception e) {

        }
        return didSucceed;
    }

}


