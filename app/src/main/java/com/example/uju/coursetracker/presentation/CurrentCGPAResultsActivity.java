package com.example.uju.coursetracker.presentation;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import com.example.uju.coursetracker.R;
import com.example.uju.coursetracker.business.AccessCourses;
import com.example.uju.coursetracker.business.CalculateCurrentCGPA;
import com.example.uju.coursetracker.objects.Course;

import java.util.ArrayList;

public class CurrentCGPAResultsActivity extends AppCompatActivity
{
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_cgpa_results);

        CalculateCurrentCGPA temp = new CalculateCurrentCGPA();
        AccessCourses ac = new AccessCourses();
        ArrayList<Course> list = new ArrayList();

        ac.getCompletedCoursesSeq(list);
        TextView tv = (TextView) findViewById(R.id.textView4);

        double currCGPA = temp.calculate(list);

        if(list.size() <= 0)
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
