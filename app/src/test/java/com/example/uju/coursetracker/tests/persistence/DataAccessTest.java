package com.example.uju.coursetracker.tests.persistence;

import com.example.uju.coursetracker.objects.Course;
import com.example.uju.coursetracker.objects.Reminder;
import com.example.uju.coursetracker.persistence.DataAccess;
import com.example.uju.coursetracker.persistence.DataAccessObject;
import com.example.uju.coursetracker.presentation.MainActivity;

import junit.framework.TestCase;
import java.util.ArrayList;


public class DataAccessTest extends TestCase
{
    private DataAccess dataAccess;

    public DataAccessTest(String arg0)
    {
        super(arg0);
    }

    public void setUp()
    {
        System.out.println("\nStarting Persistence test DataAccess (using stub)");

        // Use the following statements to run with the stub database:
//        dataAccess = new DataAccessStub();
//        dataAccess.open("Stub");

        // or switch to the real HSQLDB database:
         dataAccess = new DataAccessObject(MainActivity.dbName);
         dataAccess.open(MainActivity.getDBPathName());
    }

    public void tearDown()
    {
        dataAccess.close();
        System.out.println("\nFinished Persistence test DataAccess (using stub)");
    }

    // This is a NEW method in iteration 3
    public static void dataAccessTest(DataAccess dataAccess)
    {
        DataAccessTest dataAccessTest = new DataAccessTest("");
        dataAccessTest.dataAccess = dataAccess;

        dataAccessTest.testCompletedCoursesDB();
        dataAccessTest.testCurrentCoursesDB();
        dataAccessTest.testRemindersDB();
    }

    public void testCompletedCoursesDB()
    {
        System.out.println("\nStarting test DataAccessTest for: Completed Courses");

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
        //assertEquals("Maths", course2.getCourseName());
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

//******** works upto here

        // testing null
        Course course4 = null;
        result = dataAccess.insertCompletedCourse(course4);
        assertNull(result);
//        assertEquals(-1, dataAccess.getCompletedCourses().indexOf(course4));
//        assertEquals(11, dataAccess.getCompletedCourses().size());
//        result = dataAccess.deleteCompletedCourse(course4);
//        assertNull(result);
//        assertEquals(-1, dataAccess.getCompletedCourses().indexOf(course4));
//        assertEquals(11, dataAccess.getCompletedCourses().size());
//        result = dataAccess.updateCompletedCourse(course4);
//        assertNull(result);
//        assertEquals(-1, dataAccess.getCompletedCourses().indexOf(course4));
//        assertEquals(11, dataAccess.getCompletedCourses().size());
//
//
//
//
//        ArrayList <Course> list = new ArrayList<Course>();
//        Course course5 = new Course("HNSC 2000", "Human Nutrition", "C");
//
//        // testing Empty list
//        dataAccess.getCompletedCourses().clear();
//
//        result = dataAccess.getCompletedCoursesSeq(list);
//        assertNull(result);
//        assertNotNull(list);
//        assertEquals(0, list.size());
//
//        result = dataAccess.insertCompletedCourse(course5);
//        assertNull(result);
//        course5 = dataAccess.getCompletedCourses().get(dataAccess.getCompletedCourses().indexOf(course5));
//        assertNotNull(course5);
//        assertEquals("HNSC 2000", course5.getCourseID());
//        assertEquals("Human Nutrition", course5.getCourseName());
//        assertEquals("C", course5.getGrade());
//        assertEquals(1, dataAccess.getCompletedCourses().size());
//
//        Course course6 = new Course("HNSC 2000", "Human Nutrition", "A+");
//        result = dataAccess.updateCompletedCourse(course6);
//        assertNull(result);
//        course6 = dataAccess.getCompletedCourses().get(dataAccess.getCompletedCourses().indexOf(course6));
//        assertNotNull(course6);
//        assertEquals("HNSC 2000", course6.getCourseID());
//        assertEquals("Human Nutrition", course6.getCourseName());
//        assertEquals("A+", course6.getGrade());
//        assertEquals(1, dataAccess.getCompletedCourses().size());
//
//        result = dataAccess.deleteCompletedCourse(course5);
//        assertNull(result);
//        result = dataAccess.deleteCompletedCourse(course5);
//        assertNull(result);
//        assertEquals(-1, dataAccess.getCompletedCourses().indexOf(course5));
//        assertEquals(0, dataAccess.getCompletedCourses().size());





        /////// **************************************************************
        //   CLEAN UP PUT BACK ORIGINAL CONTENTS to DB here otherwise it'd fail



        System.out.println("Finished test");

    }

