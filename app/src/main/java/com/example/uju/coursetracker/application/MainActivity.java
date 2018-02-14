package com.example.uju.coursetracker.application;

import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.content.Intent;
import com.example.uju.coursetracker.R;
import com.example.uju.coursetracker.presentation.MyAllCoursesActivity;
import com.example.uju.coursetracker.presentation.MyCompletedCoursesActivity;
import com.example.uju.coursetracker.presentation.MyCurrentCoursesActivity;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener
{
    private DrawerLayout dLayout;
    private ActionBarDrawerToggle dToggle;

    //For accessing the database called MyCourses
    public static final String dbName = "MyCourses";

    //From sample project Main
    public static void startUp()
    {
        DatabaseService.createDataAccess(dbName);
    }

    public static void shutDown()
    {
        DatabaseService.closeDataAccess();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        //Everything related to home page and navigation bar
        dLayout = (DrawerLayout)findViewById(R.id.drawer);
        dToggle = new ActionBarDrawerToggle(MainActivity.this,dLayout,R.string.open,R.string.close);
        dLayout.addDrawerListener(dToggle);
        dToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); 
        NavigationView navigationView = (NavigationView)findViewById(R.id.nav_v);
        navigationView.setNavigationItemSelectedListener(this);

        //starts up the database
       startUp();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        //close the database
        shutDown();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(dToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {

            case R.id.cgpaCalc:
                Intent calc = new Intent(MainActivity.this, MyCompletedCoursesActivity.class);
                startActivity(calc);
                break;

            case R.id.cgpaPred:
                Intent res = new Intent(MainActivity.this, MyCurrentCoursesActivity.class);
                startActivity(res);
                break;

            case R.id.crs:
                Intent crss = new Intent(MainActivity.this, MyAllCoursesActivity.class);
                startActivity(crss);
                break;

        }

        return true;
    }

}
