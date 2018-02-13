package com.example.uju.coursetracker.presentation;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.example.uju.coursetracker.R;
import com.example.uju.coursetracker.application.DatabaseService;
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


        TextView tv = (TextView) findViewById(R.id.textView4);

        double currCGPA = calculate(DatabaseService.getDataAccess("MyCourses").getOldCourses());
        if(currCGPA != -1.0)
        {
            tv.setText(Double.toString(currCGPA));
        }
        else
        {
            tv.setText("Invalid Grades Entered");
        }


    }



}
