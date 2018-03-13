package com.example.uju.coursetracker.tests.business;

import com.example.uju.coursetracker.application.DatabaseService;
import com.example.uju.coursetracker.business.AccessCourses;
import com.example.uju.coursetracker.objects.Course;
import com.example.uju.coursetracker.presentation.MainActivity;
import com.example.uju.coursetracker.tests.persistence.StubDatabase;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;


public class AccessCoursesTest extends TestCase
{
    private static String dbName = MainActivity.dbName;

    public AccessCoursesTest(String arg0)
    {
        super(arg0);
    }

    public void testCompletedCourses()
    {
        System.out.println("\nStarting test AccessCourses for: Completed Courses");

        DatabaseService.closeDataAccess();
        DatabaseService.createDataAccess(new StubDatabase(dbName));

        Course course;
        AccessCourses ac;
        String result ="";
        ac = new AccessCourses();



        // Accessing the 1st course
        course = ac.getSequentialCompleted();

        assertNotNull(ac);
        assertNotNull(course);
        assertEquals("COMP 1010", course.getCourseID());
        assertEquals("Introduction To Java 1", course.getCourseName());
        assertEquals("A", course.getGrade());



        // Accessing the 3rd course
        course = ac.getSequentialCompleted();
        course = ac.getSequentialCompleted();

        assertNotNull(ac);
        assertNotNull(course);
        assertEquals("COMP 2140", course.getCourseID());
        assertEquals("Data Structures", course.getCourseName());
        assertEquals("B", course.getGrade());



        // Accessing the 6th course
        course = ac.getSequentialCompleted();
        course = ac.getSequentialCompleted();
        course = ac.getSequentialCompleted();

        assertNotNull(ac);
        assertNotNull(course);
        assertEquals("COMP 3020", course.getCourseID());
        assertEquals("Human-Computer Interaction", course.getCourseName());
        assertEquals("A", course.getGrade());



        // Finished traversing completed courses list in database after 12 items
        course = ac.getSequentialCompleted();
        course = ac.getSequentialCompleted();
        course = ac.getSequentialCompleted();
        course = ac.getSequentialCompleted();
        course = ac.getSequentialCompleted();
        course = ac.getSequentialCompleted();
        course = ac.getSequentialCompleted();

        assertNull(course);
        assertNotNull(ac);




        //// IT WORKS !!!!
        ///////////////////////////////////////////////////////////////////////////////////////////
        ArrayList<Course> lala = new ArrayList<Course>();

        result = ac.insertCompletedCourse(new Course("Arts","aaaa","b"));
        result = ac.updateCompletedCourse(new Course("Arts","aaaa","F"));
        result = ac.updateCompletedCourse(new Course("Arts","aaaa","C+"));

        result = ac.getCompletedCoursesSeq(lala);

        System.out.println((lala.get(lala.indexOf(new Course("Arts","aaaa","F")))).getGrade());
        ///////////////////////////////////////////////////////////////////////////////////////////

        // ... ADD MORE

        DatabaseService.closeDataAccess();
        System.out.println("Finished test");
    }


    public void testCurrentCourses()
    {
        System.out.println("\nStarting test AccessCourses for: Current Courses");

        DatabaseService.closeDataAccess();
        DatabaseService.createDataAccess(new StubDatabase(dbName));

        Course course;
        AccessCourses ac;
        String result ="";
        ac = new AccessCourses();



        // Accessing the 1st course
        course = ac.getSequentialCurrent();

        assertNotNull(ac);
        assertNotNull(course);
        assertEquals("SOCO 1200", course.getCourseID());
        assertEquals("Introduction To Sociology", course.getCourseName());
        assertEquals("A", course.getGrade());



        // Accessing the last course
        course = ac.getSequentialCurrent();
        course = ac.getSequentialCurrent();

        assertNotNull(ac);
        assertNotNull(course);
        assertEquals("ENGL 1300", course.getCourseID());
        assertEquals("Intro To English Literature", course.getCourseName());
        assertEquals("A", course.getGrade());



        // Finished traversing current courses list in database after 3 items
        course = ac.getSequentialCurrent();

        assertNull(course);
        assertNotNull(ac);



        // ... ADD MORE

        DatabaseService.closeDataAccess();
        System.out.println("Finished test");
    }




}
