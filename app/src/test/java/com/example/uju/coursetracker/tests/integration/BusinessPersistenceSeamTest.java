package com.example.uju.coursetracker.tests.integration;

import com.example.uju.coursetracker.application.DatabaseService;
import com.example.uju.coursetracker.business.AccessCourses;
import com.example.uju.coursetracker.business.AccessReminders;
import com.example.uju.coursetracker.objects.Course;
import com.example.uju.coursetracker.objects.Reminder;
import com.example.uju.coursetracker.presentation.MainActivity;
import com.example.uju.coursetracker.tests.persistence.DataAccessStub;
import junit.framework.TestCase;
import java.util.ArrayList;


public class BusinessPersistenceSeamTest extends TestCase
{
    public BusinessPersistenceSeamTest(String arg0)
    {
        super(arg0);
    }

    public void testAccessCourses()
    {
        DatabaseService.closeDataAccess();
        System.out.println("\nStarting Integration test of AccessCourses (Business) to Persistence Seam");

        //Uses Real HSQLDB
        //DatabaseService.createDataAccess(MainActivity.dbName);

        //Uses Stub DB
        DatabaseService.createDataAccess(new DataAccessStub(MainActivity.dbName));


        /////////////////////////////////////////////////////////////////////////////////
        // For Completed Courses
        /////////////////////////////////////////////////////////////////////////////////

        Course course;
        AccessCourses ac = new AccessCourses();
        String result = "";


        // Accessing the 1st course
        course = ac.getSequentialCompleted();

        assertNotNull(ac);
        assertNotNull(course);
        assertEquals("COMP 1010", course.getCourseID());
        assertEquals("Introduction To Java 1", course.getCourseName());
        assertEquals("A", course.getGrade());


        ArrayList<Course> list = new ArrayList<Course>();
        course = new Course("BIOL 5000", "Advanced Bio", "B");
        Course course2 = new Course("MUSIC 2000", "Advanced Music", "C");
        Course course3 = new Course("LAW 7000", "Advanced Law", "D");


        assertNotNull(ac);
        assertNotNull(course);
        assertNotNull(course2);
        assertNotNull(course3);
        assertNotNull(list);

        ac.getCompletedCoursesSeq(list);
        assertEquals(12, list.size());

        // insert a bunch of courses
        ac.insertCompletedCourse(course);
        ac.insertCompletedCourse(course2);
        ac.insertCompletedCourse(course3);

        ac.getCompletedCoursesSeq(list);
        assertEquals(15, list.size());
        assertEquals("MUSIC 2000", (list.get(list.indexOf(course2))).getCourseID());
        assertEquals("Advanced Music", (list.get(list.indexOf(course2))).getCourseName());
        assertEquals("C", (list.get(list.indexOf(course2))).getGrade());


        //Find by courseID and then update grade B to F
        ac.updateCompletedCourse(new Course("BIOL 5000", "Advanced Bio", "F"));

        ac.getCompletedCoursesSeq(list);
        assertEquals(15, list.size());
        assertEquals("BIOL 5000", (list.get(list.indexOf(course))).getCourseID());
        assertEquals("F", (list.get(list.indexOf(course))).getGrade());


        ac.deleteCompletedCourse(course);
        ac.deleteCompletedCourse(course2);

        ac.getCompletedCoursesSeq(list);
        assertEquals(13, list.size());
        // Not there anymore
        assertEquals(-1, (list.indexOf(course)));
        assertEquals(-1, (list.indexOf(course2)));
        // Still there
        assertEquals("LAW 7000", (list.get(list.indexOf(course3))).getCourseID());
        ac.deleteCompletedCourse(course3);
        ac.getCompletedCoursesSeq(list);
        assertEquals(12, list.size());


        /////////////////////////////////////////////////////////////////////////////////
        // For Current Courses
        /////////////////////////////////////////////////////////////////////////////////

        // Accessing the 1st course
        course = ac.getSequentialCurrent();

        assertNotNull(ac);
        assertNotNull(course);
        assertEquals("ECON 1020", course.getCourseID());
        assertEquals("Introduction To Economics", course.getCourseName());
        assertEquals("A", course.getGrade());


        list = new ArrayList<Course>();
        course = new Course("BIOL 5000", "Advanced Bio", "B");
        course2 = new Course("MUSIC 2000", "Advanced Music", "C");
        course3 = new Course("LAW 7000", "Advanced Law", "D");


        assertNotNull(ac);
        assertNotNull(course);
        assertNotNull(course2);
        assertNotNull(course3);
        assertNotNull(list);

        ac.getCurrentCoursesSeq(list);
        assertEquals(3, list.size());

        ac.insertCurrentCourse(course);
        ac.insertCurrentCourse(course2);
        ac.insertCurrentCourse(course3);

        ac.getCurrentCoursesSeq(list);
        assertEquals(6, list.size());
        assertEquals("MUSIC 2000", (list.get(list.indexOf(course2))).getCourseID());
        assertEquals("Advanced Music", (list.get(list.indexOf(course2))).getCourseName());
        assertEquals("C", (list.get(list.indexOf(course2))).getGrade());


        //Find by courseID and then update grade B to F
        ac.updateCurrentCourse(new Course("BIOL 5000", "Advanced Bio", "F"));

        ac.getCurrentCoursesSeq(list);
        assertEquals(6, list.size());
        assertEquals("BIOL 5000", (list.get(list.indexOf(course))).getCourseID());
        assertEquals("Advanced Bio", (list.get(list.indexOf(course))).getCourseName());
        assertEquals("F", (list.get(list.indexOf(course))).getGrade());


        ac.deleteCurrentCourse(course);
        ac.deleteCurrentCourse(course2);

        ac.getCurrentCoursesSeq(list);
        assertEquals(4, list.size());
        // Not there anymore
        assertEquals(-1, (list.indexOf(course)));
        assertEquals(-1, (list.indexOf(course2)));
        // Still there
        assertEquals("LAW 7000", (list.get(list.indexOf(course3))).getCourseID());


        ac.deleteCurrentCourse(course3);
        ac.getCurrentCoursesSeq(list);
        assertEquals(3, list.size());


        // Clean up not needed
        // Already in original state after the above operations

        DatabaseService.closeDataAccess();

        System.out.println("Finished Integration test of AccessCourses (Business) to Persistence Seam");
    }


