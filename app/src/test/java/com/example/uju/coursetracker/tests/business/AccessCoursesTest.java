package com.example.uju.coursetracker.tests.business;

import com.example.uju.coursetracker.application.DatabaseService;
import com.example.uju.coursetracker.business.AccessCourses;
import com.example.uju.coursetracker.objects.Course;
import com.example.uju.coursetracker.presentation.MainActivity;
import com.example.uju.coursetracker.tests.persistence.StubDatabase;
import junit.framework.TestCase;


public class AccessCoursesTest extends TestCase
{
    private static String dbName = MainActivity.dbName;

    public AccessCoursesTest(String arg0)
    {
        super(arg0);
    }

    public void test1()
    {
        AccessCourses ac;
        Course course;

        DatabaseService.closeDataAccess();

        System.out.println("\nStarting test AccessCourses");

        DatabaseService.createDataAccess(new StubDatabase(dbName));

        ac = new AccessCourses();

        course = ac.getSequentialCompleted();
        assertEquals("COMP 1010", course.getCourseID());
        assertEquals("Introduction To Java 1", course.getCourseName());

        // ... ADD MORE

        // ....Similarly add test for Current also

        DatabaseService.closeDataAccess();
        System.out.println("Finished test AccessCourses");
    }
}
