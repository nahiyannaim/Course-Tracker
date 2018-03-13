package com.example.uju.coursetracker.presentation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import com.example.uju.coursetracker.R;
import com.example.uju.coursetracker.application.DatabaseService;
import com.example.uju.coursetracker.business.CalculateCurrentCGPA;
import com.example.uju.coursetracker.business.PredictNextCGPA;

public class PredictNextCGPAResultsActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_predict_next_cgpa_results);

        TextView tv = (TextView) findViewById(R.id.predictResult);
        CalculateCurrentCGPA temp = new CalculateCurrentCGPA();
        double currCGPA = temp.calculate(DatabaseService.getDataAccess(MainActivity.getDBPathName()).getCompletedCourses());
        int totalCoursesCompleted = (DatabaseService.getDataAccess(MainActivity.getDBPathName()).getCompletedCourses()).size();
        double predictedCGPA = PredictNextCGPA.calculate(DatabaseService.getDataAccess(MainActivity.getDBPathName()).getCurrentCourses(), currCGPA, totalCoursesCompleted);
        int currentCourseListSize = (DatabaseService.getDataAccess(MainActivity.getDBPathName()).getCurrentCourses()).size();

        if(currentCourseListSize <= 0)
        {
            MessagesActivity.warning(this, "No Courses in current Semester. Your predicted CGPA is the same as your current CGPA.");
        }

        if(predictedCGPA != -1.0)
        {
            tv.setText(Double.toString(predictedCGPA));
        }
        else
        {
            tv.setText(Double.toString(currCGPA)); // If there is no Courses in current semester, display the current CGPA
        }
    }
}
