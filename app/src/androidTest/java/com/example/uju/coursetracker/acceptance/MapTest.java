package com.example.uju.coursetracker.acceptance;

import android.graphics.Point;
import android.test.ActivityInstrumentationTestCase2;
import com.example.uju.coursetracker.presentation.MainActivity;
import com.robotium.solo.Solo;
import junit.framework.Assert;
import com.example.uju.coursetracker.R;


public class MapTest extends ActivityInstrumentationTestCase2<MainActivity>
{
    private Solo solo;

    public MapTest()
    {
        super(MainActivity.class);
    }

    public void setUp() throws Exception
    {

        solo = new Solo(getInstrumentation(), getActivity());

    }

    @Override
    public void tearDown() throws Exception
    {
        super.tearDown();
    }

    public void testEITCMaps()
    {
        solo.waitForActivity("MainActivity");
        solo.clickOnImageButton(0);
        solo.clickOnMenuItem("EITC Maps");

        solo.waitForActivity("PickMapActivity");
        solo.assertCurrentActivity("Expected activity: PickMapActivity", "PickMapActivity");


        //TEST MAP SLIDER FOR EITC E1
        solo.clickOnImageButton(0);
        solo.waitForActivity("E1Activity", 120000);

        //check that you are on the slider activity
        solo.assertCurrentActivity("Expected activity: E1Activity","E1Activity");

        Point size = new Point();
        solo.getCurrentActivity().getWindowManager().getDefaultDisplay().getSize(size);

        //check that the right image is shown (10 times)

        Assert.assertTrue(solo.getCurrentActivity().getResources().getDrawable(R.drawable.facilities_e1eitc1).isVisible());

        //swipe left 9 times
        solo.drag(size.x-10, 10, size.y/2, size.y/2,1);
        Assert.assertTrue(solo.getCurrentActivity().getResources().getDrawable(R.drawable.facilities_e1eitc2).isVisible());

        solo.drag(size.x-10, 10, size.y/2, size.y/2,1);
        Assert.assertTrue(solo.getCurrentActivity().getResources().getDrawable(R.drawable.facilities_e1eitc3).isVisible());

        solo.drag(size.x-10, 10, size.y/2, size.y/2,1);
        Assert.assertTrue(solo.getCurrentActivity().getResources().getDrawable(R.drawable.facilities_e1eitc4).isVisible());

        solo.drag(size.x-10, 10, size.y/2, size.y/2,1);
        Assert.assertTrue(solo.getCurrentActivity().getResources().getDrawable(R.drawable.facilities_e1eitc5).isVisible());

        solo.drag(size.x-10, 10, size.y/2, size.y/2,1);
        Assert.assertTrue(solo.getCurrentActivity().getResources().getDrawable(R.drawable.facilities_e1eitc6).isVisible());

        solo.drag(size.x-10, 10, size.y/2, size.y/2,1);
        Assert.assertTrue(solo.getCurrentActivity().getResources().getDrawable(R.drawable.facilities_e1eitc7).isVisible());

        solo.drag(size.x-10, 10, size.y/2, size.y/2,1);
        Assert.assertTrue(solo.getCurrentActivity().getResources().getDrawable(R.drawable.facilities_e1eitc8).isVisible());

        solo.drag(size.x-10, 10, size.y/2, size.y/2,1);
        Assert.assertTrue(solo.getCurrentActivity().getResources().getDrawable(R.drawable.facilities_e1eitc9).isVisible());

        solo.drag(size.x-10, 10, size.y/2, size.y/2,1);
        Assert.assertTrue(solo.getCurrentActivity().getResources().getDrawable(R.drawable.facilities_e1eitc10).isVisible());

        //TEST MAP SLIDER BACKWARDS //////////////////////////////////////////////////////////////////////////////////////////////////////////////

        //swipe right 9 times

        solo.drag(10, size.x-10, size.y/2, size.y/2,1);
        Assert.assertTrue(solo.getCurrentActivity().getResources().getDrawable(R.drawable.facilities_e1eitc9).isVisible());

        solo.drag(10, size.x-10, size.y/2, size.y/2,1);
        Assert.assertTrue(solo.getCurrentActivity().getResources().getDrawable(R.drawable.facilities_e1eitc8).isVisible());

        solo.drag(10, size.x-10, size.y/2, size.y/2,1);
        Assert.assertTrue(solo.getCurrentActivity().getResources().getDrawable(R.drawable.facilities_e1eitc7).isVisible());

        solo.drag(10, size.x-10, size.y/2, size.y/2,1);
        Assert.assertTrue(solo.getCurrentActivity().getResources().getDrawable(R.drawable.facilities_e1eitc6).isVisible());

        solo.drag(10, size.x-10, size.y/2, size.y/2,1);
        Assert.assertTrue(solo.getCurrentActivity().getResources().getDrawable(R.drawable.facilities_e1eitc5).isVisible());

        solo.drag(10, size.x-10, size.y/2, size.y/2,1);
        Assert.assertTrue(solo.getCurrentActivity().getResources().getDrawable(R.drawable.facilities_e1eitc4).isVisible());

        solo.drag(10, size.x-10, size.y/2, size.y/2,1);
        Assert.assertTrue(solo.getCurrentActivity().getResources().getDrawable(R.drawable.facilities_e1eitc3).isVisible());

        solo.drag(10, size.x-10, size.y/2, size.y/2,1);
        Assert.assertTrue(solo.getCurrentActivity().getResources().getDrawable(R.drawable.facilities_e1eitc2).isVisible());

        solo.drag(10, size.x-10, size.y/2, size.y/2,1);
        Assert.assertTrue(solo.getCurrentActivity().getResources().getDrawable(R.drawable.facilities_e1eitc1).isVisible());

        solo.goBack();
        solo.waitForActivity("PickMapActivity");

        // check that you are on the pick map activity ///////////////////////////////////////////////////////////////////////////////////////////

        solo.assertCurrentActivity("Expected activity: PickMapActivity","PickMapActivity");

        //TEST MAP SLIDER FOR EITC E2 ////////////////////////////////////////////////////////////////////////////////////////////////////////////

        solo.clickOnImageButton(1);
        solo.waitForActivity("E2Activity");

        //check that you are on the slider activity //////////////////////////////////////////////////////////////////////////////////////////////

        solo.assertCurrentActivity("Expected activity: E2Activity","E2Activity");

        //check that the right image is shown (9 times) //////////////////////////////////////////////////////////////////////////////////////////

        Assert.assertTrue(solo.getCurrentActivity().getResources().getDrawable(R.drawable.facilities_e2eitc1).isVisible());

        //swipe left 8 times /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        solo.drag(size.x-10, 10, size.y/2, size.y/2,1);
        Assert.assertTrue(solo.getCurrentActivity().getResources().getDrawable(R.drawable.facilities_e2eitc2).isVisible());

        solo.drag(size.x-10, 10, size.y/2, size.y/2,1);
        Assert.assertTrue(solo.getCurrentActivity().getResources().getDrawable(R.drawable.facilities_e2eitc3).isVisible());

        solo.drag(size.x-10, 10, size.y/2, size.y/2,1);
        Assert.assertTrue(solo.getCurrentActivity().getResources().getDrawable(R.drawable.facilities_e2eitc4).isVisible());

        solo.drag(size.x-10, 10, size.y/2, size.y/2,1);
        Assert.assertTrue(solo.getCurrentActivity().getResources().getDrawable(R.drawable.facilities_e2eitc5).isVisible());

        solo.drag(size.x-10, 10, size.y/2, size.y/2,1);
        Assert.assertTrue(solo.getCurrentActivity().getResources().getDrawable(R.drawable.facilities_e2eitc6).isVisible());

        solo.drag(size.x-10, 10, size.y/2, size.y/2,1);
        Assert.assertTrue(solo.getCurrentActivity().getResources().getDrawable(R.drawable.facilities_e2eitc7).isVisible());

        solo.drag(size.x-10, 10, size.y/2, size.y/2,1);
        Assert.assertTrue(solo.getCurrentActivity().getResources().getDrawable(R.drawable.facilities_e2eitc8).isVisible());

        solo.drag(size.x-10, 10, size.y/2, size.y/2,1);
        Assert.assertTrue(solo.getCurrentActivity().getResources().getDrawable(R.drawable.facilities_e2eitc9).isVisible());

        //TEST MAP SLIDER BACKWARDS //////////////////////////////////////////////////////////////////////////////////////////////////////////////

        //swipe right 8 times

        solo.drag(10, size.x-10, size.y/2, size.y/2,1);
        Assert.assertTrue(solo.getCurrentActivity().getResources().getDrawable(R.drawable.facilities_e2eitc8).isVisible());

        solo.drag(10, size.x-10, size.y/2, size.y/2,1);
        Assert.assertTrue(solo.getCurrentActivity().getResources().getDrawable(R.drawable.facilities_e2eitc7).isVisible());

        solo.drag(10, size.x-10, size.y/2, size.y/2,1);
        Assert.assertTrue(solo.getCurrentActivity().getResources().getDrawable(R.drawable.facilities_e2eitc6).isVisible());

        solo.drag(10, size.x-10, size.y/2, size.y/2,1);
        Assert.assertTrue(solo.getCurrentActivity().getResources().getDrawable(R.drawable.facilities_e2eitc5).isVisible());

        solo.drag(10, size.x-10, size.y/2, size.y/2,1);
        Assert.assertTrue(solo.getCurrentActivity().getResources().getDrawable(R.drawable.facilities_e2eitc4).isVisible());

        solo.drag(10, size.x-10, size.y/2, size.y/2,1);
        Assert.assertTrue(solo.getCurrentActivity().getResources().getDrawable(R.drawable.facilities_e2eitc3).isVisible());

        solo.drag(10, size.x-10, size.y/2, size.y/2,1);
        Assert.assertTrue(solo.getCurrentActivity().getResources().getDrawable(R.drawable.facilities_e2eitc2).isVisible());

        solo.drag(10, size.x-10, size.y/2, size.y/2,1);
        Assert.assertTrue(solo.getCurrentActivity().getResources().getDrawable(R.drawable.facilities_e2eitc1).isVisible());

        solo.goBack();
        solo.waitForActivity("PickMapActivity");

        // check that you are on the pick map activity ///////////////////////////////////////////////////////////////////////////////////////////

        solo.assertCurrentActivity("Expected activity: PickMapActivity","PickMapActivity");

        //TEST MAP SLIDER FOR EITC E3 ////////////////////////////////////////////////////////////////////////////////////////////////////////////

        solo.clickOnImageButton(2);
        solo.waitForActivity("E3Activity");

        //check that you are on the slider activity //////////////////////////////////////////////////////////////////////////////////////////////

        solo.assertCurrentActivity("Expected activity: E3Activity","E3Activity");

        //check that the right image is shown (12 times) /////////////////////////////////////////////////////////////////////////////////////////

        Assert.assertTrue(solo.getCurrentActivity().getResources().getDrawable(R.drawable.facilities_e3eitc1).isVisible());

        //swipe left 11 times ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        solo.drag(size.x-10, 10, size.y/2, size.y/2,1);
        Assert.assertTrue(solo.getCurrentActivity().getResources().getDrawable(R.drawable.facilities_e3eitc2).isVisible());

        solo.drag(size.x-10, 10, size.y/2, size.y/2,1);
        Assert.assertTrue(solo.getCurrentActivity().getResources().getDrawable(R.drawable.facilities_e3eitc3).isVisible());

        solo.drag(size.x-10, 10, size.y/2, size.y/2,1);
        Assert.assertTrue(solo.getCurrentActivity().getResources().getDrawable(R.drawable.facilities_e3eitc4).isVisible());

        solo.drag(size.x-10, 10, size.y/2, size.y/2,1);
        Assert.assertTrue(solo.getCurrentActivity().getResources().getDrawable(R.drawable.facilities_e3eitc5).isVisible());

        solo.drag(size.x-10, 10, size.y/2, size.y/2,1);
        Assert.assertTrue(solo.getCurrentActivity().getResources().getDrawable(R.drawable.facilities_e3eitc6).isVisible());

        solo.drag(size.x-10, 10, size.y/2, size.y/2,1);
        Assert.assertTrue(solo.getCurrentActivity().getResources().getDrawable(R.drawable.facilities_e3eitc7).isVisible());

        solo.drag(size.x-10, 10, size.y/2, size.y/2,1);
        Assert.assertTrue(solo.getCurrentActivity().getResources().getDrawable(R.drawable.facilities_e3eitc8).isVisible());

        solo.drag(size.x-10, 10, size.y/2, size.y/2,1);
        Assert.assertTrue(solo.getCurrentActivity().getResources().getDrawable(R.drawable.facilities_e3eitc9).isVisible());

        solo.drag(size.x-10, 10, size.y/2, size.y/2,1);
        Assert.assertTrue(solo.getCurrentActivity().getResources().getDrawable(R.drawable.facilities_e3eitc10).isVisible());

        solo.drag(size.x-10, 10, size.y/2, size.y/2,1);
        Assert.assertTrue(solo.getCurrentActivity().getResources().getDrawable(R.drawable.facilities_e3eitc11).isVisible());

        solo.drag(size.x-10, 10, size.y/2, size.y/2,1);
        Assert.assertTrue(solo.getCurrentActivity().getResources().getDrawable(R.drawable.facilities_e3eitc12).isVisible());

        //TEST MAP SLIDER BACKWARDS //////////////////////////////////////////////////////////////////////////////////////////////////////////////

        //swipe right 11 times

        solo.drag(10, size.x-10, size.y/2, size.y/2,1);
        Assert.assertTrue(solo.getCurrentActivity().getResources().getDrawable(R.drawable.facilities_e3eitc11).isVisible());

        solo.drag(10, size.x-10, size.y/2, size.y/2,1);
        Assert.assertTrue(solo.getCurrentActivity().getResources().getDrawable(R.drawable.facilities_e3eitc10).isVisible());

        solo.drag(10, size.x-10, size.y/2, size.y/2,1);
        Assert.assertTrue(solo.getCurrentActivity().getResources().getDrawable(R.drawable.facilities_e3eitc9).isVisible());

        solo.drag(10, size.x-10, size.y/2, size.y/2,1);
        Assert.assertTrue(solo.getCurrentActivity().getResources().getDrawable(R.drawable.facilities_e3eitc8).isVisible());

        solo.drag(10, size.x-10, size.y/2, size.y/2,1);
        Assert.assertTrue(solo.getCurrentActivity().getResources().getDrawable(R.drawable.facilities_e3eitc7).isVisible());

        solo.drag(10, size.x-10, size.y/2, size.y/2,1);
        Assert.assertTrue(solo.getCurrentActivity().getResources().getDrawable(R.drawable.facilities_e3eitc6).isVisible());

        solo.drag(10, size.x-10, size.y/2, size.y/2,1);
        Assert.assertTrue(solo.getCurrentActivity().getResources().getDrawable(R.drawable.facilities_e3eitc5).isVisible());

        solo.drag(10, size.x-10, size.y/2, size.y/2,1);
        Assert.assertTrue(solo.getCurrentActivity().getResources().getDrawable(R.drawable.facilities_e3eitc4).isVisible());

        solo.drag(10, size.x-10, size.y/2, size.y/2,1);
        Assert.assertTrue(solo.getCurrentActivity().getResources().getDrawable(R.drawable.facilities_e3eitc3).isVisible());

        solo.drag(10, size.x-10, size.y/2, size.y/2,1);
        Assert.assertTrue(solo.getCurrentActivity().getResources().getDrawable(R.drawable.facilities_e3eitc2).isVisible());

        solo.drag(10, size.x-10, size.y/2, size.y/2,1);
        Assert.assertTrue(solo.getCurrentActivity().getResources().getDrawable(R.drawable.facilities_e3eitc1).isVisible());

        solo.goBack();
        solo.waitForActivity("PickMapActivity");

        // check that you are on the pick map activity ///////////////////////////////////////////////////////////////////////////////////////////
        solo.assertCurrentActivity("Expected activity: PickMapActivity","PickMapActivity");

        solo.goBack();
        solo.waitForActivity("MainActivity");

        //check that you are on the main activity ////////////////////////////////////////////////////////////////////////////////////////////////
        solo.assertCurrentActivity("Expected activity: MainActivity","MainActivity");
    }
}
