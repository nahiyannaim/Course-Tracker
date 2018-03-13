package com.example.uju.coursetracker.presentation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.example.uju.coursetracker.R;
import com.example.uju.coursetracker.business.AccessCourses;
import com.example.uju.coursetracker.objects.Course;
import java.util.ArrayList;


public class MyCurrentCoursesActivity extends AppCompatActivity {

    private AccessCourses accessNewCourses;
    private ArrayList<Course> newCourseList;
    private ArrayAdapter<Course> courseArrayAdapter;
    private int selectedCoursePosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_current_courses);

        accessNewCourses = new AccessCourses();
        newCourseList = new ArrayList<Course>();

        String result = accessNewCourses.getCurrentCoursesSeq(newCourseList);
        if (result != null)
        {
            MessagesActivity.fatalError(this, result);
        }
        else
        {
            courseArrayAdapter = new ArrayAdapter<Course>(this, android.R.layout.simple_list_item_activated_2, android.R.id.text1, newCourseList)
            {
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

            final ListView listView = (ListView) findViewById(R.id.CurrentCourseList);
            listView.setAdapter(courseArrayAdapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Button updateButton = (Button)findViewById(R.id.buttonCourseUpdate2);
                    Button deleteButton = (Button)findViewById(R.id.buttonCourseDelete2);

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

            final EditText editCourseID2 = (EditText)findViewById(R.id.editCourseID2);

            editCourseID2.addTextChangedListener(new TextWatcher()
            {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {}
                @Override
                public void afterTextChanged(Editable s) {}
            });
        }

        // this button would take us to predict results page
        Button predictCGPAButton = findViewById(R.id.PredictCGPAButton);
        predictCGPAButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                goToPredictCGPA();
            }
        });
    }

    public void selectCourseAtPosition(int position)
    {
        Course selected = courseArrayAdapter.getItem(position);

        EditText editCourseID2 = (EditText)findViewById(R.id.editCourseID2);
        EditText editGrade2 = (EditText)findViewById(R.id.editGrade2);

        editCourseID2.setText(selected.getCourseID());
        editGrade2.setText(selected.getGrade());
    }

    public void buttonCourseCreateOnClick(View v)
    {
        Course course = createCourseFromEditText();
        String result;

        result = validateCourseData(course, true);
        if (result == null)
        {
            result = accessNewCourses.insertCurrentCourse(course);
            if (result == null)
            {
                accessNewCourses.getCurrentCoursesSeq(newCourseList);
                courseArrayAdapter.notifyDataSetChanged();
                int pos = newCourseList.indexOf(course);
                if (pos >= 0)
                {
                    ListView listView = (ListView)findViewById(R.id.CurrentCourseList);
                    listView.setSelection(pos);
                }
            }
            else
            {
                MessagesActivity.fatalError(this, result);
            }
        }
        else
        {
            MessagesActivity.warning(this, result);
        }
    }

    public void buttonCourseUpdateOnClick(View v)
    {
        Course course = createCourseFromEditText();

        String result;

        result = validateCourseData(course, false);
        if (result == null)
        {
            result = accessNewCourses.updateCurrentCourse(course);
            if (result == null)
            {
                accessNewCourses.getCurrentCoursesSeq(newCourseList);
                courseArrayAdapter.notifyDataSetChanged();
                int pos = newCourseList.indexOf(course);
                if (pos >= 0)
                {
                    ListView listView = (ListView)findViewById(R.id.CurrentCourseList);
                    listView.setSelection(pos);
                }
            }
            else
            {
                MessagesActivity.fatalError(this, result);
            }
        }
        else
        {
            MessagesActivity.warning(this, result);
        }
    }

    public void buttonCourseDeleteOnClick(View v)
    {
        Course course = createCourseFromEditText();
        String result;

        result = accessNewCourses.deleteCurrentCourse(course);
        if (result == null)
        {
            int pos = newCourseList.indexOf(course);
            if (pos >= 0)
            {
                ListView listView = (ListView) findViewById(R.id.CurrentCourseList);
                listView.setSelection(pos);
            }
            accessNewCourses.getCurrentCoursesSeq(newCourseList);
            courseArrayAdapter.notifyDataSetChanged();
        }
        else
        {
            MessagesActivity.warning(this, result);
        }
    }

    private Course createCourseFromEditText()
    {
        EditText editCourseID2 = (EditText)findViewById(R.id.editCourseID2);
        EditText editGrade2 = (EditText)findViewById(R.id.editGrade2);

        Course course = new Course(editCourseID2.getText().toString(), "", editGrade2.getText().toString());

        return course;
    }

    private String validateCourseData(Course course, boolean isNewCourse)
    {
        if (course.getCourseID().length() == 0)
        {
            return "Course ID required";
        }

        if (course.getGrade().length() == 0)
        {
            return "Course grade required";
        }
        if( !(course.getGrade().equals("A+") ||  course.getGrade().equalsIgnoreCase("A") ||
                course.getGrade().equals("B+") || course.getGrade().equalsIgnoreCase("B") ||
                course.getGrade().equals("C+") || course.getGrade().equalsIgnoreCase("C") ||
                course.getGrade().equals("D+") || course.getGrade().equalsIgnoreCase("D") ||
                course.getGrade().equalsIgnoreCase("F"))  && !(( course.getGrade().equals("a+") ||  course.getGrade().equals("b+") || course.getGrade().equals("c+")) ))
        {
            return "Invalid course grade";

        }

        return null;
    }

    private void goToPredictCGPA()
    {
        Intent intent = new Intent(this, PredictNextCGPAResultsActivity.class);
        startActivity(intent);
    }
}