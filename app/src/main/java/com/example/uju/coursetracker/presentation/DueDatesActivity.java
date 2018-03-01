package com.example.uju.coursetracker.presentation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.uju.coursetracker.R;
import com.example.uju.coursetracker.application.MainActivity;
import com.example.uju.coursetracker.business.AccessCourses;
import com.example.uju.coursetracker.objects.Course;

import java.util.ArrayList;

public class DueDatesActivity extends AppCompatActivity {

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

    }
    private void goToReminderPage() {
        Intent intent = new Intent(this, CreateNewReminderActivity.class);
        startActivity(intent);
    }
}
