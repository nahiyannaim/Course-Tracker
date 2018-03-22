package com.example.uju.coursetracker.acceptance;

import android.test.ActivityInstrumentationTestCase2;
import com.example.uju.coursetracker.presentation.MainActivity;
import com.robotium.solo.Solo;
import junit.framework.Assert;

public class PredictCGPATest  extends ActivityInstrumentationTestCase2<MainActivity>
{
    private Solo solo;

    public PredictCGPATest()
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

    public void testValidCurrentCourse()
    {
        solo.waitForActivity("MainActivity");
        solo.clickOnImageButton(0);
        solo.clickOnMenuItem("Predict Next CGPA");
        solo.assertCurrentActivity("Expected activity MyCurrentCoursesActivity", "MyCurrentCoursesActivity");

        // Should show 3.37 before making any changes to courses
        solo.clickOnButton("Predict my CGPA");
        Assert.assertTrue(solo.searchText("3.37"));

        solo.goBack();
        solo.waitForActivity("MyCurrentCoursesActivity");
        solo.assertCurrentActivity("Expected activity MyCurrentCoursesActivity", "MyCurrentCoursesActivity");


        ////////////////////////
        // Update
        ///////////////////////
        Assert.assertTrue(solo.searchText("SOCO 1200"));
        solo.clickOnText("SOCO 1200");
        Assert.assertTrue(solo.searchEditText("SOCO 1200"));
        Assert.assertTrue(solo.searchEditText("B"));

        solo.clearEditText(1);
        solo.enterText(1, "A+");
        solo.clickOnButton("Update");
        solo.clickOnButton("Predict my CGPA");

        //Should have 3.47 GPA once ECON 1020 grade changed from A+ to D
        Assert.assertTrue(solo.searchText("3.47"));

        solo.goBack();
        solo.waitForActivity("MyCurrentCoursesActivity");
        solo.assertCurrentActivity("Expected activity MyCurrentCoursesActivity", "MyCurrentCoursesActivity");

        Assert.assertTrue(solo.searchEditText("SOCO 1200"));
        Assert.assertTrue(solo.searchEditText("A+"));


        ////////////////
        // Delete
        ///////////////////////
        Assert.assertTrue(solo.searchText("ENGL 1300"));
        solo.clickOnText("ENGL 1300");
        Assert.assertTrue(solo.searchEditText("ENGL 1300"));
        Assert.assertTrue(solo.searchEditText("C"));

        solo.clickOnButton("Delete");
        solo.clickOnButton("Predict my CGPA");

        //Should have 3.57 GPA once ENGL 1300 course is deleted
        Assert.assertTrue(solo.searchText("3.57"));

        solo.goBack();
        solo.waitForActivity("MyCurrentCoursesActivity");
        solo.assertCurrentActivity("Expected activity MyCurrentCoursesActivity", "MyCurrentCoursesActivity");

        Assert.assertTrue(solo.searchEditText("ENGL 1300"));
        Assert.assertTrue(solo.searchEditText("C"));


        ///////////////
        // Create
        ///////////////////////
        solo.clearEditText(0);
        solo.clearEditText(1);

        solo.enterText(0, "COMP 4300");
        solo.enterText(1, "F");

        solo.clickOnButton("Create");
        solo.clickOnButton("Predict my CGPA");

        //Should have 3.34 GPA once COMP 4300 course is created with a grade of F
        Assert.assertTrue(solo.searchText("3.34"));

        solo.goBack();
        solo.waitForActivity("MyCurrentCoursesActivity");
        solo.assertCurrentActivity("Expected activity MyCurrentCoursesActivity", "MyCurrentCoursesActivity");

        Assert.assertTrue(solo.searchEditText("COMP 4300"));
        Assert.assertTrue(solo.searchEditText("F"));



        // Clean up: Restoring Database to original state
        Assert.assertTrue(solo.searchText("COMP 4300"));
        solo.clickOnText("COMP 4300");
        solo.clickOnButton("Delete");

        solo.clearEditText(0);
        solo.clearEditText(1);
        solo.enterText(0, "ENGL 1300");
        solo.enterText(1, "C");
        solo.clickOnButton("Create");

        Assert.assertTrue(solo.searchText("SOCO 1200"));
        solo.clickOnText("SOCO 1200");
        //solo.clearEditText(0);
        //solo.enterText(0, "SOCO 1200");
        solo.clearEditText(1);
        solo.enterText(1, "B");
        solo.clickOnButton("Update");


        //Should show 3.37 after restoring to original contents
        solo.clickOnButton("Predict my CGPA");
        Assert.assertTrue(solo.searchText("3.37"));
        solo.goBack();
        solo.waitForActivity("MyCurrentCoursesActivity");
        solo.assertCurrentActivity("Expected activity MyCurrentCoursesActivity", "MyCurrentCoursesActivity");


        //Exit
        solo.goBack();
        solo.clickOnImageButton(0);
        solo.waitForActivity("MainActivity");
        solo.assertCurrentActivity("Expected Activity MainActivity", "MainActivity");
    }

    public void testInvalidCurrentCourse()
    {
        solo.waitForActivity("MainActivity");
        solo.clickOnImageButton(0);
        solo.clickOnMenuItem("Predict Next CGPA");
        solo.assertCurrentActivity("Expected activity MyCurrentCoursesActivity", "MyCurrentCoursesActivity");

        solo.clickOnButton("Create");
        Assert.assertTrue(solo.searchText("Warning"));
        Assert.assertTrue(solo.searchText("Please enter a valid course ID"));
        solo.goBack();

        Assert.assertTrue(solo.searchText("100: Gary Chalmers"));
        solo.clickOnText("100: Gary Chalmers");

        solo.clearEditText(1);
        solo.clickOnButton("Update");
        solo.waitForDialogToOpen();
        Assert.assertTrue(solo.searchText("Warning"));
        solo.goBack();

        solo.enterText(1, "Something Something");

        solo.clearEditText(0);
        solo.enterText(0, "987654321");
        solo.clickOnButton("Delete");
        solo.waitForDialogToOpen();
        Assert.assertTrue(solo.searchText("Warning"));
        solo.goBack();

        solo.clickOnButton("Update");
        solo.waitForDialogToOpen();
        Assert.assertTrue(solo.searchText("Fatal error"));
        solo.goBack();
        solo.assertCurrentActivity("Expected activity HomeActivity", "HomeActivity");
    }
}
