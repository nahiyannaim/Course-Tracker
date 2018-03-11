package com.example.uju.coursetracker.tests.persistence;

import com.example.uju.coursetracker.objects.Breakdown;
import com.example.uju.coursetracker.objects.Course;
import com.example.uju.coursetracker.objects.Reminder;
import com.example.uju.coursetracker.persistence.DataAccess;
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
        // or switch to the real database:
        // dataAccess = new DataAccessObject(Main.dbName);
        // dataAccess.open(Main.getDBPathName());
    }

    public void tearDown()
    {
        //**********  SHOULD WE CALL the .close method here since we call open above
        System.out.println("Finished Persistence test DataAccess (using stub)");
    }

    public void test1()
    {

        ArrayList<Course> courses;
        ArrayList<Breakdown> breakdowns;
        ArrayList<Reminder> reminders;

        Course course;
        Breakdown breakdown;
        Reminder reminder;

        String result;


        courses = new ArrayList<Course>();
        result = dataAccess.getCompletedCoursesSeq(courses);
        assertNull(result);
        assertEquals(12, courses.size());
        course = courses.get(0);
        assertEquals("COMP 1010", course.getCourseID());
        // ... ADD MORE



//        students = new ArrayList<Student>();
//        result = dataAccess.getStudentSequential(students);
//        assertNull(result);
//        assertEquals(4, students.size());
//        student = students.get(0);
//        assertEquals("100", student.getStudentID());
//        // ... ADD MORE
//
//
//        scs = dataAccess.getSC(new SC("100", "COMP3010"));
//        sc = scs.get(0);
//        assertEquals("100", sc.getStudentID());
//        assertEquals("COMP3010", sc.getCourseID());
//        // ... ADD MORE
    }
}
