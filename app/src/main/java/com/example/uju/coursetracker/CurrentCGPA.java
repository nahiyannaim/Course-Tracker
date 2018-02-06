package com.example.uju.coursetracker;

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

public class CurrentCGPA extends AppCompatActivity {

    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_cgpa);


        //This section is to only create a navigation between home page and currCGPA page Delete it after getting the real home page
        Button but2 = (Button) findViewById(R.id.toResultsButton);

        but2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(CurrentCGPA.this, Resultspage.class));
            }
        });



//////////////////////////////////////////////////////////////

        Spinner spinner = (Spinner) findViewById(R.id.spinner1);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.spinner1_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);
//////////////////////////////////////////////////////////////////


    }


}
