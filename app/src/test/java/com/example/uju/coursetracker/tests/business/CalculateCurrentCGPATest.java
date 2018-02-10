package com.example.uju.coursetracker.tests.business;


import com.example.uju.coursetracker.business.CalculateCurrentCGPA;
import com.example.uju.coursetracker.objects.Course;

import junit.framework.TestCase;

import java.util.ArrayList;

public class CalculateCurrentCGPATest extends TestCase {

    public void testValidGrades()
    {

        System.out.println("Starting Test with valid grades: ");

        ArrayList<Course> list = new ArrayList();
        double result;

        list.add(new Course("COMP 1010", "Intro to CS", "A+"));
        list.add(new Course("COMP 1020", "Advanced CS", "A"));
        list.add(new Course("PHYS 1500", "Mechanics", "B+"));
        list.add(new Course("CHEM 1300", "Organic Chemistry", "B+"));
        list.add(new Course("MATH 1700", "Calculus", "C+"));

        result = CalculateCurrentCGPA.calculate(list);

        assertNotNull(result);
        assertEquals(3.6, result);


		System.out.println("Finished Test");
    }
}

