package com.example.fitnessapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class ProfileDBHelper extends SQLiteOpenHelper { // a subclass of SQLiteOpenHelper
    private static final String DATABASE_NAME = "fitness.db"; //a static variable to name the db
    private static final int DATABASE_VERSION = 1; //a variable to hold the version number

    //Database creation sql statement
    private static final String CREATE_TABLE_SHOW = //a string variable for query
            "create table profile (_id integer primary key autoincrement, "
                    + "name text not null, "
                    + "gender int not null, "
                    + "age int not null, "
                    + "weight decimal not null, "
                    + "fitness_goal int not null, "
                    + "height decimal not null, "
                    + "steps_goal int not null, "
                    + "activity int not null);";

    private static final String CREATE_TABLE_PEDOMETER = //a string variable for query
            "create table pedometer (_id integer primary key autoincrement, "
                    + "date text not null, "
                    + "answer text not null, "
                    + "steps int not null);";

    private static final String CREATE_TABLE_EXERCISE = //a string variable for query
            "create table exercise (_id integer primary key autoincrement, "
                    + "exercise_name not null, "
                    + "exercise_date text not null);";

    private static final String CREATE_TABLE_FOOD = //a string variable for query
            "create table food (_id integer primary key autoincrement, "
                    + "food_name text not null, "
                    + "food_type int not null, "
                    + "food_date text not null, "
                    + "calories decimal not null);";

    private static final String CREATE_TABLE_MENU = //a string variable for query
            "create table menu (_id integer primary key autoincrement, "
                    + "menu_name text not null, "
                    + "menu_serving text not null, "
                    + "menu_cal decimal not null);";

    public ProfileDBHelper(Context context) { // a constructor method to call the superclass constructor
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_TABLE_SHOW);
        db.execSQL(CREATE_TABLE_PEDOMETER);
        db.execSQL(CREATE_TABLE_EXERCISE);
        db.execSQL(CREATE_TABLE_FOOD);
        db.execSQL(CREATE_TABLE_MENU);
    } //method to create

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) { //method to upgrade version
        Log.w(ProfileDBHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS profile");
        db.execSQL("DROP TABLE IF EXISTS pedometer");
        db.execSQL("DROP TABLE IF EXISTS exercise");
        db.execSQL("DROP TABLE IF EXISTS food");
        db.execSQL("DROP TABLE IF EXISTS profile");
        db.execSQL("DROP TABLE IF EXISTS menu");
        onCreate(db);
    }
}