package com.example.uju.coursetracker.tests.business;

import com.example.uju.coursetracker.application.DatabaseService;
import com.example.uju.coursetracker.business.AccessReminders;
import com.example.uju.coursetracker.objects.Reminder;
import com.example.uju.coursetracker.presentation.MainActivity;
import com.example.uju.coursetracker.tests.persistence.DataAccessStub;
import junit.framework.TestCase;
import java.util.ArrayList;

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

        DatabaseService.createDataAccess(new DataAccessStub(dbName));

        ar = new AccessReminders();


        // Accessing the 1st reminder
        reminder = ar.getSequentialReminders();

        assertNotNull(ar);
        assertNotNull(reminder);
        assertEquals("ECON 1020", reminder.getCourseID());
//        assertEquals("Assignment", reminder.getReminderType());
//        assertEquals("01/02/2018", reminder.getDueDate());



        // Accessing the 3rd reminder
        reminder = ar.getSequentialReminders();
        reminder = ar.getSequentialReminders();

        assertNotNull(ar);
        assertNotNull(reminder);
        assertEquals("ECON 1020", reminder.getCourseID());
//        assertEquals("Midterm", reminder.getReminderType());
//        assertEquals("02/20/2018", reminder.getDueDate());



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
        assertEquals("SOCO 1200", reminder.getCourseID());
//        assertEquals("Midterm", reminder.getReminderType());
//        assertEquals("02/19/2018", reminder.getDueDate());



        // Finished traversing reminders list in database after 13 items
        reminder = ar.getSequentialReminders();

        assertNull(reminder);
        assertNotNull(ar);



        ArrayList<Reminder> list = new ArrayList<Reminder>();
        reminder = new Reminder("BIOL 5000","Assignment","01/01/2018");
        Reminder reminder2 = new Reminder("MUSIC 2000","Project","02/02/2018");
        Reminder reminder3 = new Reminder("LAW 7000","Final","03/03/2018");


        assertNotNull(ar);
        assertNotNull(reminder);
        assertNotNull(reminder2);
        assertNotNull(reminder3);
        assertNotNull(list);

        ar.getRemindersSeq(list);
        assertEquals(13, list.size());

        ar.insertReminder(reminder);
        ar.insertReminder(reminder2);
        ar.insertReminder(reminder3);

        ar.getRemindersSeq(list);
        assertEquals(16, list.size());
        assertEquals("MUSIC 2000", (list.get(list.indexOf(reminder2))).getCourseID());
        assertEquals("Project", (list.get(list.indexOf(reminder2))).getReminderType());
        assertEquals("02/02/2018", (list.get(list.indexOf(reminder2))).getDueDate());


        //Find by courseID and then change due date
        ar.updateReminder(new Reminder("BIOL 5000","Assignment","07/07/2018"));

        ar.getRemindersSeq(list);
        assertEquals(16, list.size());
        assertEquals("BIOL 5000", (list.get(list.indexOf(reminder))).getCourseID());
        assertEquals("Assignment", (list.get(list.indexOf(reminder))).getReminderType());
        assertEquals("07/07/2018", (list.get(list.indexOf(reminder))).getDueDate());



        ar.deleteReminder(reminder);
        ar.deleteReminder(reminder2);

        ar.getRemindersSeq(list);
        assertEquals(14, list.size());
        assertEquals(-1, (list.indexOf(reminder)));
        assertEquals(-1, (list.indexOf(reminder2)));
        assertEquals("LAW 7000", (list.get(list.indexOf(reminder3))).getCourseID());



        ar.deleteReminder(reminder3);
        ar.insertReminder(reminder);
        ar.insertReminder(reminder2);
        ar.insertReminder(reminder3);
        ar.getRemindersSeq(list);
        assertEquals(16, list.size());



        ar.insertReminder(null);
        ar.updateReminder(null);
        ar.deleteReminder(null);
        ar.getRemindersSeq(list);
        assertEquals(16, list.size());



        Reminder reminder4 = new Reminder("STAT 5000","Project","06/06/2018");

        reminder4.setDueDate("11111111111111111");  // Invalid length of date
        assertEquals("Please enter a valid date in the format MM/DD/YYYY.", ar.validateDate(reminder4));

        reminder4.setDueDate("");  // Empty date
        assertEquals("Please enter a valid date in the format MM/DD/YYYY.", ar.validateDate(reminder4));

        reminder4.setDueDate("99/01/2018");  // Invalid Month
        assertEquals("Invalid Month entered. Please enter a valid date in the format MM/DD/YYYY.", ar.validateDate(reminder4));

        reminder4.setDueDate("01/99/2018");  // Invalid Date
        assertEquals("Invalid Date entered. Please enter a valid date in the format MM/DD/YYYY.", ar.validateDate(reminder4));

        reminder4.setDueDate("01/01/9999");  // Invalid Year
        assertEquals("Invalid Year entered. Please enter a valid date in the format MM/DD/YYYY.", ar.validateDate(reminder4));

        reminder4.setDueDate("05-05-2018");  // Slash not used in input
        assertEquals("Please enter a valid date in the format MM/DD/YYYY.", ar.validateDate(reminder4));


        DatabaseService.closeDataAccess();
        System.out.println("Finished test AccessReminders");
    }
}
