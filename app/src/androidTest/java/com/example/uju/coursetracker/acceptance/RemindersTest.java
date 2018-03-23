package com.example.uju.coursetracker.acceptance;

import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.uju.coursetracker.presentation.DueDatesActivity;
import com.robotium.solo.Solo;
import junit.framework.Assert;
import com.example.uju.coursetracker.presentation.MainActivity;

public class RemindersTest extends ActivityInstrumentationTestCase2<MainActivity> {
    private Solo solo;

    public RemindersTest() {
        super(MainActivity.class);
    }

    public void setUp() throws Exception {
        solo = new Solo(getInstrumentation(), getActivity());

        // Disable this for full acceptance test
        // System.out.println("Injecting stub database.");
        // Services.createDataAccess(new DataAccessStub(Main.dbName));
    }

    @Override
    public void tearDown() throws Exception {
        solo.finishOpenedActivities();
    }

    //
//    // Please note again that this is not a complete set of acceptance tests
//
    public void testDeleteReminder() {
        solo.waitForActivity("MainActivity");
        solo.clickOnImageButton(0);
        solo.clickOnMenuItem("My Reminders");
        solo.assertCurrentActivity("Expected activity DueDatesActivity", "DueDatesActivity");

        //DELETE
        Assert.assertTrue(solo.searchText("ENGL 1300"));
        Assert.assertTrue(solo.searchText("Midterm"));
        Assert.assertTrue(solo.searchText("Due: 02/19/2018"));
        solo.clickOnText("ENGL 1300");
        solo.clickOnButton("Delete");
        Assert.assertFalse(solo.searchText("ENGL 1300"));

        solo.goBackToActivity("DueDatesActivity");
    }
    public void testAddReminder() {
        solo.waitForActivity("MainActivity");
        solo.clickOnImageButton(0);
        solo.clickOnMenuItem("My Reminders");
        solo.assertCurrentActivity("Expected activity DueDatesActivity", "DueDatesActivity");

        //ADD REMINDER
        solo.clickOnButton("Add Reminder");
        solo.assertCurrentActivity("Expected activity CreateNewReminder Activity", "CreateNewReminderActivity");

        Assert.assertTrue(solo.searchText("ECON 1020"));
        Assert.assertTrue(solo.searchText("A+"));
        solo.clickOnText("ECON 1020");


        boolean actual = solo.searchText("Reminder Type:");
        assertEquals("RemType text not found", true, actual);

        solo.pressSpinnerItem(0, 0);
        actual = solo.isSpinnerTextSelected(0, "Assignment");
        assertEquals("Spinner is not Assignment", true, actual);


        solo.clearEditText(0);
        solo.enterText(0, "07/23/2018");

        solo.clickOnButton("DONE");
        solo.assertCurrentActivity("Expected activity DueDatesActivity", "DueDatesActivity");

        Assert.assertTrue(solo.searchText("ECON 1020"));
        Assert.assertTrue(solo.searchText("Assignment"));
        Assert.assertTrue(solo.searchText("Due: 07/23/2018"));

        solo.goBackToActivity("DueDatesActivity");

        solo.goBack();
        solo.waitForActivity("MainActivity");
    }
    public void testInvalidDelete() {
        solo.waitForActivity("MainActivity");
        solo.clickOnImageButton(0);
        solo.clickOnMenuItem("My Reminders");
        solo.assertCurrentActivity("Expected activity DueDatesActivity", "DueDatesActivity");

        //INVALID DELETE

        solo.clickOnButton("Delete");
        solo .waitForDialogToOpen();
        Assert.assertTrue(solo.searchText("Warning"));

        solo.goBack();
        solo.waitForActivity("MainActivity");

    }
        //

        //

//        Assert.assertTrue(solo.searchText("400: Mary Bailey"));
//        solo.clickOnText("400: Mary Bailey");
//        Assert.assertTrue(solo.searchEditText("400"));
//        Assert.assertTrue(solo.searchEditText("Mary Bailey"));
//        Assert.assertTrue(solo.searchEditText("Off Campus"));

//        solo.clearEditText(1);
//        solo.enterText(1, "Mary Bucket");
//        solo.clearEditText(2);
//        solo.enterText(2, "Somewhere Else");
//        solo.clickOnButton("Update");
//
//        solo.goBack();
//
//        solo.waitForActivity("HomeActivity");
//        solo.clickOnButton("Students");
//        solo.assertCurrentActivity("Expected activity StudentsActivity", "StudentsActivity");
//
//        solo.waitForText("400: Mary Bucket");
//        Assert.assertTrue(solo.searchText("400: Mary Bucket"));
//        solo.clickOnText("400: Mary Bucket");
//        Assert.assertTrue(solo.searchEditText("400"));
//        Assert.assertTrue(solo.searchEditText("Mary Bucket"));
//        Assert.assertTrue(solo.searchEditText("Somewhere Else"));
//
//        // clean up
//        solo.clearEditText(1);
//        solo.enterText(1, "Mary Bailey");
//        solo.clearEditText(2);
//        solo.enterText(2, "Off Campus");
//        solo.clickOnButton("Update");
//    }
//
//    public void testInvalidStudent()
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
    //}
}
