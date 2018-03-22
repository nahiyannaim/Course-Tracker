package com.example.uju.coursetracker.presentation;

import android.content.Context;
import android.content.res.AssetManager;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.content.Intent;
import com.example.uju.coursetracker.R;
import com.example.uju.coursetracker.application.DatabaseService;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener
{
    private DrawerLayout dLayout;
    private ActionBarDrawerToggle dToggle;

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //   FOR INSTANTIATING THE DATABASE
    ////////////////////////////////////////////////////////////////////////////////////////////////

    public static final String dbName = "CoursesDB"; //For accessing the database called CoursesDB
    private static String dbPathName = "database/CoursesDB";

    public static void startUp()
    {
        DatabaseService.createDataAccess(dbName);
    }

    public static void shutDown()
    {
        DatabaseService.closeDataAccess();
    }

    public static String getDBPathName()
    {
        if (dbPathName == null)
            return dbName;
        else
            return dbPathName;
    }

    public static void setDBPathName(String pathName)
    {
        System.out.println("Setting DB path to: " + pathName);
        dbPathName = pathName;
    }

    private void copyDatabaseToDevice()
    {
        final String DB_PATH = "db";

        String[] assetNames;
        Context context = getApplicationContext();
        File dataDirectory = context.getDir(DB_PATH, Context.MODE_PRIVATE);
        AssetManager assetManager = getAssets();

        try
        {

            assetNames = assetManager.list(DB_PATH);
            for (int i = 0; i < assetNames.length; i++)
            {
                assetNames[i] = DB_PATH + "/" + assetNames[i];
            }

            copyAssetsToDirectory(assetNames, dataDirectory);

            setDBPathName(dataDirectory.toString() + "/" + dbName);

        }
        catch (IOException ioe)
        {
            MessagesActivity.warning(this, "Unable to access application data: " + ioe.getMessage());
        }
    }

    public void copyAssetsToDirectory(String[] assets, File directory) throws IOException
    {
        AssetManager assetManager = getAssets();

        for (String asset : assets)
        {
            String[] components = asset.split("/");
            String copyPath = directory.toString() + "/" + components[components.length - 1];
            char[] buffer = new char[1024];
            int count;

            File outFile = new File(copyPath);

            if (!outFile.exists())
            {
                InputStreamReader in = new InputStreamReader(assetManager.open(asset));
                FileWriter out = new FileWriter(outFile);

                count = in.read(buffer);
                while (count != -1)
                {
                    out.write(buffer, 0, count);
                    count = in.read(buffer);
                }

                out.close();
                in.close();
            }
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //   FOR HOMEPAGE
    ////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        //Setup of the navigation bar in the home page
        dLayout = (DrawerLayout)findViewById(R.id.drawer);
        dToggle = new ActionBarDrawerToggle(MainActivity.this,dLayout,R.string.open,R.string.close);
        dLayout.addDrawerListener(dToggle);
        dToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); 
        NavigationView navigationView = (NavigationView)findViewById(R.id.nav_v);
        navigationView.setNavigationItemSelectedListener(this);

        //Start the database
        copyDatabaseToDevice();
        startUp();

    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();

        //close the database
        shutDown();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if(dToggle.onOptionsItemSelected(item))
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item)
    {
        int id = item.getItemId();
        switch (id)
        {

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

            case R.id.myRem:
                Intent remm = new Intent(MainActivity.this, DueDatesActivity.class);
                startActivity(remm);
                break;

            case R.id.mapPage:
                Intent mPage = new Intent(MainActivity.this, PickMapActivity.class);
                startActivity(mPage);
                break;
        }

        return true;
    }

}
