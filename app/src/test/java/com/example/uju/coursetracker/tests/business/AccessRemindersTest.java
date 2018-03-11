package com.example.uju.coursetracker.tests.business;

import com.example.uju.coursetracker.application.DatabaseService;
import com.example.uju.coursetracker.business.AccessReminders;
import com.example.uju.coursetracker.objects.Reminder;
import com.example.uju.coursetracker.presentation.MainActivity;
import com.example.uju.coursetracker.tests.persistence.StubDatabase;
import junit.framework.TestCase;


public class AccessRemindersTest extends TestCase
{
    private static String dbName = MainActivity.dbName;

    public AccessRemindersTest(String arg0)
    {
        super(arg0);
    }

    public void test1()
    {
        AccessReminders ar;
        Reminder reminder;

        DatabaseService.closeDataAccess();

        System.out.println("\nStarting test AccessReminders for: test1");

        DatabaseService.createDataAccess(new StubDatabase(dbName));

        ar = new AccessReminders();


        // Accessing the 1st reminder
        reminder = ar.getSequentialReminders();

        assertNotNull(ar);
        assertNotNull(reminder);
        assertEquals("SOCO 1200", reminder.getCourseID());
        assertEquals("Assignment", reminder.getReminderType());
        assertEquals("01/02/2018", reminder.getDueDate());



        // Accessing the 3rd reminder
        reminder = ar.getSequentialReminders();
        reminder = ar.getSequentialReminders();

        assertNotNull(ar);
        assertNotNull(reminder);
        assertEquals("SOCO 1200", reminder.getCourseID());
        assertEquals("Midterm", reminder.getReminderType());
        assertEquals("02/20/2018", reminder.getDueDate());



        // Accessing the last reminder
        reminder = ar.getSequentialReminders();
        reminder = ar.getSequentialReminders();
        reminder = ar.getSequentialReminders();
        reminder = ar.getSequentialReminders();
        reminder = ar.getSequentialReminders();
        reminder = ar.getSequentialReminders();
        reminder = ar.getSequentialReminders();
        reminder = ar.getSequentialReminders();
        reminder = ar.getSequentialReminders();
        reminder = ar.getSequentialReminders();

        assertNotNull(ar);
        assertNotNull(reminder);
        assertEquals("ENGL 1300", reminder.getCourseID());
        assertEquals("Midterm", reminder.getReminderType());
        assertEquals("02/19/2018", reminder.getDueDate());



        // Finished traversing reminders list in database after 13 items
        reminder = ar.getSequentialReminders();

        assertNull(reminder);
        assertNotNull(ar);


        // ... ADD MORE

        DatabaseService.closeDataAccess();
        System.out.println("Finished test AccessReminders");
    }
}
