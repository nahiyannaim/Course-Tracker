package com.example.uju.coursetracker;

import android.app.Fragment;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;
import android.widget.Button;
import android.view.View;
import android.content.Intent;
import android.support.v4.view.GravityCompat;


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
      

    }
//test
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
                Intent calc = new Intent(MainActivity.this, CurrentCGPA.class);
                startActivity(calc);
                break;

            case R.id.cgpaPred:
                Intent res = new Intent(MainActivity.this, Resultspage.class);
                startActivity(res);
                break;


        }

        return true;
    }
}
