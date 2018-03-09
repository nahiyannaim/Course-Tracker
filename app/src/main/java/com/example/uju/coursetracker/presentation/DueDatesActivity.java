package com.example.uju.coursetracker.presentation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
import com.example.uju.coursetracker.objects.Course;
import com.example.uju.coursetracker.objects.Reminder;

import java.util.ArrayList;

public class DueDatesActivity extends AppCompatActivity {




    private ArrayList<Reminder> reminderList;
    private ArrayAdapter<Reminder> creminderArrayAdapter;
    private ArrayAdapter<Reminder> reminderArrayAdapter;
    private int selectedReminder = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_due_dates);

        Button addRemButton = findViewById(R.id.addReminderButton);
        addRemButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                goToReminderPage();
            }
        });


        reminderList = new ArrayList<Reminder>();

        for (int i = 1001; i < 1010; i++){
            Reminder e = new Reminder("COMP "+String.valueOf(i),"Assignment "+String.valueOf(i-1000),"APR. "+String.valueOf(i-995)+", 2018");
            reminderList.add(e);
        }

        if(reminderList == null){
            MessagesActivity.fatalError(this, "No reminders set!");
        }
        else {
            reminderArrayAdapter = new ArrayAdapter<Reminder>(this, android.R.layout.simple_list_item_activated_2, android.R.id.text1, reminderList) {
                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    View view = super.getView(position, convertView, parent);

                    TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                    TextView text2 = (TextView) view.findViewById(android.R.id.text2);

                    text1.setText(reminderList.get(position).getCourseID());
                    text2.setText(reminderList.get(position).getReminderType() +" Due: "+reminderList.get(position).getDueDate());


                    return view;

                }
            };

            final ListView listView = findViewById(R.id.ReminderList);
            listView.setAdapter(reminderArrayAdapter);


            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    Button deleteButton = findViewById(R.id.addReminderButton2);

                    if (position == selectedReminder) {
                        listView.setItemChecked(position, false);

                        deleteButton.setEnabled(false);
                        selectedReminder = -1;
                    } else {
                        listView.setItemChecked(position, true);

                        deleteButton.setEnabled(true);
                        selectedReminder = position;

                    }
                }
            });

        }

    }

    public void deleteButtonOnClick(View v) {
        reminderList.remove(selectedReminder);

        int pos = selectedReminder;
        if (pos >= 0) {
            ListView listView = (ListView) findViewById(R.id.ReminderList);
            listView.setSelection(pos);

            reminderArrayAdapter.notifyDataSetChanged();}
        else{
            MessagesActivity.warning(this, "Item to delete not found!");
        }
    }


    private void goToReminderPage() {
        Intent intent = new Intent(this, CreateNewReminderActivity.class);
        startActivity(intent);
    }
}

