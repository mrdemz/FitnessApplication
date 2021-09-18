package com.example.fitnessapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity {

    private Profile profile = new Profile ();
    ArrayList<Profile> prof = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        initializeToggleButton();
        initSaveButton();
        initHomeActivity();
        initMeActivity();
        initNutritionActivity();
        initExerciseActivity();
        initActionBarLogo();
        EditText etName = findViewById(R.id.nameEditText);
        EditText etAge = findViewById(R.id.ageEditText);
        EditText etWeight = findViewById(R.id.weightEditText);
        EditText etHeight = findViewById(R.id.heightEditText);
        EditText editSteps = findViewById(R.id.targetStepsEditText);
        RadioButton goal1 = findViewById(R.id.goal1);
        RadioButton goal2 = findViewById(R.id.goal2);
        RadioButton goal3 = findViewById(R.id.goal3);
        RadioButton goal4 = findViewById(R.id.goal4);
        RadioButton goal5 = findViewById(R.id.goal5);
        RadioButton goal6 = findViewById(R.id.goal6);
        RadioButton goal7 = findViewById(R.id.goal7);
        RadioButton maleRad = findViewById(R.id.maleRad);
        RadioButton femaleRad = findViewById(R.id.femaleRad);
        RadioButton act1 = findViewById(R.id.act1);
        RadioButton act2 = findViewById(R.id.act2);
        RadioButton act3 = findViewById(R.id.act3);
        RadioButton act4 = findViewById(R.id.act4);
        RadioButton act5 = findViewById(R.id.act5);
        RadioButton act6 = findViewById(R.id.act6);


        ProfileDataSource pds = new ProfileDataSource(this);
        pds.open();
        if(pds.getCount()==1) {
            prof = pds.getProfile();
            pds.close();
            //==============Setting basic info assigned values===========//
            etName.setText(prof.get(0).getName());
            etAge.setText(String.valueOf(prof.get(0).getAge()), TextView.BufferType.EDITABLE);
            etWeight.setText(String.valueOf(prof.get(0).getWeight()), TextView.BufferType.EDITABLE);
            editSteps.setText(String.valueOf(prof.get(0).getStepsGoal()), TextView.BufferType.EDITABLE);

            if (prof.get(0).getGender()==1){maleRad.setChecked(true);}
            else if (prof.get(0).getGender()==2){femaleRad.setChecked(true);}

            //=====Setting activity goals preferred values==========//
            if (prof.get(0).getGoalWeight()==1){goal1.setChecked(true);}
            else if (prof.get(0).getGoalWeight()==2){goal2.setChecked(true);}
            else if (prof.get(0).getGoalWeight()==3){goal3.setChecked(true);}
            else if (prof.get(0).getGoalWeight()==4){goal4.setChecked(true);}
            else if (prof.get(0).getGoalWeight()==5){goal5.setChecked(true);}
            else if (prof.get(0).getGoalWeight()==6){goal6.setChecked(true);}
            else if (prof.get(0).getGoalWeight()==7){goal7.setChecked(true);}

            if (prof.get(0).getActivity()==1){act1.setChecked(true);}
            else if (prof.get(0).getActivity()==2){act2.setChecked(true);}
            else if (prof.get(0).getActivity()==3){act3.setChecked(true);}
            else if (prof.get(0).getActivity()==4){act4.setChecked(true);}
            else if (prof.get(0).getActivity()==5){act5.setChecked(true);}
            else if (prof.get(0).getActivity()==6){act6.setChecked(true);}

            etHeight.setText(String.valueOf(prof.get(0).getHeight()), TextView.BufferType.EDITABLE);




        }

    }

    private void initializeToggleButton(){
        final ToggleButton toggleButton = (ToggleButton)findViewById(R.id.editToggle);
        toggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setForEditing(toggleButton.isChecked());
            }
        });
    }


    private void setForEditing(boolean enabled) {
        EditText editName = findViewById(R.id.nameEditText);
        EditText editAge = findViewById(R.id.ageEditText);
        EditText editWeight = findViewById(R.id.heightEditText);
        EditText editHeight = findViewById(R.id.weightEditText);
        EditText editSteps = findViewById(R.id.targetStepsEditText);
        RadioButton maleRad = findViewById(R.id.maleRad);
        RadioButton femaleRad = findViewById(R.id.femaleRad);
        RadioButton act1 = findViewById(R.id.act1);
        RadioButton act2 = findViewById(R.id.act2);
        RadioButton act3 = findViewById(R.id.act3);
        RadioButton act4 = findViewById(R.id.act4);
        RadioButton act5 = findViewById(R.id.act5);
        RadioButton act6 = findViewById(R.id.act6);


        RadioButton goal1 = findViewById(R.id.goal1);
        RadioButton goal2 = findViewById(R.id.goal2);
        RadioButton goal3 = findViewById(R.id.goal3);
        RadioButton goal4 = findViewById(R.id.goal4);
        RadioButton goal5 = findViewById(R.id.goal5);
        RadioButton goal6 = findViewById(R.id.goal6);
        RadioButton goal7 = findViewById(R.id.goal7);




        editName.setEnabled(enabled);
        editAge.setEnabled(enabled);
        editWeight.setEnabled(enabled);
        editHeight.setEnabled(enabled);
        editSteps.setEnabled(enabled);
        maleRad.setEnabled(enabled);
        femaleRad.setEnabled(enabled);
        act1.setEnabled(enabled);
        act2.setEnabled(enabled);
        act3.setEnabled(enabled);
        act4.setEnabled(enabled);
        act5.setEnabled(enabled);
        act6.setEnabled(enabled);


        goal1.setEnabled(enabled);
        goal2.setEnabled(enabled);
        goal3.setEnabled(enabled);
        goal4.setEnabled(enabled);
        goal5.setEnabled(enabled);
        goal6.setEnabled(enabled);
        goal7.setEnabled(enabled);
    }







    private void initSaveButton(){

        Button saveButton = findViewById(R.id.saveButton);

        saveButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                EditText etName = findViewById(R.id.nameEditText);
                EditText etAge = findViewById(R.id.ageEditText);
                EditText etWeight = findViewById(R.id.weightEditText);
                EditText etHeight = findViewById(R.id.heightEditText);
                EditText editSteps = findViewById(R.id.targetStepsEditText);
                RadioButton radMale = findViewById(R.id.maleRad);
                RadioButton radFemale = findViewById(R.id.femaleRad);

                RadioButton act1 = findViewById(R.id.act1);
                RadioButton act2 = findViewById(R.id.act2);
                RadioButton act3 = findViewById(R.id.act3);
                RadioButton act4 = findViewById(R.id.act4);
                RadioButton act5 = findViewById(R.id.act5);
                RadioButton act6 = findViewById(R.id.act6);


                RadioButton goal1 = findViewById(R.id.goal1);
                RadioButton goal2 = findViewById(R.id.goal2);
                RadioButton goal3 = findViewById(R.id.goal3);
                RadioButton goal4 = findViewById(R.id.goal4);
                RadioButton goal5 = findViewById(R.id.goal5);
                RadioButton goal6 = findViewById(R.id.goal6);
                RadioButton goal7 = findViewById(R.id.goal7);





                boolean wasSuccessful;

                ProfileDataSource dataSource = new ProfileDataSource(ProfileActivity.this);
                try {
                    dataSource.open();

                    if (dataSource.getCount() == 0) {
                    int gender = 1;
                    int activity = 1;
                    int goal = 1;


                        //condition for gender radio buttons
                        if(radMale.isChecked()){
                           gender = 1;
                        }
                        else if(radFemale.isChecked()){
                            gender = 2;
                        }
                        // condition for activity level radio buttons
                        if(act1.isChecked()){
                            activity = 1;
                        }
                        else if(act2.isChecked()){
                            activity = 2;
                        }
                        else if(act3.isChecked()){
                            activity = 3;
                        }
                        else if(act4.isChecked()){
                            activity = 4;
                        }
                        else if(act5.isChecked()){
                            activity = 5;
                        }
                        else if(act6.isChecked()){
                            activity = 6;

                        }

                        // condition for fitness goals radio buttons
                        if(goal1.isChecked()){
                            goal = 1;
                        }
                        else if(goal2.isChecked()){
                            goal = 2;
                        }
                        else if(goal3.isChecked()){
                            goal = 3;
                        }
                        else if(goal4.isChecked()){
                            goal = 4;
                        }
                        else if(goal5.isChecked()){
                            goal = 5;
                        }
                        else if(goal6.isChecked()){
                            goal = 6;
                        }
                        else if(goal7.isChecked()){
                            goal = 7;
                        }


                        profile.setGender(gender);
                        profile.setName(etName.getText().toString());
                        profile.setAge(Integer.parseInt(etAge.getText().toString()));
                        profile.setWeight(Double.parseDouble(etWeight.getText().toString()));
                        profile.setGoalWeight(goal);
                        profile.setHeight(Double.parseDouble(etHeight.getText().toString()));
                        profile.setActivity(activity);
                        profile.setStepsGoal(Integer.parseInt(editSteps.getText().toString()));

                        wasSuccessful = dataSource.insertItem(profile);
                        if (wasSuccessful) {
                            int newId = dataSource.getLastItemID();
                            profile.setProfileId(newId);
                            setForEditing(false);
                            System.out.println("success");
                        }
                    } else {
                        int gender = 1;
                        int activity = 1;
                        int goal = 1;

                        if(radMale.isChecked()){
                            gender = 1;
                        }
                        else if(radFemale.isChecked()){
                            gender = 2;
                        }

                        if(act1.isChecked()){
                            activity = 1;
                        }
                        else if(act2.isChecked()){
                            activity = 2;
                        }
                        else if(act3.isChecked()){
                            activity = 3;
                        }
                        else if(act4.isChecked()){
                            activity = 4;
                        }
                        else if(act5.isChecked()){
                            activity = 5;
                        }
                        else if(act6.isChecked()){
                            activity = 6;

                        }

                        // condition for fitness goals radio buttons
                        if(goal1.isChecked()){
                            goal = 1;
                        }
                        else if(goal2.isChecked()){
                            goal = 2;
                        }
                        else if(goal3.isChecked()){
                            goal = 3;
                        }
                        else if(goal4.isChecked()){
                            goal = 4;
                        }
                        else if(goal5.isChecked()){
                            goal = 5;
                        }
                        else if(goal6.isChecked()){
                            goal = 6;
                        }
                        else if(goal7.isChecked()){
                            goal = 7;
                        }
                        profile.setGender(gender);

                        profile.setName(etName.getText().toString());
                        profile.setAge(Integer.parseInt(etAge.getText().toString()));
                        profile.setWeight(Double.parseDouble(etWeight.getText().toString()));
                        profile.setGoalWeight(goal);
                        profile.setHeight(Double.parseDouble(etHeight.getText().toString()));
                        profile.setProfileId(prof.get(0).getProfileId());
                        profile.setActivity(activity);
                        profile.setStepsGoal(Integer.parseInt(editSteps.getText().toString()));

                        dataSource.updateProfile(profile);



                        setForEditing(false);

                    }
                    //close the database
                    dataSource.close();
                } catch (Exception e) {

                }

            }
        });
    }

    private void initMeActivity(){

        View icon = findViewById(R.id.meView);

        icon.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                Intent intent = new Intent(ProfileActivity.this, ProfileActivity.class);

                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                startActivity(intent);

            }

        });

    }

    private void initHomeActivity(){

        View icon = findViewById(R.id.goalView);

        icon.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                Intent intent = new Intent(ProfileActivity.this, HomeActivity.class);

                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                startActivity(intent);

            }

        });

    }

    private void initNutritionActivity(){
        View icon = findViewById(R.id.nutritionView);
        icon.setOnClickListener(v -> {
            Intent intent = new Intent(ProfileActivity.this, NutritionActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });
    }

    private void initExerciseActivity(){
        View icon = findViewById(R.id.workoutView);
        icon.setOnClickListener(v -> {
            Intent intent = new Intent(ProfileActivity.this, ExerciseActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
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


}