package com.example.fitnessapplication;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Exercises extends AppCompatActivity {

    ProfileDataSource dataSource;
    private ExerciseModel exerciseModel;
    ImageView image;
    TextView name, details, instructions;
    Button startB, endB, cancelB, backB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bench_press);

        image = findViewById(R.id.exercisePic);
        name = findViewById(R.id.textViewName);
        details = findViewById(R.id.details);
        instructions = findViewById(R.id.instructions);

        Bundle bundle = getIntent().getExtras();

        if (bundle != null){
            int exImage = bundle.getInt("exerciseImage");
            image.setImageResource(exImage);
        }

        String exName = bundle.getString("exerciseName");
        String exDetails = bundle.getString("exerciseDetail");
        String exInstructions = bundle.getString("exerciseInstruction");

        name.setText(exName);
        details.setText(exDetails);
        instructions.setText(exInstructions);

        backButton();
    }

    public void backButton(){
        backB = findViewById(R.id.backButton);
        backB.setOnClickListener(v->{
            Intent intent = new Intent(this, ExerciseActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });
    }

    /*public void submit(){
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
    }*/
}