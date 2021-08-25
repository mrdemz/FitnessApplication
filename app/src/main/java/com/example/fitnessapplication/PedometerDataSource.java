package com.example.fitnessapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class PedometerDataSource {


    private SQLiteDatabase database; //variables to hold instances of the database
    private ProfileDBHelper dbHelper; //variable to ref helper class

    public PedometerDataSource(Context context) { //instantiating the helper class

        dbHelper = new ProfileDBHelper(context);
    }

    public void open() throws SQLException { //open db
        database = dbHelper.getWritableDatabase();
    }

    public void close() { //close db

        dbHelper.close();
    }


    public boolean insertItem(Pedometer c) {
        boolean didSucceed = false;
        try {
            ContentValues initialValues = new ContentValues();

            initialValues.put("date", c.getDate());
            initialValues.put("answer", c.getAnswer());
            initialValues.put("steps", c.getstepCount());
            didSucceed = database.insert("pedometer", null, initialValues) > 0;
        } catch (Exception e) {
            //Do nothing - will return false if there is no exception
        }
        return didSucceed;
    }

    public String getLastDate() {

        String lastDate;

        try {

            String query = "Select date from pedometer where _id = (select MAX(_ID) from pedometer)";

            Cursor cursor = database.rawQuery(query, null);

            cursor.moveToFirst();

            lastDate = cursor.getString(0);

            cursor.close();

        } catch (Exception e) {

            lastDate = e.toString();

        }

        return lastDate;

    }




    public String getLastAnswer() {

        String lastAnswer;

        try {

            String query = "Select answer from pedometer where _id = (select MAX(_ID) from pedometer)";

            Cursor cursor = database.rawQuery(query, null);

            cursor.moveToFirst();

            lastAnswer = cursor.getString(0);

            cursor.close();

        } catch (Exception e) {

            lastAnswer = e.toString();

        }

        return lastAnswer;

    }


    public int getLastItemID() {

        int lastId;

        try {

            String query = "Select MAX(_ID) from pedometer";

            Cursor cursor = database.rawQuery(query, null);

            cursor.moveToFirst();

            lastId = cursor.getInt(0);

            cursor.close();

        } catch (Exception e) {

            lastId = -1;

        }

        return lastId;

    }

    public ArrayList<Pedometer> getPedList() {
        ArrayList<Pedometer> pedometerList = new ArrayList<Pedometer>();
        try {
            String query = "Select * from pedometer where _id != (Select MAX(_ID) from pedometer)";
            Cursor cursor = database.rawQuery(query, null);
            Pedometer newItem;
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                newItem = new Pedometer();
                newItem.setPedId(cursor.getInt(0));
                newItem.setDate(cursor.getString(1));
                newItem.setAnswer(cursor.getString(2));
                newItem.setStepCount(cursor.getInt(3));
                pedometerList.add(newItem);
                cursor.moveToNext();
            }
            cursor.close();
        } catch (Exception exception) {
            pedometerList = new ArrayList<Pedometer>();
        }
        return pedometerList;
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
        String count = "SELECT count(*) FROM pedometer";
        Cursor mcursor = database.rawQuery(count, null);
        mcursor.moveToFirst();
        int icount = mcursor.getInt(0);
        return icount;
    }

    public boolean updatePedometer(Pedometer c) {
        boolean didSucceed = false;
        try {
            Long rowId = (long) c.getPedId();

            ContentValues updateValues = new ContentValues();
            updateValues.put("date", c.getDate());
            updateValues.put("answer", c.getAnswer());
            updateValues.put("steps", c.getstepCount());


            didSucceed = database.update("pedometer", updateValues, "_id=" + rowId, null) > 0;


        }
        catch (Exception e) {

        }
        return didSucceed;
    }
}
