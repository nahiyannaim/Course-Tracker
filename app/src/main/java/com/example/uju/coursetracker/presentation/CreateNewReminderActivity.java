package com.example.uju.coursetracker.presentation;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.uju.coursetracker.R;
import com.example.uju.coursetracker.business.AccessCourses;
import com.example.uju.coursetracker.objects.Course;
import com.example.uju.coursetracker.objects.Reminder;

import java.util.ArrayList;
import android.widget.AdapterView.OnItemSelectedListener;


public class CreateNewReminderActivity extends AppCompatActivity {

    private AccessCourses accessNewCourses;
    private ArrayList<Course> newCourseList;
    private ArrayAdapter<Course> courseArrayAdapter;
    private ArrayAdapter<CharSequence> RemTypeAdapter;
    private String newCoursesDBName = "new";
    private Course selectedCourse;
    private EditText selectedRemType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_reminder);
      
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

                    TextView text1 = view.findViewById(android.R.id.text1);
                    TextView text2 = view.findViewById(android.R.id.text2);

                    text1.setText(newCourseList.get(position).getCourseID());
                    text2.setText(newCourseList.get(position).getGrade());

                    return view;
                }
            };

            final ListView listView2 = findViewById(R.id.printComplCourses);
            listView2.setAdapter(courseArrayAdapter);

            createSpinner();

        }


        Button doneButton = findViewById(R.id.RemDoneButton);
        doneButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                goToDueDatesPage();
            }
        });
    }

    private void createSpinner()
    {
        Spinner remTypeSpinner = findViewById(R.id.RemSpinner);
        final ArrayAdapter<CharSequence> RemTypeAdapter = ArrayAdapter.createFromResource(this,
                R.array.reminderTypes, android.R.layout.simple_spinner_item);
        RemTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        remTypeSpinner.setAdapter(RemTypeAdapter);
        remTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {

                CharSequence remTyp = RemTypeAdapter.getItem(pos);
                selectedRemType = findViewById(R.id.editText2);
                selectedRemType.setText(remTyp.toString());

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });

    }

    private void goToDueDatesPage() {
        Intent intent = new Intent(this, DueDatesActivity.class);
        startActivity(intent);
    }


}
