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

public class DueDatesActivity extends AppCompatActivity {
//
//    private AccessCourses accessNewCourses;
//    private ArrayList<Course> newCourseList;
//    private ArrayAdapter<Course> courseArrayAdapter;
//    private String newCoursesDBName = "new";
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_due_dates);
//
//        accessNewCourses = new AccessCourses();
//
//        //current semester courseList
//        newCourseList = new ArrayList<Course>();
//
//        String result2 = accessNewCourses.getCourses(newCourseList, newCoursesDBName);
//        if (result2 != null) {
//            MessagesActivity.fatalError(this, result2);
//        } else {
//            courseArrayAdapter = new ArrayAdapter<Course>(this, android.R.layout.simple_list_item_activated_2, android.R.id.text1, newCourseList) {
//                @Override
//                public View getView(int position, View convertView, ViewGroup parent) {
//                    View view = super.getView(position, convertView, parent);
//
//                    TextView text1 = (TextView) view.findViewById(android.R.id.text1);
//                    TextView text2 = (TextView) view.findViewById(android.R.id.text2);
//
//                    text1.setText(newCourseList.get(position).getCourseID());
//                    text2.setText(newCourseList.get(position).getGrade());
//
//                    return view;
//                }
//            };
//
//            final ListView listView2 = (ListView) findViewById(R.id.printComplCourses);
//            listView2.setAdapter(courseArrayAdapter);
//
//        }
//    }
}
