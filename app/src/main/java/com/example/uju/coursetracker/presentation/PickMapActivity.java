package com.example.uju.coursetracker.presentation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.uju.coursetracker.R;

public class PickMapActivity extends AppCompatActivity {

    private ImageButton but1;
    private ImageButton but2;
    private ImageButton but3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_m);
        activateButtons();

    }

    public void activateButtons(){
        but1 = (ImageButton)findViewById(R.id.imageButton);
        but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(PickMapActivity.this,E1Activity.class);
                startActivity(intent1);
            }
        });

        but2 = (ImageButton) findViewById(R.id.imageButton2);
        but2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(PickMapActivity.this,E2Activity.class);
                startActivity(intent2);
            }
        });

        but3 = (ImageButton) findViewById(R.id.imageButton3);
        but3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent3 = new Intent(PickMapActivity.this,E3Activity.class);
                startActivity(intent3);
            }
        });
    }
}
