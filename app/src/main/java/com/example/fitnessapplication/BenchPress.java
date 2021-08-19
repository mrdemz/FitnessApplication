package com.example.fitnessapplication;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class BenchPress extends AppCompatActivity {

    ProfileDataSource dataSource;
    private ExerciseModel exerciseModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bench_press);
        submit();
    }

    public void submit(){
        Button button = findViewById(R.id.submitButton);

        button.setOnClickListener(v -> {

            EditText editText = findViewById(R.id.caloryBurntText);


            try {

                int caloriesBurned = Integer.parseInt(editText.getText().toString());
                String exerciseName = "Bench Press";
                exerciseModel = new ExerciseModel(-1, caloriesBurned, exerciseName);

                dataSource = new ProfileDataSource(this);
                boolean success = dataSource.insertCalories(exerciseModel);
                Toast.makeText(BenchPress.this, "Success " + success, Toast.LENGTH_SHORT).show();

            } catch (Exception e){
                Toast.makeText(this, "Input numbers only", Toast.LENGTH_SHORT).show();

            }

            editText.setText("");

        });
    }
}