    public void testAccessReminders()
    {
        DatabaseService.closeDataAccess();
        System.out.println("\nStarting Integration test of AccessReminders (Business) to Persistence Seam");

        // Uses Real HSQLDB
        //DatabaseService.createDataAccess(MainActivity.dbName);

        // Uses Stub DB
        DatabaseService.createDataAccess(new DataAccessStub(MainActivity.dbName));


        AccessReminders ar;
        Reminder reminder;
        String result = "";


        ar = new AccessReminders();

        // Accessing the 1st reminder
        reminder = ar.getSequentialReminders();

        assertNotNull(ar);
        assertNotNull(reminder);
        assertEquals("ECON 1020", reminder.getCourseID());
        assertEquals("Assignment", reminder.getReminderType());
        assertEquals("01/09/2018", reminder.getDueDate());



        ArrayList<Reminder> list = new ArrayList<Reminder>();
        reminder = new Reminder("BIOL 5000","Assignment","01/01/2018");
        Reminder reminder2 = new Reminder("MUSIC 2000","Project","02/02/2018");


        assertNotNull(ar);
        assertNotNull(reminder);
        assertNotNull(reminder2);
        assertNotNull(list);

        ar.getRemindersSeq(list);
        assertEquals(13, list.size());

        ar.insertReminder(reminder);
        ar.insertReminder(reminder2);


        ar.getRemindersSeq(list);
        assertEquals(15, list.size());
        assertEquals("BIOL 5000", (list.get(list.indexOf(reminder))).getCourseID());
        assertEquals("Assignment", (list.get(list.indexOf(reminder))).getReminderType());
        assertEquals("01/01/2018", (list.get(list.indexOf(reminder))).getDueDate());
        assertEquals("MUSIC 2000", (list.get(list.indexOf(reminder2))).getCourseID());
        assertEquals("Project", (list.get(list.indexOf(reminder2))).getReminderType());
        assertEquals("02/02/2018", (list.get(list.indexOf(reminder2))).getDueDate());


        //Find by courseID and then change due date
        ar.updateReminder(new Reminder("MUSIC 2000","Project","07/07/2018"));

        ar.getRemindersSeq(list);
        assertEquals(15, list.size());
        assertEquals("MUSIC 2000", (list.get(list.indexOf(reminder2))).getCourseID());
        assertEquals("Project", (list.get(list.indexOf(reminder2))).getReminderType());
        assertEquals("07/07/2018", (list.get(list.indexOf(reminder2))).getDueDate());


        // Need to revert back to original date so that delete correctly works for SQL since it checks cID == type == date
        ar.updateReminder(new Reminder("MUSIC 2000","Project","02/02/2018"));


        ar.deleteReminder(reminder);
        ar.deleteReminder(reminder2);
        ar.getRemindersSeq(list);
        assertEquals(-1, (list.indexOf(reminder2)));
        assertEquals(-1, (list.indexOf(reminder)));
        assertEquals(13, list.size());


        // Clean up not needed
        // Already in original state after the above operations

        DatabaseService.closeDataAccess();

        System.out.println("Finished Integration test of AccessReminders (Business) to Persistence Seam");

    }

}