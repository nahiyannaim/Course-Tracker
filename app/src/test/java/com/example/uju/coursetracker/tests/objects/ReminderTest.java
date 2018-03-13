package com.example.uju.coursetracker.tests.objects;

import com.example.uju.coursetracker.objects.Reminder;
import junit.framework.TestCase;

public class ReminderTest extends TestCase
{
    public void testReminder()
    {
        System.out.println("Starting Object Test: testReminder");

        Reminder reminder = new Reminder("COMP 3350", "Final", "07/19/2018");
        assertNotNull(reminder);
        assertTrue("COMP 3350".equals(reminder.getCourseID()));
        assertTrue("Final".equals(reminder.getReminderType()));
        assertTrue("07/19/2018".equals(reminder.getDueDate()));


        Reminder reminder3 = reminder;
        assertSame(reminder, reminder3);


        //Equality
        Reminder reminder2 = new Reminder("COMP 3350", "Final", "07/19/2018");
        assertTrue(reminder.equals(reminder2));


        //Inequality
        reminder3 = new Reminder("STAT 2000", "Midterm", "01/01/2019");
        assertFalse(reminder2.equals(reminder3));


        //empty strings test
        Reminder reminder4 = new Reminder("","","");
        assertNotNull(reminder4);
        assertEquals("", reminder4.getCourseID());
        assertEquals("", reminder4.getReminderType());
        assertEquals("", reminder4.getDueDate());


        //null item test
        Reminder reminder6 = new Reminder(null, null, null);
        assertNotNull(reminder6);
        assertEquals(null, reminder6.getCourseID());
        assertEquals(null, reminder6.getReminderType());
        assertEquals(null, reminder6.getDueDate());


        //setters test
        Reminder reminder7 = new Reminder("ECON 1500", "Project", "02/02/2018");
        reminder7.setCourseID("CHEM 1000");
        reminder7.setReminderType("Final");
        reminder7.setDueDate("05/05/2019");
        assertNotNull(reminder7);
        assertEquals("CHEM 1000", reminder7.getCourseID());
        assertEquals("Final", reminder7.getReminderType());
        assertEquals("05/05/2019", reminder7.getDueDate());


        System.out.println("Finished Test");

    }
}
