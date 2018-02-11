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

        System.out.println("Starting Test PredictNextCGPA for: Valid grades");

        ArrayList<Course> list = new ArrayList();
        double result;

        list.add(new Course("COMP 1010", "Intro to CS", "A+"));
        list.add(new Course("MATH 1500", "Calculus", "B+"));


        result = PredictNextCGPA.calculate(list, 3.2, 5);

        assertNotNull(result);
        assertEquals(3.43, result);


        System.out.println("Finished Test");
    }


    public void testAllValidGrades()
    {

        System.out.println("Starting Test PredictNextCGPA for: All Valid grades");

        ArrayList<Course> list = new ArrayList();
        double result;

        list.add(new Course("COMP 1010", "Intro to CS", "A+"));
        list.add(new Course("COMP 1020", "Advanced CS", "A"));
        list.add(new Course("PHYS 1500", "Mechanics", "B+"));
        list.add(new Course("CHEM 1300", "Organic Chemistry", "B"));
        list.add(new Course("MATH 1700", "Advanced Calculus", "C+"));
        list.add(new Course("FMLY 1000", "Family Development", "C"));
        list.add(new Course("GEOG 1280", "Human Geography", "D"));
        list.add(new Course("Math 1500", "Intro Calculus", "F"));

        result = PredictNextCGPA.calculate(list, 4.0, 10);

        assertNotNull(result);
        assertEquals(3.36, result);


        System.out.println("Finished Test");
    }


    public void testInvalidGrades()
    {

        System.out.println("Starting Test PredictNextCGPA for: Invalid grades");

        ArrayList<Course> list = new ArrayList();
        double result;

        list.add(new Course("COMP 1010", "Intro to CS", "P+"));
        list.add(new Course("COMP 1020", "Advanced CS", "S"));


        result = PredictNextCGPA.calculate(list, 3.0, 5);

        assertNotNull(result);
        assertEquals(-1.0, result);


        System.out.println("Finished Test");
    }


    public void testValidAndInvalidGrades()
    {

        System.out.println("Starting Test PredictNextCGPA for: Valid and Invalid grades");

        ArrayList<Course> list = new ArrayList();
        double result;

        list.add(new Course("MATH 1010", "Intro to Math", "P+"));
        list.add(new Course("PHYS 1010", "Intro to Physics", "A"));
        list.add(new Course("CHEM 1010", "Intro to Chemistry", "S"));
        list.add(new Course("BIOL 1010", "Intro to Biology", "B+"));


        result = PredictNextCGPA.calculate(list, 3.0, 5);

        assertNotNull(result);
        assertEquals(-1.0, result);


        System.out.println("Finished Test");
    }



    public void testEmptyGrades()
    {
        System.out.println("Starting test PredictNextCGPA for: Empty grades");

        ArrayList<Course> list = new ArrayList();

        list.add(new Course("ENVR 1000", "Environmental Science", " "));
        list.add(new Course("STAT 2000", "Advanced Statistics", " "));

        double result = PredictNextCGPA.calculate(list, 3.0, 5);

        assertNotNull(result);
        assertEquals(-1.0, result);

        System.out.println("Finished Test");
    }


    public void testMixedCombinationGrades()
    {

        System.out.println("Starting Test PredictNextCGPA for: Mixed Combination grades");

        ArrayList<Course> list = new ArrayList();
        double result;

        list.add(new Course("MATH 1010", "Intro to Math", "A"));
        list.add(new Course("PHYS 1010", "Intro to Physics", " "));
        list.add(new Course("CHEM 1010", "Intro to Chemistry", "X"));
        list.add(new Course("BIOL 1010", "Intro to Biology", "2"));
        list.add(new Course("COMP 1010", "Intro to Programming", "B+"));

        result = PredictNextCGPA.calculate(list, 3.0, 5);

        assertNotNull(result);
        assertEquals(-1.0, result);


        System.out.println("Finished Test");
    }


    public void testEmptyList()
    {
        System.out.println("Starting test PredictNextCGPA for: Empty list");

        ArrayList<Course> list = new ArrayList();

        double result = PredictNextCGPA.calculate(list, 3.0, 5);

        assertNotNull(result);
        assertEquals(-1.0, result);

        System.out.println("Finished test");
    }


    public void testNullList()
    {
        System.out.println("Starting test PredictNextCGPA for: null list");

        double result = PredictNextCGPA.calculate(null, 3.0, 5);

        assertNotNull(result);
        assertEquals(-1.0, result);

        System.out.println("Finished Test");
    }


    public void testInvalidCurrentCGPA()
    {
        System.out.println("Starting test PredictNextCGPA for: Invalid current CGPA");

        ArrayList<Course> list = new ArrayList();

        double result = PredictNextCGPA.calculate(list, 252.7, 5);

        assertNotNull(result);
        assertEquals(-1.0, result);

        System.out.println("Finished Test");
    }


    public void testInvalidTotalCoursesCompleted()
    {
        System.out.println("Starting test PredictNextCGPA for: Invalid total courses completed");

        ArrayList<Course> list = new ArrayList();

        double result = PredictNextCGPA.calculate(list, 3.5, -62);

        assertNotNull(result);
        assertEquals(-1.0, result);

        System.out.println("Finished Test");
    }

    public void testAllInvalid()
    {
        System.out.println("Starting test PredictNextCGPA for: All Invalid ");

        double result = PredictNextCGPA.calculate(null, 52.6, -23);

        assertNotNull(result);
        assertEquals(-1.0, result);

        System.out.println("Finished Test");
    }


    public void testNullListItem()
    {
        System.out.println("Starting test PredictNextCGPA for: null list item");

        ArrayList<Course> list = new ArrayList();
        list.add(null);
        list.add(null);
        list.add(null);

        double result = PredictNextCGPA.calculate(list, 3.0, 5);

        assertNotNull(result);
        assertEquals(-1.0, result);

        System.out.println("Finished Test");
    }

    // Extreme Edge case: Prediction is calculated for first semester of user with no record of previous semester
    // In this case predictor is doing the same thing what currentCGPA does
    public void testFirstSemester()
    {

        System.out.println("Starting Test PredictNextCGPA for: First Semester");

        ArrayList<Course> list = new ArrayList();
        double result;

        list.add(new Course("COMP 1010", "Intro to CS", "A+"));
        list.add(new Course("MATH 1500", "Calculus", "B+"));


        result = PredictNextCGPA.calculate(list, 0, 0);

        assertNotNull(result);
        assertEquals(4.0, result);


        System.out.println("Finished Test");
    }



}
