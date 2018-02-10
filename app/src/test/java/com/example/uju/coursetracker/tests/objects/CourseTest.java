package com.example.uju.coursetracker.tests.objects;


import com.example.uju.coursetracker.objects.Course;

import junit.framework.TestCase;

public class CourseTest extends TestCase
{

    public void testCourse()
    {
        System.out.println("Starting Object Test: testCourse");

        Course course = new Course("Math 1500", "Calculus", "A");
        Course course2 = new Course("Math 1500", "Calculus", "A");
        Course course3 = course ;

        assertNotNull(course);
        assertEquals("Math 1500", course.getCourseID());
        assertEquals("Calculus", course.getCourseName());
        assertEquals("A", course.getGrade());
        assertTrue(course.equals(course2));
        assertSame(course, course3);

        System.out.println("Finished Test");
    }
}
