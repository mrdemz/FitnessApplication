package com.example.fitnessapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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




        EditText etName = findViewById(R.id.nameEditText);
        EditText etAge = findViewById(R.id.ageEditText);
        EditText etWeight = findViewById(R.id.weightEditText);
        EditText etHeight = findViewById(R.id.heightEditText);


        ProfileDataSource pds = new ProfileDataSource(this);
        pds.open();
        if(pds.getCount()==1) {
            prof = pds.getProfile();
            pds.close();





            etName.setText(prof.get(0).getName());
            etAge.setText(String.valueOf(prof.get(0).getAge()), TextView.BufferType.EDITABLE);
            etWeight.setText(String.valueOf(prof.get(0).getWeight()), TextView.BufferType.EDITABLE);
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

        /*findViewById(R.id.editTextVaccineName).setEnabled(enabled); this is possible too*/
        editName.setEnabled(enabled);
        editAge.setEnabled(enabled);
        editWeight.setEnabled(enabled);
        editHeight.setEnabled(enabled);

    }







    private void initSaveButton(){
        // declare the widget to use the listener
        Button saveButton = findViewById(R.id.saveButton);
        // set a listener
        saveButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                boolean wasSuccessful;

                ProfileDataSource dataSource = new ProfileDataSource(ProfileActivity.this);
                try {
                    dataSource.open();

                    if (dataSource.getCount() == 0) {

                        EditText etName = findViewById(R.id.nameEditText);
                        EditText etAge = findViewById(R.id.ageEditText);
                        EditText etWeight = findViewById(R.id.weightEditText);
                        EditText etHeight = findViewById(R.id.heightEditText);

                        profile.setName(etName.getText().toString());
                        profile.setAge(Integer.parseInt(etAge.getText().toString()));
                        profile.setWeight(Double.parseDouble(etWeight.getText().toString()));
                        profile.setHeight(Double.parseDouble(etHeight.getText().toString()));

                        wasSuccessful = dataSource.insertItem(profile);
                        if (wasSuccessful) {
                            int newId = dataSource.getLastItemID();
                            profile.setProfileId(newId);
                            setForEditing(false);
                            System.out.println("success");
                        }
                    } else {
                        EditText etName = findViewById(R.id.nameEditText);
                        EditText etAge = findViewById(R.id.ageEditText);
                        EditText etWeight = findViewById(R.id.weightEditText);
                        EditText etHeight = findViewById(R.id.heightEditText);

                        profile.setName(etName.getText().toString());
                        profile.setAge(Integer.parseInt(etAge.getText().toString()));
                        profile.setWeight(Double.parseDouble(etWeight.getText().toString()));
                        profile.setHeight(Double.parseDouble(etHeight.getText().toString()));
                        profile.setProfileId(prof.get(0).getProfileId());
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


}