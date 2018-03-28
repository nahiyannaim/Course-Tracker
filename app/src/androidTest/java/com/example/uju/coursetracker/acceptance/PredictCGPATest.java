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

        // Should show 3.54 before making any changes to courses
        solo.clickOnButton("Predict my CGPA");
        Assert.assertTrue(solo.searchText("3.54"));

        solo.goBack();
        solo.waitForActivity("MyCurrentCoursesActivity");
        solo.assertCurrentActivity("Expected activity MyCurrentCoursesActivity", "MyCurrentCoursesActivity");


        ////////////////////////
        // Update
        ///////////////////////
        Assert.assertTrue(solo.searchText("SOCO 1200"));
        solo.clickOnText("SOCO 1200");
        Assert.assertTrue(solo.searchEditText("SOCO 1200"));
        Assert.assertTrue(solo.searchEditText("A"));

        solo.clearEditText(1);
        solo.enterText(1, "F");
        solo.clickOnButton("Update");
        solo.clickOnButton("Predict my CGPA");

        //Should have 3.27 GPA once SOCO 1200 grade changed from A to F
        Assert.assertTrue(solo.searchText("3.27"));

        solo.goBack();
        solo.waitForActivity("MyCurrentCoursesActivity");
        solo.assertCurrentActivity("Expected activity MyCurrentCoursesActivity", "MyCurrentCoursesActivity");

        Assert.assertTrue(solo.searchEditText("SOCO 1200"));
        Assert.assertTrue(solo.searchEditText("F"));


        ////////////////
        // Delete
        ////////////////
        Assert.assertTrue(solo.searchText("ENGL 1300"));
        solo.clickOnText("ENGL 1300");
        Assert.assertTrue(solo.searchEditText("ENGL 1300"));
        Assert.assertTrue(solo.searchEditText("A"));

        solo.clickOnButton("Delete");
        solo.clickOnButton("Predict my CGPA");

        //Should have 3.22 GPA once ENGL 1300 course is deleted
        Assert.assertTrue(solo.searchText("3.22"));

        solo.goBack();
        solo.waitForActivity("MyCurrentCoursesActivity");
        solo.assertCurrentActivity("Expected activity MyCurrentCoursesActivity", "MyCurrentCoursesActivity");



        //////////////////////
        // Create
        //////////////////////
        solo.clearEditText(0);
        solo.clearEditText(1);

        solo.enterText(0, "COMP 4300");
        solo.enterText(1, "F");

        solo.clickOnButton("Create");
        solo.clickOnButton("Predict my CGPA");

        //Should have 3.0 GPA once COMP 4300 course is created with a grade of F
        Assert.assertTrue(solo.searchText("3.0"));

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
        solo.enterText(1, "A");
        solo.clickOnButton("Create");

        Assert.assertTrue(solo.searchText("SOCO 1200"));
        solo.clickOnText("SOCO 1200");
        solo.clearEditText(1);
        solo.enterText(1, "A");
        solo.clickOnButton("Update");


        //Should show 3.54 after restoring to original contents
        solo.clickOnButton("Predict my CGPA");
        Assert.assertTrue(solo.searchText("3.54"));
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

        // Should show 3.54 before making any changes to courses
        solo.clickOnButton("Predict my CGPA");
        Assert.assertTrue(solo.searchText("3.54"));

        solo.goBack();
        solo.waitForActivity("MyCurrentCoursesActivity");
        solo.assertCurrentActivity("Expected activity MyCurrentCoursesActivity", "MyCurrentCoursesActivity");


        // Trying to create course with Invalid/Empty course ID
        solo.clickOnButton("Create");
        solo.waitForDialogToOpen();
        Assert.assertTrue(solo.searchText("Warning"));
        Assert.assertTrue(solo.searchText("Please enter a valid Course ID."));
        solo.goBack();
        solo.assertCurrentActivity("Expected activity MyCurrentCoursesActivity", "MyCurrentCoursesActivity");


        // Trying to update a course with Empty grade
        Assert.assertTrue(solo.searchText("SOCO 1200"));
        solo.clickOnText("SOCO 1200");
        solo.clearEditText(1);
        solo.clickOnButton("Update");
        solo.waitForDialogToOpen();
        Assert.assertTrue(solo.searchText("Warning"));
        Assert.assertTrue(solo.searchText("Please enter a valid grade."));
        solo.goBack();
        solo.assertCurrentActivity("Expected activity MyCurrentCoursesActivity", "MyCurrentCoursesActivity");


        // Trying to update a course with Invalid grade
        Assert.assertTrue(solo.searchText("ENGL 1300"));
        solo.clickOnText("ENGL 1300");
        solo.clearEditText(1);
        solo.enterText(1, "XXXXXXX");
        solo.clickOnButton("Update");
        solo.waitForDialogToOpen();
        Assert.assertTrue(solo.searchText("Warning"));
        Assert.assertTrue(solo.searchText("Please enter a valid grade."));
        solo.goBack();
        solo.assertCurrentActivity("Expected activity MyCurrentCoursesActivity", "MyCurrentCoursesActivity");


        //Updating a course that is not in the list
        solo.clearEditText(0);
        solo.clearEditText(1);
        solo.enterText(0, "ASIA 1700");
        solo.enterText(1, "A+");
        solo.clickOnButton("Update");
        solo.waitForDialogToOpen();
        Assert.assertTrue(solo.searchText("Warning"));
        Assert.assertTrue(solo.searchText("Cannot Update a course that is not in the list."));
        solo.goBack();
        solo.assertCurrentActivity("Expected activity MyCurrentCoursesActivity", "MyCurrentCoursesActivity");


        //Deleting a course that is not in the list
        solo.clearEditText(0);
        solo.clearEditText(1);
        solo.enterText(0, "PSYC 1500");
        solo.enterText(1, "B+");
        solo.clickOnButton("Delete");
        solo.waitForDialogToOpen();
        Assert.assertTrue(solo.searchText("Warning"));
        Assert.assertTrue(solo.searchText("Cannot Delete a course that is not in the list."));
        solo.goBack();
        solo.assertCurrentActivity("Expected activity MyCurrentCoursesActivity", "MyCurrentCoursesActivity");


        //Creating a duplicate course with same courseID
        Assert.assertTrue(solo.searchText("ECON 1020"));
        solo.clearEditText(0);
        solo.clearEditText(1);
        solo.enterText(0, "ECON 1020");
        solo.enterText(1, "A");
        solo.clickOnButton("Create");
        Assert.assertTrue(solo.searchText("Warning"));
        Assert.assertTrue(solo.searchText("This Course already exists in the list."));
        solo.goBack();
        solo.assertCurrentActivity("Expected activity MyCurrentCoursesActivity", "MyCurrentCoursesActivity");


        //Should show 3.54 since no real changes were made to any courses
        solo.clickOnButton("Predict my CGPA");
        Assert.assertTrue(solo.searchText("3.54"));

        solo.goBack();
        solo.waitForActivity("MyCurrentCoursesActivity");
        solo.assertCurrentActivity("Expected activity MyCurrentCoursesActivity", "MyCurrentCoursesActivity");


        //Exit
        solo.goBack();
        solo.clickOnImageButton(0);
        solo.waitForActivity("MainActivity");
    }
}
