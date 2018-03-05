package com.example.uju.coursetracker.presentation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.example.uju.coursetracker.R;
import com.example.uju.coursetracker.business.AccessCourses;
import com.example.uju.coursetracker.objects.Course;
import java.util.ArrayList;


public class MyAllCoursesActivity extends AppCompatActivity
{

    // For Completed Courses
    private AccessCourses accessCompletedCourses;
    private ArrayList<Course> oldCourseList;
    private ArrayAdapter<Course> completedCourseArrayAdapter;
    private String completedCoursesDBName = "old";

    // For Current Courses
    private AccessCourses accessNewCourses;
    private ArrayList<Course> newCourseList;
    private ArrayAdapter<Course> courseArrayAdapter;
    private String newCoursesDBName = "new";


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_allcourses);

        // For Completed Courses ----------------------------------------------------------------------------
        accessCompletedCourses = new AccessCourses();

        //completed course List
        oldCourseList = new ArrayList<Course>();

        String result = accessCompletedCourses.getCompletedCoursesSeq(oldCourseList);
        if (result != null) {
            MessagesActivity.fatalError(this, result);
        } else {
            completedCourseArrayAdapter = new ArrayAdapter<Course>(this, android.R.layout.simple_list_item_activated_2, android.R.id.text1, oldCourseList) {
                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    View view = super.getView(position, convertView, parent);

                    TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                    TextView text2 = (TextView) view.findViewById(android.R.id.text2);

                    text1.setText(oldCourseList.get(position).getCourseID());
                    text2.setText(oldCourseList.get(position).getGrade());

                    return view;
                }
            };

            final ListView listView = (ListView) findViewById(R.id.printCompleted);
            listView.setAdapter(completedCourseArrayAdapter);

        }

        //------------------------------------------------------------------------------------------------------------------



        // For Current Courses ---------------------------------------------------------------------------------------------
        accessNewCourses = new AccessCourses();

        //current semester courseList
        newCourseList = new ArrayList<Course>();

        String result2 = accessNewCourses.getCurrentCoursesSeq(newCourseList);
        if (result2 != null) {
            MessagesActivity.fatalError(this, result2);
        } else {
            courseArrayAdapter = new ArrayAdapter<Course>(this, android.R.layout.simple_list_item_activated_2, android.R.id.text1, newCourseList) {
                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    View view = super.getView(position, convertView, parent);

                    TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                    TextView text2 = (TextView) view.findViewById(android.R.id.text2);

                    text1.setText(newCourseList.get(position).getCourseID());
                    text2.setText(newCourseList.get(position).getGrade());

                    return view;
                }
            };

            final ListView listView2 = (ListView) findViewById(R.id.printCurrent);
            listView2.setAdapter(courseArrayAdapter);

        }

        //------------------------------------------------------------------------------------------------------------------
    }
}
