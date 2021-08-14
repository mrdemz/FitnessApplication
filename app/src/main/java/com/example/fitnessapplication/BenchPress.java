package com.example.fitnessapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class BenchPress extends AppCompatActivity {


    private ExerciseModel exerciseModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bench_press);
        submit();
    }

    public void submit(){
        Button button = findViewById(R.id.submitButton);
        EditText editText = findViewById(R.id.caloryBurnttext);

        button.setOnClickListener(v -> {

            try {
                exerciseModel = new ExerciseModel(1, editText.getText().toString(), "Bench Press");
            } catch (Exception e){
                Toast.makeText(BenchPress.this, "Input numbers only", Toast.LENGTH_SHORT).show();
            }

            ProfileDataSource dataSource = new ProfileDataSource(this);
            boolean success = dataSource.insertCalories(exerciseModel);
            Toast.makeText(BenchPress.this, "Success= " + success, Toast.LENGTH_SHORT).show();

        });
    }
}