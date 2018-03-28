package com.example.uju.coursetracker.acceptance;

import android.test.ActivityInstrumentationTestCase2;
import com.example.uju.coursetracker.presentation.MainActivity;
import com.robotium.solo.Solo;
import junit.framework.Assert;

public class CurrentCGPATest extends ActivityInstrumentationTestCase2<MainActivity>
{
    private Solo solo;

    public CurrentCGPATest()
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

    public void testValidCompletedCourse()
    {
        solo.waitForActivity("MainActivity");
        solo.clickOnImageButton(0);
        solo.clickOnMenuItem("My Current CGPA");
        solo.assertCurrentActivity("Expected activity MyCompletedCoursesActivity", "MyCompletedCoursesActivity");

        // Should show 3.42 before making any changes to courses
        solo.clickOnButton("Calculate my current CGPA");
        Assert.assertTrue(solo.searchText("3.42"));

        solo.goBack();
        solo.waitForActivity("MyCompletedCoursesActivity");
        solo.assertCurrentActivity("Expected activity MyCompletedCoursesActivity", "MyCompletedCoursesActivity");


        ////////////////////////
        // Update
        ///////////////////////
        Assert.assertTrue(solo.searchText("WOMN 3000"));
        solo.clickOnText("WOMN 3000");
        Assert.assertTrue(solo.searchEditText("WOMN 3000"));
        Assert.assertTrue(solo.searchEditText("D"));

        solo.clearEditText(1);
        solo.enterText(1, "A");
        solo.clickOnButton("Update");
        solo.clickOnButton("Calculate my current CGPA");

        //Should have 3.67 GPA once WOMN 3000 grade changed from D to A
        Assert.assertTrue(solo.searchText("3.67"));

        solo.goBack();
        solo.waitForActivity("MyCompletedCoursesActivity");
        solo.assertCurrentActivity("Expected activity MyCompletedCoursesActivity", "MyCompletedCoursesActivity");

        Assert.assertTrue(solo.searchEditText("WOMN 3000"));
        Assert.assertTrue(solo.searchEditText("A"));


        ////////////////
        // Delete
        ///////////////////////
        Assert.assertTrue(solo.searchText("MATH 1700"));
        solo.clickOnText("MATH 1700");
        Assert.assertTrue(solo.searchEditText("MATH 1700"));
        Assert.assertTrue(solo.searchEditText("A+"));

        solo.clickOnButton("Delete");
        solo.clickOnButton("Calculate my current CGPA");

        //Should have 3.59 GPA once MATH 1700 course is deleted
        Assert.assertTrue(solo.searchText("3.59"));

        solo.goBack();
        solo.waitForActivity("MyCompletedCoursesActivity");
        solo.assertCurrentActivity("Expected activity MyCompletedCoursesActivity", "MyCompletedCoursesActivity");

        Assert.assertTrue(solo.searchEditText("MATH 1700"));
        Assert.assertTrue(solo.searchEditText("A+"));

        //Assert.assertFalse(solo.searchEditText("MATH 1700"));


        ///////////////
        // Create
        ///////////////////////
        solo.clearEditText(0);
        solo.clearEditText(1);

        solo.enterText(0, "STAT 6000");
        solo.enterText(1, "F");

        solo.clickOnButton("Create");
        solo.clickOnButton("Calculate my current CGPA");

        //Should have 3.29 GPA once STAT 6000 course is created with a grade of F
        Assert.assertTrue(solo.searchText("3.29"));

        solo.goBack();
        solo.waitForActivity("MyCompletedCoursesActivity");
        solo.assertCurrentActivity("Expected activity MyCompletedCoursesActivity", "MyCompletedCoursesActivity");

        Assert.assertTrue(solo.searchEditText("STAT 6000"));
        Assert.assertTrue(solo.searchEditText("F"));



        // Clean up: Restoring Database to original state
        Assert.assertTrue(solo.searchText("WOMN 3000"));
        solo.clickOnText("WOMN 3000");
        solo.clearEditText(1);
        solo.enterText(1, "D");
        solo.clickOnButton("Update");

        Assert.assertTrue(solo.searchText("STAT 6000"));
        solo.clickOnText("STAT 6000");
        solo.clickOnButton("Delete");

        solo.clearEditText(0);
        solo.clearEditText(1);
        solo.enterText(0, "MATH 1700");
        solo.enterText(1, "A+");
        solo.clickOnButton("Create");


        //Should show 3.42 after restoring to original contents
        solo.clickOnButton("Calculate my current CGPA");
        solo.goBack();
        solo.waitForActivity("MyCompletedCoursesActivity");
        solo.assertCurrentActivity("Expected activity MyCompletedCoursesActivity", "MyCompletedCoursesActivity");


        //Exit
        solo.goBack();
        solo.clickOnImageButton(0);
        solo.waitForActivity("MainActivity");
    }

