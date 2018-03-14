package com.example.uju.coursetracker.presentation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import com.example.uju.coursetracker.R;
import com.example.uju.coursetracker.business.AccessCourses;
import com.example.uju.coursetracker.business.CalculateCurrentCGPA;
import com.example.uju.coursetracker.business.PredictNextCGPA;
import com.example.uju.coursetracker.objects.Course;
import java.util.ArrayList;

public class PredictNextCGPAResultsActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_predict_next_cgpa_results);

        TextView tv = (TextView) findViewById(R.id.predictResult);

        AccessCourses ac = new AccessCourses();
        AccessCourses ac2 = new AccessCourses();
        ArrayList<Course> list = new ArrayList();
        ArrayList<Course> list2 = new ArrayList();
        CalculateCurrentCGPA temp = new CalculateCurrentCGPA();

        ac.getCompletedCoursesSeq(list);
        ac2.getCurrentCoursesSeq(list2);

        double currCGPA = temp.calculate(list);
        int totalCoursesCompleted = list.size();
        double predictedCGPA = PredictNextCGPA.calculate(list2, currCGPA, totalCoursesCompleted);
        int currentCourseListSize = list2.size();


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
