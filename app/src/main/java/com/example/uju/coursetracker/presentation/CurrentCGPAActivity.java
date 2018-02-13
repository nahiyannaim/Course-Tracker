package com.example.uju.coursetracker.presentation;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.example.uju.coursetracker.R;
import com.example.uju.coursetracker.application.MainActivity;
import com.example.uju.coursetracker.objects.Course;
import com.example.uju.coursetracker.persistence.StubDatabase;

import java.util.ArrayList;

import static com.example.uju.coursetracker.business.CalculateCurrentCGPA.calculate;
import static com.example.uju.coursetracker.business.PredictNextCGPA.calculate;


public class CurrentCGPAActivity extends AppCompatActivity {

    private ArrayList<Course> doneCourses;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_cgpa);

        StubDatabase stubdb = new StubDatabase("oldcrs");
        TextView tv = (TextView) findViewById(R.id.textView4);
        stubdb.open("oldcrs");
        double currCGPA = calculate(stubdb.getOldCourses());
        tv.setText(Double.toString(currCGPA));

        Button button1 = findViewById(R.id.button);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               goToPredictedCGPA();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();  // Always call the superclass method first
        setContentView(R.layout.activity_current_cgpa);

        StubDatabase stubdb = new StubDatabase("oldcrs");
        TextView tv = (TextView) findViewById(R.id.textView4);
        stubdb.open("oldcrs");
        double currCGPA = calculate(stubdb.getOldCourses());
        tv.setText(Double.toString(currCGPA));

        Button button1 = findViewById(R.id.button);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToPredictedCGPA();
            }
        });
    }


    private void goToPredictedCGPA() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }





}
