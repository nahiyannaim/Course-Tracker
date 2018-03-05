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
import com.example.uju.coursetracker.application.MainActivity;
import com.example.uju.coursetracker.business.AccessCourses;
import com.example.uju.coursetracker.business.AccessReminders;
import com.example.uju.coursetracker.objects.Course;
import com.example.uju.coursetracker.objects.Reminder;
import java.util.ArrayList;


public class DueDatesActivity extends AppCompatActivity
{

    private ArrayAdapter<Reminder> reminderArrayAdapter;
    private int selectedReminder = -1;
    private AccessReminders accessReminders;
    private ArrayList<Reminder> reminderList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_due_dates);


        accessReminders = new AccessReminders();
        reminderList = new ArrayList<Reminder>();

        String result = accessReminders.getRemindersSeq(reminderList);
        if (result != null) {
            MessagesActivity.fatalError(this, result);
        } else {
            reminderArrayAdapter = new ArrayAdapter<Reminder>(this, android.R.layout.simple_list_item_activated_2, android.R.id.text1, reminderList) {
                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    View view = super.getView(position, convertView, parent);

                    TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                    TextView text2 = (TextView) view.findViewById(android.R.id.text2);

                    text1.setText(reminderList.get(position).getCourseID());
                    text2.setText(reminderList.get(position).getReminderType() + " Due on: "+reminderList.get(position).getDueDate());

                    return view;
                }
            };

            final ListView listView = (ListView) findViewById(R.id.ReminderList);
            listView.setAdapter(reminderArrayAdapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id)
                {
                    Button deleteButton = findViewById(R.id.addReminderButton2);

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
                    }
                }
            });
        }


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


    public void deleteButtonOnClick(View v)
    {

        reminderList.remove(selectedReminder);


        int pos = selectedReminder;

        if (pos >= 0)
        {
            ListView listView = (ListView) findViewById(R.id.ReminderList);
            listView.setSelection(pos);
            reminderArrayAdapter.notifyDataSetChanged();
        }

        else
        {
            MessagesActivity.warning(this, "Item to delete not found!");
        }
    }

    private void goToReminderPage()
    {
        Intent intent = new Intent(this, CreateNewReminderActivity.class);
        startActivity(intent);
    }

}