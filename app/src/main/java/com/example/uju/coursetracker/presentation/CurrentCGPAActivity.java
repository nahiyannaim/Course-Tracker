package com.example.uju.coursetracker.presentation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

import com.example.uju.coursetracker.R;
import com.example.uju.coursetracker.application.MainActivity;

public class CurrentCGPAActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_cgpa);


        Button button1 = findViewById(R.id.button);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToPredictedCGPA();

            }
        });
    }
    private void goToPredictedCGPA() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }





}
