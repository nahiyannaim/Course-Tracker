package com.example.uju.coursetracker.acceptance;

import android.test.ActivityInstrumentationTestCase2;

import com.robotium.solo.Solo;
import junit.framework.Assert;
import com.example.uju.coursetracker.presentation.MainActivity;

public class RemindersTest extends ActivityInstrumentationTestCase2<MainActivity>
{
    private Solo solo;

    public RemindersTest()
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

    public void testDeleteReminder()
    {
        solo.waitForActivity("MainActivity");
        solo.clickOnImageButton(0);
        solo.clickOnMenuItem("My Reminders");
        solo.assertCurrentActivity("Expected activity DueDatesActivity", "DueDatesActivity");

        //DELETE
        Assert.assertTrue(solo.searchText("ENGL 1300"));
        Assert.assertTrue(solo.searchText("Midterm"));
        Assert.assertTrue(solo.searchText("Due: 02/19/2018"));
        solo.clickOnText("Due: 02/19/2018");
        solo.clickOnButton("Delete");
        //Assert.assertFalse(solo.searchText("ENGL 1300"));

        //RESTORE DB
        solo.clickOnButton("Add Reminder");
        solo.waitForActivity("CreateNewReminderActivity");
        solo.assertCurrentActivity("Expected activity CreateNewReminder Activity", "CreateNewReminderActivity");

        Assert.assertTrue(solo.searchText("ENGL 1300"));
        Assert.assertTrue(solo.searchText("A"));
        solo.clickOnText("ENGL 1300");


        boolean actual = solo.searchText("Reminder Type:");
        assertEquals("RemType text not found", true, actual);

        solo.pressSpinnerItem(0, 2);
        actual = solo.isSpinnerTextSelected(0, "Midterm");
        assertEquals("Spinner is not Midterm", true, actual);


        solo.clearEditText(0);
        solo.enterText(0, "02/19/2018");

        solo.clickOnButton("DONE");
        solo.waitForActivity("DueDatesActivity");
        solo.assertCurrentActivity("Expected activity DueDatesActivity", "DueDatesActivity");


        solo.goBack();
        solo.waitForActivity("MainActivity");
    }

    public void testAddReminder()
    {
        solo.waitForActivity("MainActivity");
        solo.clickOnImageButton(0);
        solo.clickOnMenuItem("My Reminders");
        solo.assertCurrentActivity("Expected activity DueDatesActivity", "DueDatesActivity");

        //ADD REMINDER
        solo.clickOnButton("Add Reminder");
        solo.waitForActivity("CreateNewReminderActivity");
        solo.assertCurrentActivity("Expected activity CreateNewReminder Activity", "CreateNewReminderActivity");

        Assert.assertTrue(solo.searchText("ECON 1020"));
        Assert.assertTrue(solo.searchText("A"));
        solo.clickOnText("ECON 1020");


        boolean actual = solo.searchText("Reminder Type:");
        assertEquals("RemType text not found", true, actual);

        solo.pressSpinnerItem(0, 0);
        actual = solo.isSpinnerTextSelected(0, "Assignment");
        assertEquals("Spinner is not Assignment", true, actual);


        solo.clearEditText(0);
        solo.enterText(0, "07/23/2018");

        solo.clickOnButton("DONE");
        solo.waitForActivity("DueDatesActivity");
        solo.assertCurrentActivity("Expected activity DueDatesActivity", "DueDatesActivity");

        Assert.assertTrue(solo.searchText("ECON 1020"));
        Assert.assertTrue(solo.searchText("Assignment"));
        Assert.assertTrue(solo.searchText("Due: 07/23/2018"));

        //RESTORE DB

        solo.clickOnText("ECON 1020",2);
        solo.clickOnButton("Delete");
        Assert.assertTrue(solo.searchText("ECON 1020",0));

        solo.goBack();
        solo.waitForActivity("MainActivity");
    }

    public void testInvalidDelete()
    {
        solo.waitForActivity("MainActivity");
        solo.clickOnImageButton(0);
        solo.clickOnMenuItem("My Reminders");
        solo.waitForActivity("DueDatesActivity");
        solo.assertCurrentActivity("Expected activity DueDatesActivity", "DueDatesActivity");

        //INVALID DELETE

        solo.clickOnButton("Delete");
        solo .waitForDialogToOpen();
        Assert.assertTrue(solo.searchText("Warning"));
        Assert.assertTrue(solo.searchText("Please select a Reminder to delete."));

        solo.goBack();
        solo.waitForActivity("MainActivity");


    }

