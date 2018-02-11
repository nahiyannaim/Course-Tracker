package com.example.uju.coursetracker.tests.business;


import com.example.uju.coursetracker.business.CalculateCurrentCGPA;
import com.example.uju.coursetracker.business.PredictNextCGPA;
import com.example.uju.coursetracker.objects.Course;

import junit.framework.TestCase;

import java.util.ArrayList;

public class PredictNextCGPATest extends TestCase
{
    public void testValidGrades()
    {

        System.out.println("Starting Test: Valid grades");

        ArrayList<Course> list = new ArrayList();
        double result;

        list.add(new Course("COMP 1010", "Intro to CS", "A"));
        list.add(new Course("MATH 1500", "Calculus", "A"));
        list.add(new Course("COMP 1010", "Intro to CS", "B+"));
        list.add(new Course("MATH 1500", "Calculus", "B+"));


        result = PredictNextCGPA.calculate(list, 3.47, 18);

        assertNotNull(result);
        assertEquals(3.52, result);


        System.out.println("Finished Test");
    }
}
