package com.example.uju.coursetracker.tests.objects;

import com.example.uju.coursetracker.objects.Reminder;

import junit.framework.TestCase;

public class RemindersTest extends TestCase
{
    public RemindersTest(String arg0) { super(arg0); }

    public  void testReminder()
    {
        Reminder rm;

        System.out.println("\n Startung Reminder Testing");

        rm = new Reminder("COMP3350","Quiz","19-07-18");
        assertNotNull(rm);
        assertTrue("COMP3350".equals(rm.getCourseID()));
        assertTrue("Quiz".equals(rm.getReminderType()));
        assertTrue("19-07-18".equals(rm.getDueDate()));

        

        System.out.println("Finished Reminder Testing");

    }
;}
