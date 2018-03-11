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

        System.out.println("Finished Test");

    }

}
