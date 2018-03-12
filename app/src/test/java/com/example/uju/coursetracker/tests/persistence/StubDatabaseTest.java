package com.example.uju.coursetracker.tests.persistence;

import com.example.uju.coursetracker.objects.Breakdown;
import com.example.uju.coursetracker.objects.Course;
import com.example.uju.coursetracker.objects.Reminder;
import com.example.uju.coursetracker.persistence.DataAccess;
import com.example.uju.coursetracker.persistence.DataAccessObject;
import com.example.uju.coursetracker.presentation.MainActivity;

import junit.framework.TestCase;
import java.util.ArrayList;


public class StubDatabaseTest extends TestCase
{
    private DataAccess dataAccess;

    public StubDatabaseTest(String arg0)
    {
        super(arg0);
    }

    public void setUp()
    {
        System.out.println("\nStarting Persistence test DataAccess (using stub)");

        // Use the following statements to run with the stub database:
        dataAccess = new StubDatabase();
        dataAccess.open("Stub");

        // or switch to the real HSQLDB database:
        // dataAccess = new DataAccessObject(MainActivity.dbName);
        // dataAccess.open(MainActivity.getDBPathName());
    }

    public void tearDown()
    {
        //**********  SHOULD WE CALL the .close method here since we call open above
        System.out.println("\nFinished Persistence test DataAccess (using stub)");
    }

    public void testCompletedCoursesDB()
    {

        System.out.println("\nStarting test StubDatabaseTest for: Completed Courses");

        ArrayList<Course> courses;
        Course course;
        String result;
        courses = new ArrayList<Course>();


        //Testing Seq
        result = dataAccess.getCompletedCoursesSeq(courses);
        assertNull(result);
        assertNotNull(courses);
        assertEquals(12, courses.size());
        course = courses.get(0);
        assertNotNull(course);
        assertEquals("COMP 1010", course.getCourseID());
        assertEquals("Introduction To Java 1", course.getCourseName());
        assertEquals("A", course.getGrade());



        //Testing Update for COMP 1010 with different course name and grade
        Course course2 = new Course("COMP 1010", "Maths", "B");
        result = dataAccess.updateCompletedCourse(course2);
        assertNull(result);
        course2 = dataAccess.getCompletedCourses().get(dataAccess.getCompletedCourses().indexOf(course2));
        assertNotNull(course2);
        assertEquals("COMP 1010", course2.getCourseID());
        assertEquals("Maths", course2.getCourseName());
        assertEquals("B", course2.getGrade());
        assertEquals(12, dataAccess.getCompletedCourses().size());



        //Testing Insert
        Course course3 = new Course("PHYS 3200", "Advanced Physics", "B+");
        result = dataAccess.insertCompletedCourse(course3);
        assertNull(result);
        course3 = dataAccess.getCompletedCourses().get(dataAccess.getCompletedCourses().indexOf(course3));
        assertNotNull(course3);
        assertEquals("PHYS 3200", course3.getCourseID());
        assertEquals("Advanced Physics", course3.getCourseName());
        assertEquals("B+", course3.getGrade());
        assertEquals(13, dataAccess.getCompletedCourses().size());



        //Testing Delete
        result = dataAccess.deleteCompletedCourse(course2);
        assertNull(result);
        result = dataAccess.deleteCompletedCourse(course3);
        assertNull(result);
        assertEquals(-1, dataAccess.getCompletedCourses().indexOf(course2));
        assertEquals(-1, dataAccess.getCompletedCourses().indexOf(course3));
        assertEquals(11, dataAccess.getCompletedCourses().size());


        // ... ADD MORE


        System.out.println("Finished test");

    }



