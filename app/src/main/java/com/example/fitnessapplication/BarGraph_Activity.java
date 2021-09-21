package com.example.fitnessapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;


import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;

public class BarGraph_Activity extends AppCompatActivity {

    ArrayList<Pedometer> ped = new ArrayList<>();
    PedometerDataSource pedometerDataSource = new PedometerDataSource(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_graph);
        initGraph();
        initBackButton();
        initActionBarLogo();
    }



    private void initGraph(){
        BarChart barChart = findViewById(R.id.barChart);
        pedometerDataSource.open();
        if (pedometerDataSource.getCount()>0) {
            ped = pedometerDataSource.getPedList();
        }

        ArrayList<BarEntry> steps = new ArrayList<>();
        for(int i = 0; i < ped.size(); i++) {
            String str[] = ped.get(i).getDate().split("/");
            int day = Integer.parseInt(str[1]);


            steps.add(new BarEntry(day, ped.get(i).getstepCount()));


        }
        BarDataSet barDataSet = new BarDataSet(steps, "Number of Steps");
        barDataSet.setColors(Color.GRAY);
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(11f);

        BarData barData = new BarData (barDataSet);

        barChart.setFitBars(true);
        barChart.setData(barData);
        barChart.getDescription().setText("Steps Progress");
        barChart.animateY(2000);


    }
    public void initBackButton(){
        View icon = findViewById(R.id.barChartBackButton);
        icon.setOnClickListener(v -> {
            Intent intent = new Intent(BarGraph_Activity.this, HomeActivity.class);
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