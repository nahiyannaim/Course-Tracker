package com.example.uju.coursetracker.acceptance;


import android.test.ActivityInstrumentationTestCase2;
import com.example.uju.coursetracker.presentation.MainActivity;
//import com.robotium.solo.Solo;
import junit.framework.Assert;

public class CurrentCGPATest extends ActivityInstrumentationTestCase2<MainActivity>
{
    //private Solo solo;

    public CurrentCGPATest()
    {
        super(MainActivity.class);
    }

    public void setUp() throws Exception
    {
        //solo = new Solo(getInstrumentation(), getActivity());

        // Disable this for full acceptance test
        // System.out.println("Injecting stub database.");
        // Services.createDataAccess(new DataAccessStub(Main.dbName));
    }

    @Override
    public void tearDown() throws Exception
    {
        //solo.finishOpenedActivities();
    }

    // Please note again that this is not a complete set of acceptance tests

    public void testValidCompletedCourse()
    {
        //ADD MORE APPROPRIATE ASSERTS IN THIS METHOD

//        solo.waitForActivity("MainActivity");
//        solo.clickOnImageButton(0);
//        solo.clickOnMenuItem("My Current CGPA");
//        solo.assertCurrentActivity("Expected activity MyCompletedCoursesActivity", "MyCompletedCoursesActivity");
//
//
//        // Should show 3.42 before making any changes to courses
//        solo.clickOnButton("Calculate my current CGPA");
//        solo.goBack();
//        solo.waitForActivity("MyCompletedCoursesActivity");
//        solo.assertCurrentActivity("Expected activity MyCompletedCoursesActivity", "MyCompletedCoursesActivity");
//
//
//        ////////////////////////
//        // Update
//        ///////////////////////
//        Assert.assertTrue(solo.searchText("WOMN 3000"));
//        solo.clickOnText("WOMN 3000");
//        Assert.assertTrue(solo.searchEditText("WOMN 3000"));
//        Assert.assertTrue(solo.searchEditText("D"));
//
//
//        solo.clearEditText(1);
//        solo.enterText(1, "A");
//        solo.clickOnButton("Update");
//        solo.clickOnButton("Calculate my current CGPA");
//
//        solo.goBack();
//        solo.waitForActivity("MyCompletedCoursesActivity");
//        solo.assertCurrentActivity("Expected activity MyCompletedCoursesActivity", "MyCompletedCoursesActivity");
//
//        Assert.assertTrue(solo.searchEditText("WOMN 3000"));
//        Assert.assertTrue(solo.searchEditText("A"));
//
//
//        ////////////////
//        // Delete
//        ///////////////////////
//        Assert.assertTrue(solo.searchText("MATH 1700"));
//        solo.clickOnText("MATH 1700");
//        Assert.assertTrue(solo.searchEditText("MATH 1700"));
//        Assert.assertTrue(solo.searchEditText("A+"));
//
//        solo.clickOnButton("Delete");
//        solo.clickOnButton("Calculate my current CGPA");
//
//        solo.goBack();
//        solo.waitForActivity("MyCompletedCoursesActivity");
//        solo.assertCurrentActivity("Expected activity MyCompletedCoursesActivity", "MyCompletedCoursesActivity");
//
//        //Assert.assertFalse(solo.searchEditText("MATH 1700"));
//
//
//        ///////////////
//        // Create
//        ///////////////////////
//        solo.clearEditText(0);
//        solo.clearEditText(1);
//
//        solo.enterText(0, "STAT 6000");
//        solo.enterText(1, "F");
//
//        solo.clickOnButton("Create");
//        solo.clickOnButton("Calculate my current CGPA");
//
//        solo.goBack();
//        solo.waitForActivity("MyCompletedCoursesActivity");
//        solo.assertCurrentActivity("Expected activity MyCompletedCoursesActivity", "MyCompletedCoursesActivity");
//
//        Assert.assertTrue(solo.searchEditText("STAT 6000"));
//        Assert.assertTrue(solo.searchEditText("F"));
//
//
//
//        // Clean up: Restoring Database to original state
//        Assert.assertTrue(solo.searchText("WOMN 3000"));
//        solo.clickOnText("WOMN 3000");
//        solo.clearEditText(1);
//        solo.enterText(1, "D");
//        solo.clickOnButton("Update");
//
//        Assert.assertTrue(solo.searchText("STAT 6000"));
//        solo.clickOnText("STAT 6000");
//        solo.clickOnButton("Delete");
//
//        solo.clearEditText(0);
//        solo.clearEditText(1);
//        solo.enterText(0, "MATH 1700");
//        solo.enterText(1, "A+");
//        solo.clickOnButton("Create");
//
//
//        //Should show 3.42 after restoring to original contents
//        solo.clickOnButton("Calculate my current CGPA");
//        solo.goBack();
//        solo.waitForActivity("MyCompletedCoursesActivity");
//        solo.assertCurrentActivity("Expected activity MyCompletedCoursesActivity", "MyCompletedCoursesActivity");
//
//
//        //Exit
//        solo.goBack();
//        solo.clickOnImageButton(0);
//        solo.waitForActivity("MainActivity");

    }

//    public void testInvalidCompletedCourse()
//    {
//        solo.waitForActivity("HomeActivity");
//        solo.clickOnButton("Students");
//        solo.assertCurrentActivity("Expected activity StudentsActivity", "StudentsActivity");
//
//        solo.clickOnButton("Create");
//        Assert.assertTrue(solo.searchText("Warning"));
//        solo.goBack();
//
//        Assert.assertTrue(solo.searchText("100: Gary Chalmers"));
//        solo.clickOnText("100: Gary Chalmers");
//
//        solo.clearEditText(1);
//        solo.clickOnButton("Update");
//        solo.waitForDialogToOpen();
//        Assert.assertTrue(solo.searchText("Warning"));
//        solo.goBack();
//
//        solo.enterText(1, "Something Something");
//
//        solo.clearEditText(0);
//        solo.enterText(0, "987654321");
//        solo.clickOnButton("Delete");
//        solo.waitForDialogToOpen();
//        Assert.assertTrue(solo.searchText("Warning"));
//        solo.goBack();
//
//        solo.clickOnButton("Update");
//        solo.waitForDialogToOpen();
//        Assert.assertTrue(solo.searchText("Fatal error"));
//        solo.goBack();
//        solo.assertCurrentActivity("Expected activity HomeActivity", "HomeActivity");
//    }
}
