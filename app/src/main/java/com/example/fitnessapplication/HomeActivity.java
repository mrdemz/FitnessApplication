package com.example.fitnessapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


    initMeActivity();
    initGraph();
    initDate();
    ProgressBar progress = findViewById(R.id.progressBar);
    progress.setProgress(10);


    }

    private void initDate(){
        Date currentDate = Calendar.getInstance().getTime();
        DateFormat dateFormat = android.text.format.DateFormat.getDateFormat(getApplicationContext());
        TextView date = findViewById(R.id.dateLabel);
        date.setText(dateFormat.format(currentDate));


    }








    private void initGraph(){
        BarChart barChart = findViewById(R.id.barChart);

        ArrayList<BarEntry> steps = new ArrayList<>();
        steps.add(new BarEntry(01, 2111));
        steps.add(new BarEntry(02, 4200));
        steps.add(new BarEntry(03, 3000));
        steps.add(new BarEntry(04, 2113));
        steps.add(new BarEntry(05, 1345));
        steps.add(new BarEntry(06, 678));
        steps.add(new BarEntry(07, 1067));

        BarDataSet barDataSet = new BarDataSet(steps, "Number of Steps");
        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(11f);

        BarData barData = new BarData (barDataSet);

        barChart.setFitBars(true);
        barChart.setData(barData);
        barChart.getDescription().setText("Steps Progress");
        barChart.animateY(2000);


    }





    private void initMeActivity(){

        View icon = findViewById(R.id.meView);

        icon.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                Intent intent = new Intent(HomeActivity.this, meActivity.class);

                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                startActivity(intent);

            }

        });

    }


}