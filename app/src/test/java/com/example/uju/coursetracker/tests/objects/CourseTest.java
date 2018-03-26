package com.example.uju.coursetracker.tests.objects;

import com.example.uju.coursetracker.objects.Course;
import junit.framework.TestCase;

public class CourseTest extends TestCase
{
    public void testCourse()
    {
        System.out.println("Starting Object Test: testCourse");

        Course course = new Course("Math 1500", "Calculus", "A");
        assertNotNull(course);
        assertEquals("Math 1500", course.getCourseID());
        assertEquals("Calculus", course.getCourseName());
        assertEquals("A", course.getGrade());


        Course course3 = course ;
        assertSame(course, course3);


        //Equality
        Course course2 = new Course("Math 1500", "Calculus", "A");
        assertTrue(course.equals(course2));


        //Inequality
        course3 = new Course("Stat 2000", "Statistics 2", "B");
        assertFalse(course2.equals(course3));


        //empty strings test
        Course course4 = new Course("","","");
        assertNotNull(course4);
        assertEquals("", course4.getCourseID());
        assertEquals("", course4.getCourseName());
        assertEquals("", course4.getGrade());


        //null item test
        Course course6 = new Course(null, null, null);
        assertNotNull(course6);
        assertEquals(null, course6.getCourseID());
        assertEquals(null, course6.getCourseName());
        assertEquals(null, course6.getGrade());


        //setters test
        Course course7 = new Course("ECON 1500", "Economics", "C");
        course7.setCourseID("CHEM 1000");
        course7.setCourseName("Chemistry");
        course7.setGrade("B+");
        assertNotNull(course7);
        assertEquals("CHEM 1000", course7.getCourseID());
        assertEquals("Chemistry", course7.getCourseName());
        assertEquals("B+", course7.getGrade());


        System.out.println("Finished Test");
    }
}