    public void testInvalidCompletedCourse()
    {

        solo.waitForActivity("MainActivity");
        solo.clickOnImageButton(0);
        solo.clickOnMenuItem("My Current CGPA");
        solo.assertCurrentActivity("Expected activity MyCompletedCoursesActivity", "MyCompletedCoursesActivity");


        // Should show 3.42 before making any changes to courses
        solo.clickOnButton("Calculate my current CGPA");
        Assert.assertTrue(solo.searchText("3.42"));

        solo.goBack();
        solo.waitForActivity("MyCompletedCoursesActivity");
        solo.assertCurrentActivity("Expected activity MyCompletedCoursesActivity", "MyCompletedCoursesActivity");


        // Trying to create course with Invalid/Empty course ID
        solo.clickOnButton("Create");
        Assert.assertTrue(solo.searchText("Warning"));
        Assert.assertTrue(solo.searchText("Please enter a valid Course ID."));
        solo.goBack();
        solo.assertCurrentActivity("Expected activity MyCompletedCoursesActivity", "MyCompletedCoursesActivity");


        // Trying to update a course with Empty grade
        Assert.assertTrue(solo.searchText("COMP 1010"));
        solo.clickOnText("COMP 1010");
        solo.clearEditText(1);
        solo.clickOnButton("Update");
        solo.waitForDialogToOpen();
        Assert.assertTrue(solo.searchText("Warning"));
        Assert.assertTrue(solo.searchText("Please enter a valid grade."));
        solo.goBack();
        solo.assertCurrentActivity("Expected activity MyCompletedCoursesActivity", "MyCompletedCoursesActivity");


        // Trying to update a course with Invalid grade
        Assert.assertTrue(solo.searchText("COMP 3010"));
        solo.clickOnText("COMP 3010");
        solo.clearEditText(1);
        solo.enterText(1, "AAAAAAA");
        solo.clickOnButton("Update");
        solo.waitForDialogToOpen();
        Assert.assertTrue(solo.searchText("Warning"));
        Assert.assertTrue(solo.searchText("Please enter a valid grade."));
        solo.goBack();
        solo.assertCurrentActivity("Expected activity MyCompletedCoursesActivity", "MyCompletedCoursesActivity");


        //Updating a course that is not in the list
        solo.clearEditText(0);
        solo.clearEditText(1);
        solo.enterText(0, "MATH 987654321");
        solo.enterText(1, "A");
        solo.clickOnButton("Update");
        solo.waitForDialogToOpen();
        Assert.assertTrue(solo.searchText("Warning"));
        Assert.assertTrue(solo.searchText("Cannot Update a course that is not in the list."));
        solo.goBack();
        solo.assertCurrentActivity("Expected activity MyCompletedCoursesActivity", "MyCompletedCoursesActivity");


        //Deleting a course that is not in the list
        solo.clearEditText(0);
        solo.clearEditText(1);
        solo.enterText(0, "STAT 5555555555");
        solo.enterText(1, "C+");
        solo.clickOnButton("Delete");
        solo.waitForDialogToOpen();
        Assert.assertTrue(solo.searchText("Warning"));
        Assert.assertTrue(solo.searchText("Cannot Delete a course that is not in the list."));
        solo.goBack();
        solo.assertCurrentActivity("Expected activity MyCompletedCoursesActivity", "MyCompletedCoursesActivity");


        //Creating a duplicate course with same courseID
        Assert.assertTrue(solo.searchText("COMP 1010"));
        solo.clearEditText(0);
        solo.clearEditText(1);
        solo.enterText(0, "COMP 1010");
        solo.enterText(1, "A");
        solo.clickOnButton("Create");
        Assert.assertTrue(solo.searchText("Warning"));
        Assert.assertTrue(solo.searchText("This Course already exists in the list."));
        solo.goBack();
        solo.assertCurrentActivity("Expected activity MyCompletedCoursesActivity", "MyCompletedCoursesActivity");


        //Should show 3.42 since no real changes were made to any courses
        solo.clickOnButton("Calculate my current CGPA");
        Assert.assertTrue(solo.searchText("3.42"));

        solo.goBack();
        solo.waitForActivity("MyCompletedCoursesActivity");
        solo.assertCurrentActivity("Expected activity MyCompletedCoursesActivity", "MyCompletedCoursesActivity");


        //Exit
        solo.goBack();
        solo.clickOnImageButton(0);
        solo.waitForActivity("MainActivity");
    }
}