package com.example.uju.coursetracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);


        //This section is to only create a navigation between home page and currCGPA page Delete it after getting the real home page
        Button but = (Button) findViewById(R.id.goToCurrButton);

        but.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(MainActivity.this, CurrentCGPA.class));
            }
        });
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////

    }
}
