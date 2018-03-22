package com.example.uju.coursetracker.acceptance;

import android.test.ActivityInstrumentationTestCase2;
import com.example.uju.coursetracker.presentation.MainActivity;
import com.robotium.solo.Solo;
import junit.framework.Assert;

/**
 * Created by daniel on 2018-03-22.
 */

public class MapTest extends ActivityInstrumentationTestCase2<MainActivity>{
    private Solo solo;

    public MapTest()
    {
        super(MainActivity.class);
    }

    public void setUp() throws Exception
    {
        solo = new Solo(getInstrumentation(), getActivity());

        // Disable this for full acceptance test
        // System.out.println("Injecting stub database.");
        // Services.createDataAccess(new DataAccessStub(Main.dbName));
    }

    @Override
    public void tearDown() throws Exception
    {
        solo.finishOpenedActivities();
    }
}
