package com.example.uju.coursetracker.presentation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import com.example.uju.coursetracker.R;
import com.example.uju.coursetracker.business.AccessCourses;
import com.example.uju.coursetracker.objects.Course;

public class MyCoursesActivity extends AppCompatActivity
{
    private AccessCourses accessCompletedCourses;
    //private AccessCourses accessNewCourses;

    private ArrayList<Course> courseList;
    //private ArrayList<Course> newCourseList;

    private ArrayAdapter<Course> courseArrayAdapter;
    private int selectedCoursePosition = -1;

    private String completedCoursesDBName = "old";
    private String newCoursesDBName = "new";


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_courses);

        accessCompletedCourses = new AccessCourses();

        //completed courseList
        courseList = new ArrayList<Course>();
        //courseList = accessCompletedCourses.getOldCourses();
        String result = accessCompletedCourses.getCourses(courseList, completedCoursesDBName);
        if (result != null)
        {
            Messages.fatalError(this, result);
        }
        else
        {
            courseArrayAdapter = new ArrayAdapter<Course>(this, android.R.layout.simple_list_item_activated_2, android.R.id.text1, courseList)
            {
                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    View view = super.getView(position, convertView, parent);

                    TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                    TextView text2 = (TextView) view.findViewById(android.R.id.text2);

                    text1.setText(courseList.get(position).getCourseID());
                    text2.setText(courseList.get(position).getGrade());

                    return view;
                }
            };

            final ListView listView = (ListView)findViewById(R.id.CompletedCourseList);
            listView.setAdapter(courseArrayAdapter);

////            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
////                @Override
////                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
////                    Button updateButton = (Button)findViewById(R.id.buttonCourseUpdate);
////                    Button deleteButton = (Button)findViewById(R.id.buttonCourseDelete);
////
////                    if (position == selectedCoursePosition) {
////                        listView.setItemChecked(position, false);
////                        updateButton.setEnabled(false);
////                        deleteButton.setEnabled(false);
////                        selectedCoursePosition = -1;
////                    } else {
////                        listView.setItemChecked(position, true);
////                        updateButton.setEnabled(true);
////                        deleteButton.setEnabled(true);
////                        selectedCoursePosition = position;
////                        selectCourseAtPosition(position);
////                    }
////                }
////            });
//
////            final EditText editCourseID = (EditText)findViewById(R.id.editCourseID);
////            final Button buttonCourseStudents = (Button)findViewById(R.id.buttonCourseStudents);
////            editCourseID.addTextChangedListener(new TextWatcher() {
////                @Override
////                public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
////                @Override
////                public void onTextChanged(CharSequence s, int start, int before, int count) {}
////
////                @Override
////                public void afterTextChanged(Editable s) {
////                    buttonCourseStudents.setEnabled(editCourseID.getText().toString().length() > 0);
////                }
////            });
        }


        Button calculateCurrCGPAButton = findViewById(R.id.CalculateCGPAButton);
        calculateCurrCGPAButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                goToCurrentCGPA();
            }
        });




    }

    private void goToCurrentCGPA()
    {
        Intent intent = new Intent(this, CurrentCGPAActivity.class);
        startActivity(intent);
    }


}