    public void testCurrentCoursesDB()
    {

//        System.out.println("\nStarting test DataAccessTest for: Current Courses");
//
//        ArrayList<Course> courses;
//        Course course;
//        String result;
//        courses = new ArrayList<Course>();
//
//
//        //Testing Seq
//        result = dataAccess.getCurrentCoursesSeq(courses);
//        assertNull(result);
//        assertNotNull(courses);
//        assertEquals(3, courses.size());
//        course = courses.get(0);
//        assertNotNull(course);
//        assertEquals("SOCO 1200", course.getCourseID());
//        assertEquals("Introduction To Sociology", course.getCourseName());
//        assertEquals("A", course.getGrade());
//
//
//
//        //Testing Update for SOCO 1200 with different course name and grade
//        Course course2 = new Course("SOCO 1200", "Physics", "C+");
//        result = dataAccess.updateCurrentCourse(course2);
//        assertNull(result);
//        course2 = dataAccess.getCurrentCourses().get(dataAccess.getCurrentCourses().indexOf(course2));
//        assertNotNull(course2);
//        assertEquals("SOCO 1200", course2.getCourseID());
//        assertEquals("Physics", course2.getCourseName());
//        assertEquals("C+", course2.getGrade());
//        assertEquals(3, dataAccess.getCurrentCourses().size());
//
//
//
//        //Testing Insert
//        Course course3 = new Course("PHYS 3200", "Advanced Physics", "B+");
//        result = dataAccess.insertCurrentCourse(course3);
//        assertNull(result);
//        course3 = dataAccess.getCurrentCourses().get(dataAccess.getCurrentCourses().indexOf(course3));
//        assertNotNull(course3);
//        assertEquals("PHYS 3200", course3.getCourseID());
//        assertEquals("Advanced Physics", course3.getCourseName());
//        assertEquals("B+", course3.getGrade());
//        assertEquals(4, dataAccess.getCurrentCourses().size());
//
//
//
//        //Testing Delete
//        result = dataAccess.deleteCurrentCourse(course2);
//        assertNull(result);
//        result = dataAccess.deleteCurrentCourse(course3);
//        assertNull(result);
//        assertEquals(-1, dataAccess.getCurrentCourses().indexOf(course2));
//        assertEquals(-1, dataAccess.getCurrentCourses().indexOf(course3));
//        assertEquals(2, dataAccess.getCurrentCourses().size());
//
//
//
//        // testing null
//        Course course4 = null;
//        result = dataAccess.insertCurrentCourse(course4);
//        assertNull(result);
//        assertEquals(-1, dataAccess.getCurrentCourses().indexOf(course4));
//        assertEquals(2, dataAccess.getCurrentCourses().size());
//        result = dataAccess.deleteCurrentCourse(course4);
//        assertNull(result);
//        assertEquals(-1, dataAccess.getCurrentCourses().indexOf(course4));
//        assertEquals(2, dataAccess.getCurrentCourses().size());
//        result = dataAccess.updateCurrentCourse(course4);
//        assertNull(result);
//        assertEquals(-1, dataAccess.getCurrentCourses().indexOf(course4));
//        assertEquals(2, dataAccess.getCurrentCourses().size());
//
//
//
//
//        ArrayList <Course> list = new ArrayList<Course>();
//        Course course5 = new Course("HNSC 2000", "Human Nutrition", "C");
//
//        // testing Empty list
//        dataAccess.getCurrentCourses().clear();
//
//        result = dataAccess.getCurrentCoursesSeq(list);
//        assertNull(result);
//        assertNotNull(list);
//        assertEquals(0, list.size());
//
//        result = dataAccess.insertCurrentCourse(course5);
//        assertNull(result);
//        course5 = dataAccess.getCurrentCourses().get(dataAccess.getCurrentCourses().indexOf(course5));
//        assertNotNull(course5);
//        assertEquals("HNSC 2000", course5.getCourseID());
//        assertEquals("Human Nutrition", course5.getCourseName());
//        assertEquals("C", course5.getGrade());
//        assertEquals(1, dataAccess.getCurrentCourses().size());
//
//        Course course6 = new Course("HNSC 2000", "Human Nutrition", "A+");
//        result = dataAccess.updateCurrentCourse(course6);
//        assertNull(result);
//        course6 = dataAccess.getCurrentCourses().get(dataAccess.getCurrentCourses().indexOf(course6));
//        assertNotNull(course6);
//        assertEquals("HNSC 2000", course6.getCourseID());
//        assertEquals("Human Nutrition", course6.getCourseName());
//        assertEquals("A+", course6.getGrade());
//        assertEquals(1, dataAccess.getCurrentCourses().size());
//
//        result = dataAccess.deleteCurrentCourse(course5);
//        assertNull(result);
//        result = dataAccess.deleteCurrentCourse(course5);
//        assertNull(result);
//        assertEquals(-1, dataAccess.getCurrentCourses().indexOf(course5));
//        assertEquals(0, dataAccess.getCurrentCourses().size());
//
//
//        System.out.println("Finished test");
//
//    }
//
//    public void testRemindersDB()
//    {
//
//        System.out.println("\nStarting test DataAccessTest for: Reminders");
//
//        ArrayList<Reminder> reminders;
//        Reminder reminder;
//        String result;
//        reminders = new ArrayList<Reminder>();
//
//
//        //Testing Seq
//        result = dataAccess.getRemindersSeq(reminders);
//        assertNull(result);
//        assertNotNull(reminders);
//        assertEquals(13, reminders.size());
//        reminder = reminders.get(0);
//        assertNotNull(reminder);
//        assertEquals("SOCO 1200", reminder.getCourseID());
//        assertEquals("Assignment", reminder.getReminderType());
//        assertEquals("01/02/2018", reminder.getDueDate());
//
//
//
//        //Testing Update for SOCO 1200 with different reminder type and due date
//        Reminder reminder2 = new Reminder("SOCO 1200", "Project", "01/01/2019");
//        result = dataAccess.updateReminder(reminder2);
//        assertNull(result);
//        reminder2 = dataAccess.getReminders().get(dataAccess.getReminders().indexOf(reminder2));
//        assertNotNull(reminder2);
//        assertEquals("SOCO 1200", reminder2.getCourseID());
//        assertEquals("Project", reminder2.getReminderType());
//        assertEquals("01/01/2019", reminder2.getDueDate());
//        assertEquals(13, dataAccess.getReminders().size());
//
//
//
//        //Testing Insert
//        Reminder reminder3 = new Reminder("CHEM 2000", "Quiz", "07/07/2018");
//        result = dataAccess.insertReminder(reminder3);
//        assertNull(result);
//        reminder3 = dataAccess.getReminders().get(dataAccess.getReminders().indexOf(reminder3));
//        assertNotNull(reminder3);
//        assertEquals("CHEM 2000", reminder3.getCourseID());
//        assertEquals("Quiz", reminder3.getReminderType());
//        assertEquals("07/07/2018", reminder3.getDueDate());
//        assertEquals(14, dataAccess.getReminders().size());
//
//
//
//        //Testing Delete
//        result = dataAccess.deleteReminder(reminder2);
//        assertNull(result);
//        result = dataAccess.deleteReminder(reminder3);
//        assertNull(result);
//        assertEquals(-1, dataAccess.getCompletedCourses().indexOf(reminder2));
//        assertEquals(-1, dataAccess.getCompletedCourses().indexOf(reminder3));
//        assertEquals(12, dataAccess.getCompletedCourses().size());
//
//
//
//        // testing null
//        Reminder reminder4 = null;
//        result = dataAccess.insertReminder(reminder4);
//        assertNull(result);
//        assertEquals(-1, dataAccess.getReminders().indexOf(reminder4));
//        assertEquals(12, dataAccess.getReminders().size());
//        result = dataAccess.deleteReminder(reminder4);
//        assertNull(result);
//        assertEquals(-1, dataAccess.getReminders().indexOf(reminder4));
//        assertEquals(12, dataAccess.getReminders().size());
//        result = dataAccess.updateReminder(reminder4);
//        assertNull(result);
//        assertEquals(-1, dataAccess.getReminders().indexOf(reminder4));
//        assertEquals(12, dataAccess.getReminders().size());
//
//
//
//        ArrayList <Reminder> list = new ArrayList<Reminder>();
//        Reminder reminder5 = new Reminder("HNSC 2000", "Midterm", "10/10/2018");
//
//
//
//        // testing Empty list
//        dataAccess.getReminders().clear();
//
//        result = dataAccess.getRemindersSeq(list);
//        assertNull(result);
//        assertNotNull(list);
//        assertEquals(0, list.size());
//
//        result = dataAccess.insertReminder(reminder5);
//        assertNull(result);
//        reminder5 = dataAccess.getReminders().get(dataAccess.getReminders().indexOf(reminder5));
//        assertNotNull(reminder5);
//        assertEquals("HNSC 2000", reminder5.getCourseID());
//        assertEquals("Midterm", reminder5.getReminderType());
//        assertEquals("10/10/2018", reminder5.getDueDate());
//        assertEquals(1, dataAccess.getReminders().size());
//
//        Reminder reminder6 = new Reminder("HNSC 2000", "Midterm", "12/12/2018");
//        result = dataAccess.updateReminder(reminder6);
//        assertNull(result);
//        reminder6 = dataAccess.getReminders().get(dataAccess.getReminders().indexOf(reminder6));
//        assertNotNull(reminder6);
//        assertEquals("HNSC 2000", reminder6.getCourseID());
//        assertEquals("Midterm", reminder6.getReminderType());
//        assertEquals("12/12/2018", reminder6.getDueDate());
//        assertEquals(1, dataAccess.getReminders().size());
//
//        result = dataAccess.deleteReminder(reminder5);
//        assertNull(result);
//        result = dataAccess.deleteReminder(reminder5);
//        assertNull(result);
//        assertEquals(-1, dataAccess.getReminders().indexOf(reminder5));
//        assertEquals(0, dataAccess.getReminders().size());
//
//
//        System.out.println("Finished test");

    }
}
