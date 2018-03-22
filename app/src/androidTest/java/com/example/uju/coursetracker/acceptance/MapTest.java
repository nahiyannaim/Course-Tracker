package com.example.uju.coursetracker.acceptance;

import android.test.ActivityInstrumentationTestCase2;
import com.example.uju.coursetracker.presentation.MainActivity;
import com.robotium.solo.Solo;
import junit.framework.Assert;

/**
 * Created by daniel on 2018-03-22.
 * True
 */

public class MapTest extends ActivityInstrumentationTestCase2<MainActivity>{
    private Solo solo;

    public MapTest()
    {
        super(MainActivity.class);
    }

    public void setUp() throws Exception
    {
        super.setUp();
        solo = new Solo(getInstrumentation(), getActivity());

        // Disable this for full acceptance test
        // System.out.println("Injecting stub database.");
        // Services.createDataAccess(new DataAccessStub(Main.dbName));
    }

    @Override
    public void tearDown() throws Exception
    {
        super.tearDown();
        solo.finishOpenedActivities();
    }

    public void testEITCMaps(){
        solo.waitForActivity("MainActivity");
        solo.clickOnImageButton(0);
        solo.clickOnMenuItem("EITC Maps");
        solo.assertCurrentActivity("Expected activity: PickMapActivity", "PickMapActivity");


        //TEST MAP SLIDER FOR EITC E1
        solo.clickOnButton("e1_button");
        solo.assertCurrentActivity("","");                                    //check that you are on the slider activity

        Assert.assertEquals(solo.getImage(0).getContentDescription(), "e1eitc1");      //check that the right image is shown (10 times)

        solo.clickOnButton("");                                                         //click on "next" button 9 times
        Assert.assertEquals(solo.getImage(0).getContentDescription(), "e1eitc2");

        solo.clickOnButton("");
        Assert.assertEquals(solo.getImage(0).getContentDescription(), "e1eitc3");

        solo.clickOnButton("");
        Assert.assertEquals(solo.getImage(0).getContentDescription(), "e1eitc4");

        solo.clickOnButton("");
        Assert.assertEquals(solo.getImage(0).getContentDescription(), "e1eitc5");

        solo.clickOnButton("");
        Assert.assertEquals(solo.getImage(0).getContentDescription(), "e1eitc6");

        solo.clickOnButton("");
        Assert.assertEquals(solo.getImage(0).getContentDescription(), "e1eitc7");

        solo.clickOnButton("");
        Assert.assertEquals(solo.getImage(0).getContentDescription(), "e1eitc8");

        solo.clickOnButton("");
        Assert.assertEquals(solo.getImage(0).getContentDescription(), "e1eitc9");

        solo.clickOnButton("");
        Assert.assertEquals(solo.getImage(0).getContentDescription(), "e1eitc10");

        //TEST MAP SLIDER BACKWARDS (USING SLIDER "BACK" BUTTON)

        solo.clickOnButton("");                                                         //click on "prev" button 9 times
        Assert.assertEquals(solo.getImage(0).getContentDescription(), "e1eitc9");

        solo.clickOnButton("");
        Assert.assertEquals(solo.getImage(0).getContentDescription(), "e1eitc8");

        solo.clickOnButton("");
        Assert.assertEquals(solo.getImage(0).getContentDescription(), "e1eitc7");

        solo.clickOnButton("");
        Assert.assertEquals(solo.getImage(0).getContentDescription(), "e1eitc6");

        solo.clickOnButton("");
        Assert.assertEquals(solo.getImage(0).getContentDescription(), "e1eitc5");

        solo.clickOnButton("");
        Assert.assertEquals(solo.getImage(0).getContentDescription(), "e1eitc4");

        solo.clickOnButton("");
        Assert.assertEquals(solo.getImage(0).getContentDescription(), "e1eitc3");

        solo.clickOnButton("");
        Assert.assertEquals(solo.getImage(0).getContentDescription(), "e1eitc2");

        solo.clickOnButton("");
        Assert.assertEquals(solo.getImage(0).getContentDescription(), "e1eitc1");

        solo.goBack();
        // check that you are on the pick map activity
        solo.assertCurrentActivity("Expected activity: PickMapActivity","PickMapActivity");

        //TEST MAP SLIDER FOR EITC E2 /////////////////////////////////////////////////////////////////////////////////////////////////////

        solo.clickOnButton("e2_button");
        solo.assertCurrentActivity("","");                                    //check that you are on the slider activity

        Assert.assertEquals(solo.getImage(0).getContentDescription(), "e2eitc1");      //check that the right image is shown (9 times)

        solo.clickOnButton("");                                                         //click on "next" button 8 times
        Assert.assertEquals(solo.getImage(0).getContentDescription(), "e2eitc2");

        solo.clickOnButton("");
        Assert.assertEquals(solo.getImage(0).getContentDescription(), "e2eitc3");

        solo.clickOnButton("");
        Assert.assertEquals(solo.getImage(0).getContentDescription(), "e2eitc4");

        solo.clickOnButton("");
        Assert.assertEquals(solo.getImage(0).getContentDescription(), "e2eitc5");

        solo.clickOnButton("");
        Assert.assertEquals(solo.getImage(0).getContentDescription(), "e2eitc6");

        solo.clickOnButton("");
        Assert.assertEquals(solo.getImage(0).getContentDescription(), "e2eitc7");

        solo.clickOnButton("");
        Assert.assertEquals(solo.getImage(0).getContentDescription(), "e2eitc8");

        solo.clickOnButton("");
        Assert.assertEquals(solo.getImage(0).getContentDescription(), "e2eitc9");

        //TEST MAP SLIDER BACKWARDS (USING SLIDER "BACK" BUTTON)

        solo.clickOnButton("");                                                         //click on "prev" button 8 times
        Assert.assertEquals(solo.getImage(0).getContentDescription(), "e2eitc8");

        solo.clickOnButton("");
        Assert.assertEquals(solo.getImage(0).getContentDescription(), "e2eitc7");

        solo.clickOnButton("");
        Assert.assertEquals(solo.getImage(0).getContentDescription(), "e2eitc6");

        solo.clickOnButton("");
        Assert.assertEquals(solo.getImage(0).getContentDescription(), "e2eitc5");

        solo.clickOnButton("");
        Assert.assertEquals(solo.getImage(0).getContentDescription(), "e2eitc4");

        solo.clickOnButton("");
        Assert.assertEquals(solo.getImage(0).getContentDescription(), "e2eitc3");

        solo.clickOnButton("");
        Assert.assertEquals(solo.getImage(0).getContentDescription(), "e2eitc2");

        solo.clickOnButton("");
        Assert.assertEquals(solo.getImage(0).getContentDescription(), "e2eitc1");

        solo.goBack();
        // check that you are on the pick map activity
        solo.assertCurrentActivity("Expected activity: PickMapActivity","PickMapActivity");

        //TEST MAP SLIDER FOR EITC E3 /////////////////////////////////////////////////////////////////////////////////////////////////////

        solo.clickOnButton("e3_button");
        solo.assertCurrentActivity("","");                                    //check that you are on the slider activity

        Assert.assertEquals(solo.getImage(0).getContentDescription(), "e3eitc1");      //check that the right image is shown (12 times)

        solo.clickOnButton("");                                                         //click on "next" button 11 times
        Assert.assertEquals(solo.getImage(0).getContentDescription(), "e3eitc2");

        solo.clickOnButton("");
        Assert.assertEquals(solo.getImage(0).getContentDescription(), "e3eitc3");

        solo.clickOnButton("");
        Assert.assertEquals(solo.getImage(0).getContentDescription(), "e3eitc4");

        solo.clickOnButton("");
        Assert.assertEquals(solo.getImage(0).getContentDescription(), "e3eitc5");

        solo.clickOnButton("");
        Assert.assertEquals(solo.getImage(0).getContentDescription(), "e3eitc6");

        solo.clickOnButton("");
        Assert.assertEquals(solo.getImage(0).getContentDescription(), "e3eitc7");

        solo.clickOnButton("");
        Assert.assertEquals(solo.getImage(0).getContentDescription(), "e3eitc8");

        solo.clickOnButton("");
        Assert.assertEquals(solo.getImage(0).getContentDescription(), "e3eitc9");

        solo.clickOnButton("");
        Assert.assertEquals(solo.getImage(0).getContentDescription(), "e3eitc10");

        solo.clickOnButton("");
        Assert.assertEquals(solo.getImage(0).getContentDescription(), "e3eitc11");

        solo.clickOnButton("");
        Assert.assertEquals(solo.getImage(0).getContentDescription(), "e3eitc12");

        //TEST MAP SLIDER BACKWARDS (USING SLIDER "BACK" BUTTON)
        solo.clickOnButton("");
        Assert.assertEquals(solo.getImage(0).getContentDescription(), "e3eitc11");        //click on "prev" button 11 times

        solo.clickOnButton("");
        Assert.assertEquals(solo.getImage(0).getContentDescription(), "e3eitc10");

        solo.clickOnButton("");
        Assert.assertEquals(solo.getImage(0).getContentDescription(), "e3eitc9");

        solo.clickOnButton("");
        Assert.assertEquals(solo.getImage(0).getContentDescription(), "e3eitc8");

        solo.clickOnButton("");
        Assert.assertEquals(solo.getImage(0).getContentDescription(), "e3eitc7");

        solo.clickOnButton("");
        Assert.assertEquals(solo.getImage(0).getContentDescription(), "e3eitc6");

        solo.clickOnButton("");
        Assert.assertEquals(solo.getImage(0).getContentDescription(), "e3eitc5");

        solo.clickOnButton("");
        Assert.assertEquals(solo.getImage(0).getContentDescription(), "e3eitc4");

        solo.clickOnButton("");
        Assert.assertEquals(solo.getImage(0).getContentDescription(), "e3eitc3");

        solo.clickOnButton("");
        Assert.assertEquals(solo.getImage(0).getContentDescription(), "e3eitc2");

        solo.clickOnButton("");
        Assert.assertEquals(solo.getImage(0).getContentDescription(), "e3eitc1");

        solo.goBack();
        // check that you are on the pick map activity
        solo.assertCurrentActivity("Expected activity: PickMapActivity","PickMapActivity");

        solo.goBack();
        //check that you are on the main activity
        solo.assertCurrentActivity("Expected activity: MainActivity","MainActivity");


    }
}
