package com.example.uju.coursetracker.presentation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.uju.coursetracker.R;
import com.example.uju.coursetracker.application.DatabaseService;
import com.example.uju.coursetracker.business.CalculateCurrentCGPA;
import com.example.uju.coursetracker.business.PredictNextCGPA;
import com.example.uju.coursetracker.objects.Course;

import java.util.ArrayList;



public class PredictNextCGPAActivity extends AppCompatActivity {


    private ArrayList<Course> currentCourses;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_predict_next_cgpa);



        TextView tv = (TextView) findViewById(R.id.predictResult);

        double currCGPA = CalculateCurrentCGPA.calculate(DatabaseService.getDataAccess("MyCourses").getOldCourses());
        int totalCoursesCompleted = (DatabaseService.getDataAccess("MyCourses").getOldCourses()).size();

        double predictedCGPA = PredictNextCGPA.calculate(DatabaseService.getDataAccess("MyCourses").getNewCourses(), currCGPA, totalCoursesCompleted);


        if(predictedCGPA != -1.0)
        {
            tv.setText(Double.toString(predictedCGPA));
        }
        else
        {
            tv.setText("Invalid Grades Entered");
        }


    }
}