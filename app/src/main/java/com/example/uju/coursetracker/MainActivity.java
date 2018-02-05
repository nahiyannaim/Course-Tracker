package com.example.uju.coursetracker;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout dLayout;
    private ActionBarDrawerToggle dToggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        dLayout = (DrawerLayout)findViewById(R.id.drawer);
        dToggle = new ActionBarDrawerToggle(MainActivity.this,dLayout,R.string.open,R.string.close);
        dLayout.addDrawerListener(dToggle);
        dToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationView navigationView = (NavigationView)findViewById(R.id.nav_v);
        navigationView.setNavigationItemSelectedListener(this);
      
      //This section is to only create a navigation between home page and currCGPA page Delete it after getting the real home page
        Button but = (Button) findViewById(R.id.goToCurrButton);

        but.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(MainActivity.this, CurrentCGPA.class));
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(dToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.menu)
        {
            Toast.makeText(this, "Menu", Toast.LENGTH_SHORT).show();
        }
        if(id == R.id.cgpaCalc)
        {
            Toast.makeText(this, "CGPA Calculator", Toast.LENGTH_SHORT).show();
        }
        if(id == R.id.cgpaPred)
        {
            Toast.makeText(this, "CGPA Predictor", Toast.LENGTH_SHORT).show();
        }
        if(id == R.id.sched)
        {
            Toast.makeText(this, "Schedule", Toast.LENGTH_SHORT).show();
        }
        if(id == R.id.crs)
        {
            Toast.makeText(this, "Courses", Toast.LENGTH_SHORT).show();
        }
        if(id == R.id.prog)
        {
            Toast.makeText(this, "Progress", Toast.LENGTH_SHORT).show();
        }
        if(id == R.id.loc)
        {
            Toast.makeText(this, "Location", Toast.LENGTH_SHORT).show();
        }
        return false;

    }
}
