package com.example.uju.coursetracker.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
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


public class MyCompletedCoursesActivity extends Activity
{

    private AccessCourses accessCompletedCourses;
    private ArrayList<Course> courseList;
    private ArrayAdapter<Course> courseArrayAdapter;
    private int selectedCoursePosition = -1;
    private String completedCoursesDBName = "old";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_completed_courses);

        accessCompletedCourses = new AccessCourses();

        //completed courseList
        courseList = new ArrayList<Course>();
        //courseList = accessCompletedCourses.getOldCourses();
        String result = accessCompletedCourses.getCourses(courseList, completedCoursesDBName);
        if (result != null) {
            MessagesActivity.fatalError(this, result);
        } else {
            courseArrayAdapter = new ArrayAdapter<Course>(this, android.R.layout.simple_list_item_activated_2, android.R.id.text1, courseList) {
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

            final ListView listView = (ListView) findViewById(R.id.CompletedCourseList);
            listView.setAdapter(courseArrayAdapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Button updateButton = (Button)findViewById(R.id.buttonCourseUpdate);
                    Button deleteButton = (Button)findViewById(R.id.buttonCourseDelete);

                    if (position == selectedCoursePosition) {
                        listView.setItemChecked(position, false);
                        updateButton.setEnabled(false);
                        deleteButton.setEnabled(false);
                        selectedCoursePosition = -1;
                    } else {
                        listView.setItemChecked(position, true);
                        updateButton.setEnabled(true);
                        deleteButton.setEnabled(true);
                        selectedCoursePosition = position;
                        selectCourseAtPosition(position);
                    }
                }
            });

            final EditText editCourseID = (EditText)findViewById(R.id.editCourseID);

            editCourseID.addTextChangedListener(new TextWatcher()
            {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {}
                @Override
                public void afterTextChanged(Editable s) {}
            });
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


        Button predictNextCGPAButton = findViewById(R.id.goToCurrentCoursesButton);
        predictNextCGPAButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                goToCurrentCourses();
            }
        });

    }


    public void selectCourseAtPosition(int position)
    {
        Course selected = courseArrayAdapter.getItem(position);

        EditText editCourseID = (EditText)findViewById(R.id.editCourseID);
        EditText editGrade = (EditText)findViewById(R.id.editGrade);

        editCourseID.setText(selected.getCourseID());
        editGrade.setText(selected.getGrade());
    }


    public void buttonCourseCreateOnClick(View v)
    {
        Course course = createCourseFromEditText();
        String result;

        result = validateCourseData(course, true);
        if (result == null) {
            result = accessCompletedCourses.insertCompletedCourse(course);
            if (result == null) {
                accessCompletedCourses.getCourses(courseList, completedCoursesDBName);
                courseArrayAdapter.notifyDataSetChanged();
                int pos = courseList.indexOf(course);
                if (pos >= 0) {
                    ListView listView = (ListView)findViewById(R.id.CompletedCourseList);
                    listView.setSelection(pos);
                }
            } else {
                MessagesActivity.fatalError(this, result);
            }
        } else {
            MessagesActivity.warning(this, result);
        }
    }

    public void buttonCourseUpdateOnClick(View v) {
        Course course = createCourseFromEditText();

        String result;

        result = validateCourseData(course, false);
        if (result == null) {
            result = accessCompletedCourses.updateCompletedCourse(course);
            if (result == null) {
                accessCompletedCourses.getCourses(courseList, completedCoursesDBName);
                courseArrayAdapter.notifyDataSetChanged();
                int pos = courseList.indexOf(course);
                if (pos >= 0) {
                    ListView listView = (ListView)findViewById(R.id.CompletedCourseList);
                    listView.setSelection(pos);
                }
            } else {
                MessagesActivity.fatalError(this, result);
            }
        } else {
            MessagesActivity.warning(this, result);
        }
    }

    public void buttonCourseDeleteOnClick(View v) {
        Course course = createCourseFromEditText();
        String result;

        result = accessCompletedCourses.deleteCompletedCourse(course);
        if (result == null) {
            int pos = courseList.indexOf(course);
            if (pos >= 0) {
                ListView listView = (ListView) findViewById(R.id.CompletedCourseList);
                listView.setSelection(pos);
            }
            accessCompletedCourses.getCourses(courseList, completedCoursesDBName);
            courseArrayAdapter.notifyDataSetChanged();
        } else {
            MessagesActivity.warning(this, result);
        }
    }

    private Course createCourseFromEditText() {
        EditText editCourseID = (EditText)findViewById(R.id.editCourseID);
        EditText editGrade = (EditText)findViewById(R.id.editGrade);

        Course course = new Course(editCourseID.getText().toString(), "", editGrade.getText().toString());

        return course;
    }

    private String validateCourseData(Course course, boolean isNewCourse) {
        if (course.getCourseID().length() == 0)
        {
            return "Course ID required";
        }

        if (course.getGrade().length() == 0)
        {
            return "Course grade required";
        }

        if(!(course.getGrade().equals("A+") ||  course.getGrade().equals("A") ||
                course.getGrade().equals("B+") || course.getGrade().equals("B") ||
                course.getGrade().equals("C+") || course.getGrade().equals("C") ||
                course.getGrade().equals("D+") || course.getGrade().equals("D") ||
                course.getGrade().equals("F")))
        {
            return "Invalid course grade";

        }

        return null;
    }



    private void goToCurrentCGPA()
    {
        Intent intent = new Intent(this, CurrentCGPAResultsActivity.class);
        startActivity(intent);
    }


    private void goToCurrentCourses()
    {
        Intent intent = new Intent(this, MyCurrentCoursesActivity.class);
        startActivity(intent);
    }

}