    public void testCurrentCoursesDB()
    {

        System.out.println("\nStarting test StubDatabaseTest for: Current Courses");

        ArrayList<Course> courses;
        Course course;
        String result;
        courses = new ArrayList<Course>();


        //Testing Seq
        result = dataAccess.getCurrentCoursesSeq(courses);
        assertNull(result);
        assertNotNull(courses);
        assertEquals(3, courses.size());
        course = courses.get(0);
        assertNotNull(course);
        assertEquals("SOCO 1200", course.getCourseID());
        assertEquals("Introduction To Sociology", course.getCourseName());
        assertEquals("A", course.getGrade());



        //Testing Update for SOCO 1200 with different course name and grade
        Course course2 = new Course("SOCO 1200", "Physics", "C+");
        result = dataAccess.updateCurrentCourse(course2);
        assertNull(result);
        course2 = dataAccess.getCurrentCourses().get(dataAccess.getCurrentCourses().indexOf(course2));
        assertNotNull(course2);
        assertEquals("SOCO 1200", course2.getCourseID());
        assertEquals("Physics", course2.getCourseName());
        assertEquals("C+", course2.getGrade());
        assertEquals(3, dataAccess.getCurrentCourses().size());



        //Testing Insert
        Course course3 = new Course("PHYS 3200", "Advanced Physics", "B+");
        result = dataAccess.insertCurrentCourse(course3);
        assertNull(result);
        course3 = dataAccess.getCurrentCourses().get(dataAccess.getCurrentCourses().indexOf(course3));
        assertNotNull(course3);
        assertEquals("PHYS 3200", course3.getCourseID());
        assertEquals("Advanced Physics", course3.getCourseName());
        assertEquals("B+", course3.getGrade());
        assertEquals(4, dataAccess.getCurrentCourses().size());



        //Testing Delete
        result = dataAccess.deleteCurrentCourse(course2);
        assertNull(result);
        result = dataAccess.deleteCurrentCourse(course3);
        assertNull(result);
        assertEquals(-1, dataAccess.getCurrentCourses().indexOf(course2));
        assertEquals(-1, dataAccess.getCurrentCourses().indexOf(course3));
        assertEquals(2, dataAccess.getCurrentCourses().size());


        // ... ADD MORE

        System.out.println("Finished test");

    }




    public void testRemindersDB()
    {

        System.out.println("\nStarting test StubDatabaseTest for: Reminders");

        ArrayList<Reminder> reminders;
        Reminder reminder;
        String result;
        reminders = new ArrayList<Reminder>();


        //Testing Seq
        result = dataAccess.getRemindersSeq(reminders);
        assertNull(result);
        assertNotNull(reminders);
        assertEquals(13, reminders.size());
        reminder = reminders.get(0);
        assertNotNull(reminder);
        assertEquals("SOCO 1200", reminder.getCourseID());
        assertEquals("Assignment", reminder.getReminderType());
        assertEquals("01/02/2018", reminder.getDueDate());



        //Testing Update for SOCO 1200 with different reminder type and due date
        Reminder reminder2 = new Reminder("SOCO 1200", "Project", "01/01/2019");
        result = dataAccess.updateReminder(reminder2);
        assertNull(result);
        reminder2 = dataAccess.getReminders().get(dataAccess.getReminders().indexOf(reminder2));
        assertNotNull(reminder2);
        assertEquals("SOCO 1200", reminder2.getCourseID());
        assertEquals("Project", reminder2.getReminderType());
        assertEquals("01/01/2019", reminder2.getDueDate());
        assertEquals(13, dataAccess.getReminders().size());



        //Testing Insert
        Reminder reminder3 = new Reminder("CHEM 2000", "Quiz", "07/07/2018");
        result = dataAccess.insertReminder(reminder3);
        assertNull(result);
        reminder3 = dataAccess.getReminders().get(dataAccess.getReminders().indexOf(reminder3));
        assertNotNull(reminder3);
        assertEquals("CHEM 2000", reminder3.getCourseID());
        assertEquals("Quiz", reminder3.getReminderType());
        assertEquals("07/07/2018", reminder3.getDueDate());
        assertEquals(14, dataAccess.getReminders().size());



        //Testing Delete
        result = dataAccess.deleteReminder(reminder2);
        assertNull(result);
        result = dataAccess.deleteReminder(reminder3);
        assertNull(result);
        assertEquals(-1, dataAccess.getCompletedCourses().indexOf(reminder2));
        assertEquals(-1, dataAccess.getCompletedCourses().indexOf(reminder3));
        assertEquals(12, dataAccess.getCompletedCourses().size());


        // ... ADD MORE

        System.out.println("Finished test");

    }


}
