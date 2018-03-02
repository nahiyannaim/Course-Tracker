package com.example.uju.coursetracker.presentation;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import com.example.uju.coursetracker.R;
import com.example.uju.coursetracker.application.DatabaseService;
import com.example.uju.coursetracker.application.MainActivity;

import static com.example.uju.coursetracker.business.CalculateCurrentCGPA.calculate;


public class CurrentCGPAResultsActivity extends AppCompatActivity
{


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_cgpa_results);

        TextView tv = (TextView) findViewById(R.id.textView4);
        double currCGPA = calculate(DatabaseService.getDataAccess(MainActivity.getDBPathName()).getCompletedCourses());
        int completedCourseListSize = (DatabaseService.getDataAccess(MainActivity.getDBPathName()).getCompletedCourses()).size();

        if(completedCourseListSize <= 0)
        {
            MessagesActivity.warning(this, "No Completed Courses found");
        }

        if(currCGPA != -1.0)
        {
            tv.setText(Double.toString(currCGPA));
        }
        else
        {
            tv.setText("0.0");
        }

    }

}
