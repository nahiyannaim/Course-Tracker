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
        dataAccess = new DataAccessStub();
        dataAccess.open("Stub");

        // or switch to the real HSQLDB database:
//         dataAccess = new DataAccessObject(MainActivity.dbName);
//         dataAccess.open(MainActivity.getDBPathName());
    }

    public void tearDown()
    {
        dataAccess.close();
        System.out.println("\nFinished Persistence test DataAccess (using stub)");
    }

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



        //Testing Update for COMP 1010 with different grade
        Course course2 = new Course("COMP 1010", "Introduction To Java 1", "B");
        result = dataAccess.updateCompletedCourse(course2);
        assertNull(result);
        course2 = dataAccess.getCompletedCourses().get(dataAccess.getCompletedCourses().indexOf(course2));
        assertNotNull(course2);
        assertEquals("COMP 1010", course2.getCourseID());
        assertEquals("Introduction To Java 1", course2.getCourseName());
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



        //Testing null
        Course course4 = null;
        result = dataAccess.insertCompletedCourse(course4);
        assertTrue(result.length() > 0); //inserting a null course returns a string
        assertEquals(-1, dataAccess.getCompletedCourses().indexOf(course4));
        assertEquals(11, dataAccess.getCompletedCourses().size());
        result = dataAccess.deleteCompletedCourse(course4);
        assertTrue(result.length() > 0); //deleting a null course returns a string
        assertEquals(-1, dataAccess.getCompletedCourses().indexOf(course4));
        assertEquals(11, dataAccess.getCompletedCourses().size());
        result = dataAccess.updateCompletedCourse(course4);
        assertTrue(result.length() > 0); //updating a null course returns a string
        assertEquals(-1, dataAccess.getCompletedCourses().indexOf(course4));
        assertEquals(11, dataAccess.getCompletedCourses().size());



        //Testing database without completed courses
        //delete all completed courses from database
        Course deletedCourse1 = new Course("COMP 1020", "Introduction To Java 2", "B+");
        result = dataAccess.deleteCompletedCourse(deletedCourse1);
        assertNull(result);
        Course deletedCourse2 = new Course("COMP 2140", "Data Structures", "B");
        result = dataAccess.deleteCompletedCourse(deletedCourse2);
        assertNull(result);
        Course deletedCourse3 = new Course("COMP 2080", "Analysis Of Algorithms", "C+");
        result = dataAccess.deleteCompletedCourse(deletedCourse3);
        assertNull(result);
        Course deletedCourse4 = new Course("COMP 3010", "Distributed Computing", "C");
        result = dataAccess.deleteCompletedCourse(deletedCourse4);
        assertNull(result);
        Course deletedCourse5 = new Course("COMP 3020", "Human-Computer Interaction", "A");
        result = dataAccess.deleteCompletedCourse(deletedCourse5);
        assertNull(result);
        Course deletedCourse6 = new Course("COMP 3350", "Software Development", "A+");
        result = dataAccess.deleteCompletedCourse(deletedCourse6);
        assertNull(result);
        Course deletedCourse7 = new Course("COMP 3380", "Introduction To Databases", "A+");
        result = dataAccess.deleteCompletedCourse(deletedCourse7);
        assertNull(result);
        Course deletedCourse8 = new Course("FMLY 1000", "Introduction To Family Studies", "B+");
        result = dataAccess.deleteCompletedCourse(deletedCourse8);
        assertNull(result);
        Course deletedCourse9 = new Course("MATH 1500", "Calculus 1", "A");
        result = dataAccess.deleteCompletedCourse(deletedCourse9);
        assertNull(result);
        Course deletedCourse10 = new Course("MATH 1700", "Calculus 2", "A+");
        result = dataAccess.deleteCompletedCourse(deletedCourse10);
        assertNull(result);
        Course deletedCourse11 = new Course("WOMN 3000", "Women And Gender Studies", "D");
        result = dataAccess.deleteCompletedCourse(deletedCourse11);
        assertNull(result);

        //Inserting a new course to database
        ArrayList <Course> list = new ArrayList<Course>();
        Course course5 = new Course("HNSC 2000", "Human Nutrition", "C");

        result = dataAccess.getCompletedCoursesSeq(list);
        assertNull(result);
        assertNotNull(list);
        assertEquals(0, list.size());

        result = dataAccess.insertCompletedCourse(course5);
        assertNull(result);
        course5 = dataAccess.getCompletedCourses().get(dataAccess.getCompletedCourses().indexOf(course5));
        assertNotNull(course5);
        assertEquals("HNSC 2000", course5.getCourseID());
        assertEquals("Human Nutrition", course5.getCourseName());
        assertEquals("C", course5.getGrade());
        assertEquals(1, dataAccess.getCompletedCourses().size());

        //Updating grade of the new course
        Course course6 = new Course("HNSC 2000", "Human Nutrition", "A+");
        result = dataAccess.updateCompletedCourse(course6);
        assertNull(result);
        course6 = dataAccess.getCompletedCourses().get(dataAccess.getCompletedCourses().indexOf(course6));
        assertNotNull(course6);
        assertEquals("HNSC 2000", course6.getCourseID());
        assertEquals("Human Nutrition", course6.getCourseName());
        assertEquals("A+", course6.getGrade());
        assertEquals(1, dataAccess.getCompletedCourses().size());

        //Deleting the new course
        result = dataAccess.deleteCompletedCourse(course6);
        assertNull(result);
        assertEquals(-1, dataAccess.getCompletedCourses().indexOf(course5));
        assertEquals(0, dataAccess.getCompletedCourses().size());




        /////// **************************************************************
        //   CLEAN UP PUT BACK ORIGINAL CONTENTS to DB here otherwise it'd fail

        //Create and restore all the completed courses again
        Course restoreCourse1 = new Course("COMP 1010", "Introduction To Java 1", "A");
        result = dataAccess.insertCompletedCourse(restoreCourse1);
        assertNull(result);
        Course restoreCourse2 = new Course("COMP 1020", "Introduction To Java 2", "B+");
        result = dataAccess.insertCompletedCourse(restoreCourse2);
        assertNull(result);
        Course restoreCourse3 = new Course("COMP 2140", "Data Structures", "B");
        result = dataAccess.insertCompletedCourse(restoreCourse3);
        assertNull(result);
        Course restoreCourse4 = new Course("COMP 2080", "Analysis Of Algorithms", "C+");
        result = dataAccess.insertCompletedCourse(restoreCourse4);
        assertNull(result);
        Course restoreCourse5 = new Course("COMP 3010", "Distributed Computing", "C");
        result = dataAccess.insertCompletedCourse(restoreCourse5);
        assertNull(result);
        Course restoreCourse6 = new Course("COMP 3020", "Human-Computer Interaction", "A");
        result = dataAccess.insertCompletedCourse(restoreCourse6);
        assertNull(result);
        Course restoreCourse7 = new Course("COMP 3350", "Software Development", "A+");
        result = dataAccess.insertCompletedCourse(restoreCourse7);
        assertNull(result);
        Course restoreCourse8 = new Course("COMP 3380", "Introduction To Databases", "A+");
        result = dataAccess.insertCompletedCourse(restoreCourse8);
        assertNull(result);
        Course restoreCourse9 = new Course("FMLY 1000", "Introduction To Family Studies", "B+");
        result = dataAccess.insertCompletedCourse(restoreCourse9);
        assertNull(result);
        Course restoreCourse10 = new Course("MATH 1500", "Calculus 1", "A");
        result = dataAccess.insertCompletedCourse(restoreCourse10);
        assertNull(result);
        Course restoreCourse11 = new Course("MATH 1700", "Calculus 2", "A+");
        result = dataAccess.insertCompletedCourse(restoreCourse11);
        assertNull(result);
        Course restoreCourse12 = new Course("WOMN 3000", "Women And Gender Studies", "D");
        result = dataAccess.insertCompletedCourse(restoreCourse12);
        assertNull(result);

        System.out.println("Finished test");
    }

    public void testCurrentCoursesDB()
    {
        System.out.println("\nStarting test DataAccessTest for: Current Courses");

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
        assertEquals("ECON 1020", course.getCourseID());
        assertEquals("Introduction To Economics", course.getCourseName());
        assertEquals("A", course.getGrade());



        //Testing Update for ECON 1020 with different grade
        Course course2 = new Course("ECON 1020", "Introduction To Economics", "B");
        result = dataAccess.updateCurrentCourse(course2);
        assertNull(result);
        course2 = dataAccess.getCurrentCourses().get(dataAccess.getCurrentCourses().indexOf(course2));
        assertNotNull(course2);
        assertEquals("ECON 1020", course2.getCourseID());
        assertEquals("Introduction To Economics", course2.getCourseName());
        assertEquals("B", course2.getGrade());
        assertEquals(3, dataAccess.getCurrentCourses().size());



        //Testing Insert
        Course course3 = new Course("PHYS 4000", "Advanced Physics 2", "B+");
        result = dataAccess.insertCurrentCourse(course3);
        assertNull(result);
        course3 = dataAccess.getCurrentCourses().get(dataAccess.getCurrentCourses().indexOf(course3));
        assertNotNull(course3);
        assertEquals("PHYS 4000", course3.getCourseID());
        assertEquals("Advanced Physics 2", course3.getCourseName());
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



        //Testing null
        Course course4 = null;
        result = dataAccess.insertCurrentCourse(course4);
        assertTrue(result.length() > 0); //inserting a null course returns a string
        assertEquals(-1, dataAccess.getCurrentCourses().indexOf(course4));
        assertEquals(2, dataAccess.getCurrentCourses().size());
        result = dataAccess.deleteCurrentCourse(course4);
        assertTrue(result.length() > 0); //deleting a null course returns a string
        assertEquals(-1, dataAccess.getCurrentCourses().indexOf(course4));
        assertEquals(2, dataAccess.getCurrentCourses().size());
        result = dataAccess.updateCurrentCourse(course4);
        assertTrue(result.length() > 0); //updating a null course returns a string
        assertEquals(-1, dataAccess.getCurrentCourses().indexOf(course4));
        assertEquals(2, dataAccess.getCurrentCourses().size());



        //Testing database without completed courses
        //delete all current courses from database
        Course deletedCourse1 = new Course("SOCO 1200", "Introduction To Sociology", "A");
        result = dataAccess.deleteCurrentCourse(deletedCourse1);
        assertNull(result);
        Course deletedCourse2 = new Course("ENGL 1300", "Intro to English Literature", "A");
        result = dataAccess.deleteCurrentCourse(deletedCourse2);
        assertNull(result);

        //Inserting a new course to database
        ArrayList <Course> list = new ArrayList<Course>();
        Course course5 = new Course("HNSC 3000", "Human Nutrition 2", "C");

        result = dataAccess.getCurrentCoursesSeq(list);
        assertNull(result);
        assertNotNull(list);
        assertEquals(0, list.size());

        result = dataAccess.insertCurrentCourse(course5);
        assertNull(result);
        course5 = dataAccess.getCurrentCourses().get(dataAccess.getCurrentCourses().indexOf(course5));
        assertNotNull(course5);
        assertEquals("HNSC 3000", course5.getCourseID());
        assertEquals("Human Nutrition 2", course5.getCourseName());
        assertEquals("C", course5.getGrade());
        assertEquals(1, dataAccess.getCurrentCourses().size());

        //Updating grade of the new course
        Course course6 = new Course("HNSC 3000", "Human Nutrition 2", "A+");
        result = dataAccess.updateCurrentCourse(course6);
        assertNull(result);
        course6 = dataAccess.getCurrentCourses().get(dataAccess.getCurrentCourses().indexOf(course6));
        assertNotNull(course6);
        assertEquals("HNSC 3000", course6.getCourseID());
        assertEquals("Human Nutrition 2", course6.getCourseName());
        assertEquals("A+", course6.getGrade());
        assertEquals(1, dataAccess.getCurrentCourses().size());

        //Deleting the new course
        result = dataAccess.deleteCurrentCourse(course6);
        assertNull(result);
        assertEquals(-1, dataAccess.getCurrentCourses().indexOf(course5));
        assertEquals(0, dataAccess.getCurrentCourses().size());




        /////// **************************************************************
        //   CLEAN UP PUT BACK ORIGINAL CONTENTS to DB here otherwise it'd fail

        //Create and restore all the current courses again
        Course restoreCourse1 = new Course("SOCO 1200", "Introduction To Sociology", "A");
        result = dataAccess.insertCurrentCourse(restoreCourse1);
        assertNull(result);
        Course restoreCourse2 = new Course("ECON 1020", "Introduction To Economics", "A");
        result = dataAccess.insertCurrentCourse(restoreCourse2);
        assertNull(result);
        Course restoreCourse3 = new Course("ENGL 1300", "Intro to English Literature", "A");
        result = dataAccess.insertCurrentCourse(restoreCourse3);
        assertNull(result);

        System.out.println("Finished test");
    }

    public void testRemindersDB()
    {
        System.out.println("\nStarting test DataAccessTest for: Reminders");

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
        assertEquals("ECON 1020", reminder.getCourseID());
        assertEquals("Assignment", reminder.getReminderType());
        assertEquals("01/09/2018", reminder.getDueDate());


        //Testing Insert
        Reminder reminder2 = new Reminder("CHEM 2000", "Quiz", "07/07/2018");
        result = dataAccess.insertReminder(reminder2);
        assertNull(result);
        reminder2 = dataAccess.getReminders().get(dataAccess.getReminders().indexOf(reminder2));
        assertNotNull(reminder2);
        assertEquals("CHEM 2000", reminder2.getCourseID());
        assertEquals("Quiz", reminder2.getReminderType());
        assertEquals("07/07/2018", reminder2.getDueDate());
        assertEquals(14, dataAccess.getReminders().size());



        //Testing Delete
        result = dataAccess.deleteReminder(reminder);
        assertNull(result);
        result = dataAccess.deleteReminder(reminder2);
        assertNull(result);
        assertEquals(-1, dataAccess.getCompletedCourses().indexOf(reminder));
        assertEquals(-1, dataAccess.getCompletedCourses().indexOf(reminder2));
        assertEquals(12, dataAccess.getCompletedCourses().size());



        // testing null
        Reminder reminder3 = null;
        result = dataAccess.insertReminder(reminder3);
        assertTrue(result.length() > 0); //inserting a null reminder returns a string
        assertEquals(-1, dataAccess.getReminders().indexOf(reminder3));
        assertEquals(12, dataAccess.getReminders().size());
        result = dataAccess.deleteReminder(reminder3);
        assertTrue(result.length() > 0); //inserting a null reminder returns a string
        assertEquals(-1, dataAccess.getReminders().indexOf(reminder3));
        assertEquals(12, dataAccess.getReminders().size());
        result = dataAccess.updateReminder(reminder3);
        assertTrue(result.length() > 0); //inserting a null reminder returns a string
        assertEquals(-1, dataAccess.getReminders().indexOf(reminder3));
        assertEquals(12, dataAccess.getReminders().size());



        //Testing database without reminders
        //delete all reminders from database
        Reminder deletedReminder1 = new Reminder("ECON 1020", "Assignment", "03/15/2018");
        result = dataAccess.deleteReminder(deletedReminder1);
        assertNull(result);
        Reminder deletedReminder2 = new Reminder("ECON 1020", "Final", "04/12/2018");
        result = dataAccess.deleteReminder(deletedReminder2);
        assertNull(result);
        Reminder deletedReminder3 = new Reminder("ECON 1020", "Midterm", "02/23/2018");
        result = dataAccess.deleteReminder(deletedReminder3);
        assertNull(result);
        Reminder deletedReminder4 = new Reminder("ECON 1020", "Quiz", "01/03/2018");
        result = dataAccess.deleteReminder(deletedReminder4);
        assertNull(result);
        Reminder deletedReminder5 = new Reminder("ECON 1020", "Quiz", "01/17/2018");
        result = dataAccess.deleteReminder(deletedReminder5);
        assertNull(result);
        Reminder deletedReminder6 = new Reminder("ENGL 1300", "Assignment", "02/25/2018");
        result = dataAccess.deleteReminder(deletedReminder6);
        assertNull(result);
        Reminder deletedReminder7 = new Reminder("ENGL 1300", "Final", "04/09/2018");
        result = dataAccess.deleteReminder(deletedReminder7);
        assertNull(result);
        Reminder deletedReminder8 = new Reminder("ENGL 1300", "Midterm", "02/19/2018");
        result = dataAccess.deleteReminder(deletedReminder8);
        assertNull(result);
        Reminder deletedReminder9 = new Reminder("SOCO 1200", "Assignment", "01/02/2018");
        result = dataAccess.deleteReminder(deletedReminder9);
        assertNull(result);
        Reminder deletedReminder10 = new Reminder("SOCO 1200", "Assignment", "02/09/2018");
        result = dataAccess.deleteReminder(deletedReminder10);
        assertNull(result);
        Reminder deletedReminder11 = new Reminder("SOCO 1200", "Final", "04/02/2018");
        result = dataAccess.deleteReminder(deletedReminder11);
        assertNull(result);
        Reminder deletedReminder12 = new Reminder("SOCO 1200", "Midterm", "02/20/2018");
        result = dataAccess.deleteReminder(deletedReminder12);
        assertNull(result);

        //Inserting a new reminder to database
        ArrayList <Reminder> list = new ArrayList<Reminder>();
        Reminder reminder5 = new Reminder("HNSC 2000", "Midterm", "10/10/2018");

        result = dataAccess.getRemindersSeq(list);
        assertNull(result);
        assertNotNull(list);
        assertEquals(0, list.size());

        result = dataAccess.insertReminder(reminder5);
        assertNull(result);
        reminder5 = dataAccess.getReminders().get(dataAccess.getReminders().indexOf(reminder5));
        assertNotNull(reminder5);
        assertEquals("HNSC 2000", reminder5.getCourseID());
        assertEquals("Midterm", reminder5.getReminderType());
        assertEquals("10/10/2018", reminder5.getDueDate());
        assertEquals(1, dataAccess.getReminders().size());

        //Deleting the new reminder
        result = dataAccess.deleteReminder(reminder5);
        assertNull(result);
        assertEquals(-1, dataAccess.getReminders().indexOf(reminder5));
        assertEquals(0, dataAccess.getReminders().size());




        /////// **************************************************************
        //   CLEAN UP PUT BACK ORIGINAL CONTENTS to DB here otherwise it'd fail

        //Create and restore all the current courses again
        Reminder restoreReminder1 = new Reminder("ECON 1020", "Assignment", "01/09/2018");
        result = dataAccess.insertReminder(restoreReminder1);
        assertNull(result);
        Reminder restoreReminder2 = new Reminder("ECON 1020", "Assignment", "03/15/2018");
        result = dataAccess.insertReminder(restoreReminder2);
        assertNull(result);
        Reminder restoreReminder3 = new Reminder("ECON 1020", "Final", "04/12/2018");
        result = dataAccess.insertReminder(restoreReminder3);
        assertNull(result);
        Reminder restoreReminder4 = new Reminder("ECON 1020", "Midterm", "02/23/2018");
        result = dataAccess.insertReminder(restoreReminder4);
        assertNull(result);
        Reminder restoreReminder5 = new Reminder("ECON 1020", "Quiz", "01/03/2018");
        result = dataAccess.insertReminder(restoreReminder5);
        assertNull(result);
        Reminder restoreReminder6 = new Reminder("ECON 1020", "Quiz", "01/17/2018");
        result = dataAccess.insertReminder(restoreReminder6);
        assertNull(result);
        Reminder restoreReminder7 = new Reminder("ENGL 1300", "Assignment", "02/25/2018");
        result = dataAccess.insertReminder(restoreReminder7);
        assertNull(result);
        Reminder restoreReminder8 = new Reminder("ENGL 1300", "Final", "04/09/2018");
        result = dataAccess.insertReminder(restoreReminder8);
        assertNull(result);
        Reminder restoreReminder9 = new Reminder("ENGL 1300", "Midterm", "02/19/2018");
        result = dataAccess.insertReminder(restoreReminder9);
        assertNull(result);
        Reminder restoreReminder10 = new Reminder("SOCO 1200", "Assignment", "01/02/2018");
        result = dataAccess.insertReminder(restoreReminder10);
        assertNull(result);
        Reminder restoreReminder11 = new Reminder("SOCO 1200", "Assignment", "02/09/2018");
        result = dataAccess.insertReminder(restoreReminder11);
        assertNull(result);
        Reminder restoreReminder12 = new Reminder("SOCO 1200", "Final", "04/02/2018");
        result = dataAccess.insertReminder(restoreReminder12);
        assertNull(result);
        Reminder restoreReminder13 = new Reminder("SOCO 1200", "Midterm", "02/20/2018");
        result = dataAccess.insertReminder(restoreReminder13);
        assertNull(result);


        System.out.println("Finished test");
    }
}
