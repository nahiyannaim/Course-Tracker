package com.example.uju.coursetracker.presentation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.example.uju.coursetracker.business.AccessReminders;
import com.example.uju.coursetracker.objects.Course;
import com.example.uju.coursetracker.objects.Reminder;
import java.util.ArrayList;


public class CreateNewReminderActivity extends AppCompatActivity {

    private AccessCourses accessNewCourses;
    private ArrayList<Course> newCourseList;
    private ArrayAdapter<Course> courseArrayAdapter;

    private AccessReminders accessReminders;
    private ArrayList<Reminder> reminderList;
    private int selectedReminder = -1;
    private String selectedCourseID;
    private String selectedType;
    private String selectedDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_reminder);

        accessNewCourses = new AccessCourses();

        //current semester courses list
        newCourseList = new ArrayList<Course>();

        accessReminders = new AccessReminders();
        reminderList = new ArrayList<Reminder>();

        String result2 = accessNewCourses.getCurrentCoursesSeq(newCourseList);

        if (result2 != null)
        {
            MessagesActivity.fatalError(this, result2);
        }
        else
        {
            courseArrayAdapter = new ArrayAdapter<Course>(this, android.R.layout.simple_list_item_activated_2, android.R.id.text1, newCourseList) {
                @Override
                public View getView(int position, View convertView, ViewGroup parent)
                {
                    View view = super.getView(position, convertView, parent);

                    TextView text1 = view.findViewById(android.R.id.text1);
                    TextView text2 = view.findViewById(android.R.id.text2);

                    text1.setText(newCourseList.get(position).getCourseID());
                    text2.setText(newCourseList.get(position).getGrade());

                    return view;
                }
            };

            final ListView listView = findViewById(R.id.printComplCourses);
            listView.setAdapter(courseArrayAdapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Button doneButton = (Button)findViewById(R.id.RemDoneButton);


                    if (position == selectedReminder)
                    {
                        listView.setItemChecked(position, false);
                        doneButton.setEnabled(false);
                        selectedReminder = -1;
                    }
                    else
                    {
                        listView.setItemChecked(position, true);
                        doneButton.setEnabled(true);
                        selectedReminder = position;
                        selectReminderAtPosition(position);
                    }
                }
            });

        }

        Spinner remTypeSpinner = findViewById(R.id.RemSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.reminderTypes, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        remTypeSpinner.setAdapter(adapter);


        Button doneButton = findViewById(R.id.RemDoneButton);
//        doneButton.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View view)
//            {
//                goToDueDatesPage();
//            }
//        });
        doneButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                buttonReminderCreateOnClick(view);
                goToDueDatesPage();
            }
        });
    }

    public void selectReminderAtPosition(int position)
    {
        Course selected = courseArrayAdapter.getItem(position);
        selectedCourseID = selected.getCourseID();
    }


    public void buttonReminderCreateOnClick(View v)
    {
        EditText editDate = (EditText) findViewById(R.id.editText);
        selectedDate = (editDate.getText()).toString();

        Spinner reminderItem =(Spinner) findViewById(R.id.RemSpinner);
        selectedType = reminderItem.getSelectedItem().toString();

        Reminder reminder = new Reminder(selectedCourseID, selectedType, selectedDate);

        String result;
        result = validateDate(reminder);
        if (result == null)
        {
            result = accessReminders.insertReminder(reminder);

            if (result == null)
            {
                accessReminders.getRemindersSeq(reminderList);
                int pos = reminderList.indexOf(reminder);

                if (pos >= 0)
                {
                    ListView listView = (ListView)findViewById(R.id.ReminderList);
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

    private String validateDate(Reminder reminder)
    {
        String result = null;

        if((reminder.getDueDate()).length() == 0)
        {
            result = "Due date required";
        }

        return result;
    }

    private void goToDueDatesPage()
    {
        Intent intent = new Intent(this, DueDatesActivity.class);
        startActivity(intent);
    }
}
