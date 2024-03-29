package com.example.uju.coursetracker.presentation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import com.example.uju.coursetracker.R;
import com.example.uju.coursetracker.business.AccessReminders;
import com.example.uju.coursetracker.objects.Reminder;
import java.util.ArrayList;

public class DueDatesActivity extends AppCompatActivity
{
    private ArrayAdapter<Reminder> reminderArrayAdapter;
    private int selectedReminder = -1;
    private AccessReminders accessReminders;
    private ArrayList<Reminder> reminderList;
    private String selectedCourseID;
    private String selectedType;
    private String selectedDate;

    private final int courseIDTextSize = 20;
    private final int dueDateTextSize = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_due_dates);

        accessReminders = new AccessReminders();
        reminderList = new ArrayList<Reminder>();

        String result = accessReminders.getRemindersSeq(reminderList);

        if (result != null)
        {
            MessagesActivity.fatalError(this, result);
        }
        else
        {
            reminderArrayAdapter = new ArrayAdapter<Reminder>(this, android.R.layout.simple_list_item_activated_2, android.R.id.text1, reminderList)
            {
                @Override
                public View getView(int position, View convertView, ViewGroup parent)
                {
                    View view = super.getView(position, convertView, parent);

                    TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                    TextView text2 = (TextView) view.findViewById(android.R.id.text2);

                    text1.setText(reminderList.get(position).getCourseID());
                    text2.setText(reminderList.get(position).getReminderType() + "\n" + "Due: "+reminderList.get(position).getDueDate());

                    text1.setTextSize(courseIDTextSize);
                    text2.setTextSize(dueDateTextSize);

                    return view;
                }
            };

            final ListView listView = (ListView) findViewById(R.id.ReminderList);
            listView.setAdapter(reminderArrayAdapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
            {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id)
                {
                    Button deleteButton = findViewById(R.id.deleteReminderButton);

                    if (position == selectedReminder)
                    {
                        listView.setItemChecked(position, false);
                        deleteButton.setEnabled(false);
                        selectedReminder = -1;
                    }
                    else
                    {
                        listView.setItemChecked(position, true);
                        deleteButton.setEnabled(true);
                        selectedReminder = position;
                        selectReminderAtPosition(position);
                    }
                }
            });
        }

        // this button would take us to create new reminders page
        Button addRemButton = findViewById(R.id.addReminderButton);
        addRemButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                goToReminderPage();
            }
        });
    }

    public void selectReminderAtPosition(int position)
    {
        Reminder selected = reminderArrayAdapter.getItem(position);

        selectedCourseID = selected.getCourseID();
        selectedType = selected.getReminderType();
        selectedDate = selected.getDueDate();
    }

    public void deleteButtonOnClick(View v)
    {
        Reminder reminder = new Reminder(selectedCourseID, selectedType, selectedDate);
        String result;

        result = accessReminders.deleteReminder(reminder);

        if (result == null)
        {
            int pos = reminderList.indexOf(reminder);

            if (pos >= 0)
            {
                ListView listView = (ListView) findViewById(R.id.ReminderList);
                listView.setSelection(pos);
            }

            accessReminders.getRemindersSeq(reminderList);
            reminderArrayAdapter.notifyDataSetChanged();
        }
        else
        {
            result = "Please select a Reminder to delete.";
            MessagesActivity.warning(this, result);
        }
    }

    private void goToReminderPage()
    {
        Intent intent = new Intent(this, CreateNewReminderActivity.class);
        startActivity(intent);
    }

    public void onBackPressed()
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}