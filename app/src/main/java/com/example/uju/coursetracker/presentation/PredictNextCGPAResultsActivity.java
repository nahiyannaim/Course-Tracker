package com.example.uju.coursetracker.presentation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import com.example.uju.coursetracker.R;
import com.example.uju.coursetracker.application.DatabaseService;
import com.example.uju.coursetracker.application.MainActivity;
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

        double currCGPA = CalculateCurrentCGPA.calculate(DatabaseService.getDataAccess(MainActivity.getDBPathName()).getCompletedCourses());
        int totalCoursesCompleted = (DatabaseService.getDataAccess(MainActivity.getDBPathName()).getCompletedCourses()).size();
        double predictedCGPA = PredictNextCGPA.calculate(DatabaseService.getDataAccess(MainActivity.getDBPathName()).getCurrentCourses(), currCGPA, totalCoursesCompleted);
        int currentCourseListSize = (DatabaseService.getDataAccess(MainActivity.getDBPathName()).getCurrentCourses()).size();

        if(currentCourseListSize <= 0)
        {
            MessagesActivity.warning(this, "No Current Courses");
        }

        if(predictedCGPA != -1.0)
        {
            tv.setText(Double.toString(predictedCGPA));
        }
        else
        {
            tv.setText("0.0");
        }

    }
}