    public void testInvalidAddReminder()
    {
        solo.waitForActivity("MainActivity");
        solo.clickOnImageButton(0);
        solo.clickOnMenuItem("My Reminders");
        solo.waitForActivity("DueDatesActivity");
        solo.assertCurrentActivity("Expected activity DueDatesActivity", "DueDatesActivity");


        //TEST W/OUT CLICKING ON COURSE
        solo.clickOnButton("Add Reminder");
        solo.waitForActivity("CreateNewReminderActivity");
        solo.assertCurrentActivity("Expected activity CreateNewReminder Activity", "CreateNewReminderActivity");

        solo.clickOnButton("DONE");
        solo.waitForDialogToOpen();
        Assert.assertTrue(solo.searchText("Warning"));
        Assert.assertTrue(solo.searchText("Please select a Course from the list."));
        solo.goBack();


        //TEST IF YOU PUT DATE W/OUT CLICKING ON COURSE
        solo.clearEditText(0);
        solo.enterText(0, "07/23/2018");

        solo.clickOnButton("DONE");
        solo.waitForDialogToOpen();
        Assert.assertTrue(solo.searchText("Warning"));
        Assert.assertTrue(solo.searchText("Please select a Course from the list."));
        solo.goBack();


        //TEST IF YOU ADD REM TYPE W/OUT CLICKING ON COURSE
        boolean actual = solo.searchText("Reminder Type:");
        assertEquals("RemType text not found", true, actual);

        solo.pressSpinnerItem(0, 1);
        actual = solo.isSpinnerTextSelected(0, "Quiz");
        assertEquals("Spinner is not Quiz", true, actual);

        solo.clickOnButton("DONE");
        solo.waitForDialogToOpen();
        Assert.assertTrue(solo.searchText("Warning"));
        Assert.assertTrue(solo.searchText("Please select a Course from the list."));
        solo.goBack();


        //TEST ALL PATHS W/OUT CLICKING ON COURSE
        actual = solo.searchText("Reminder Type:");
        assertEquals("RemType text not found", true, actual);

        solo.pressSpinnerItem(0, 1);
        actual = solo.isSpinnerTextSelected(0, "Midterm");
        assertEquals("Spinner is not Midterm", true, actual);

        solo.clearEditText(0);
        solo.enterText(0, "07/30/2019");

        solo.clickOnButton("DONE");
        solo.waitForDialogToOpen();
        Assert.assertTrue(solo.searchText("Warning"));
        Assert.assertTrue(solo.searchText("Please select a Course from the list."));

        solo.goBackToActivity("DueDatesActivity");
        solo.goBack();
        solo.waitForActivity("MainActivity");

    }

    public void testInvalidDate()
    {
        solo.waitForActivity("MainActivity");
        solo.clickOnImageButton(0);
        solo.clickOnMenuItem("My Reminders");
        solo.waitForActivity("DueDatesActivity");
        solo.assertCurrentActivity("Expected activity DueDatesActivity", "DueDatesActivity");

        //ADD REMINDER
        solo.clickOnButton("Add Reminder");
        solo.assertCurrentActivity("Expected activity CreateNewReminder Activity", "CreateNewReminderActivity");

        Assert.assertTrue(solo.searchText("ECON 1020"));
        Assert.assertTrue(solo.searchText("A"));
        solo.clickOnText("ECON 1020");


        boolean actual = solo.searchText("Reminder Type:");
        assertEquals("RemType text not found", true, actual);

        solo.pressSpinnerItem(0, 0);
        actual = solo.isSpinnerTextSelected(0, "Assignment");
        assertEquals("Spinner is not Assignment", true, actual);


        //TEST FOR DIGIT < 10
        solo.clearEditText(0);
        solo.enterText(0,"07/10/18");

        solo.clickOnButton("DONE");
        solo.waitForDialogToOpen();
        Assert.assertTrue(solo.searchText("Warning"));
        Assert.assertTrue(solo.searchText("Please enter a valid date in the format MM/DD/YYYY."));
        solo.goBack();


        //TEST FOR DIGIT > 10
        solo.clearEditText(0);
        solo.enterText(0,"07/10/201818");

        solo.clickOnButton("DONE");
        solo.waitForDialogToOpen();
        Assert.assertTrue(solo.searchText("Warning"));
        Assert.assertTrue(solo.searchText("Please enter a valid date in the format MM/DD/YYYY."));
        solo.goBack();


        //TEST FOR INVALID MONTH
        solo.clearEditText(0);
        solo.enterText(0,"13/10/2018");

        solo.clickOnButton("DONE");
        solo.waitForDialogToOpen();
        Assert.assertTrue(solo.searchText("Warning"));
        Assert.assertTrue(solo.searchText("Invalid Month entered. Please enter a valid date in the format MM/DD/YYYY."));
        solo.goBack();


        //TEST FOR INVALID DAY
        solo.clearEditText(0);
        solo.enterText(0,"07/32/2018");

        solo.clickOnButton("DONE");
        solo.waitForDialogToOpen();
        Assert.assertTrue(solo.searchText("Warning"));
        Assert.assertTrue(solo.searchText("Invalid Date entered. Please enter a valid date in the format MM/DD/YYYY."));
        solo.goBack();


        //TEST FOR DATE WITHOUT "/" BU "-"
        solo.clearEditText(0);
        solo.enterText(0,"07-10-2018");

        solo.clickOnButton("DONE");
        solo.waitForDialogToOpen();
        Assert.assertTrue(solo.searchText("Warning"));
        Assert.assertTrue(solo.searchText("Please enter a valid date in the format MM/DD/YYYY."));
        solo.goBack();


        //TEST FOR DATE BEFORE THE PRESENT DATE
        solo.clearEditText(0);
        solo.enterText(0,"03/20/2017");


        solo.clickOnButton("DONE");
        solo.waitForDialogToOpen();
        Assert.assertTrue(solo.searchText("Warning"));
        Assert.assertTrue(solo.searchText("Invalid Year entered. Please enter a valid date in the format MM/DD/YYYY."));
        solo.goBack();

        solo.goBackToActivity("DueDatesActivity");

        solo.goBack();
        solo.waitForActivity("MainActivity");

    }

}