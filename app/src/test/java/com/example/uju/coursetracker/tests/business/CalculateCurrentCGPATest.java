package com.example.uju.coursetracker.tests.business;

import com.example.uju.coursetracker.business.CalculateCurrentCGPA;
import com.example.uju.coursetracker.objects.Course;
import junit.framework.TestCase;
import java.util.ArrayList;

public class CalculateCurrentCGPATest extends TestCase
{
    public void testValidGrades()
    {

        System.out.println("Starting Test: Valid grades");

        ArrayList<Course> list = new ArrayList();
        double result;
        CalculateCurrentCGPA temp = new CalculateCurrentCGPA();

        list.add(new Course("COMP 1010", "Intro to CS", "A"));
        list.add(new Course("MATH 1500", "Calculus", "B+"));


        result = temp.calculate(list);

        assertNotNull(result);
        assertEquals(3.75, result);


        System.out.println("Finished Test");
    }

    public void testAllValidGrades()
    {

        System.out.println("Starting Test: All Valid grades");

        ArrayList<Course> list = new ArrayList();
        double result;
        CalculateCurrentCGPA temp = new CalculateCurrentCGPA();

        list.add(new Course("COMP 1010", "Intro to CS", "A+"));
        list.add(new Course("COMP 1020", "Advanced CS", "A"));
        list.add(new Course("PHYS 1500", "Mechanics", "B+"));
        list.add(new Course("CHEM 1300", "Organic Chemistry", "B"));
        list.add(new Course("MATH 1700", "Advanced Calculus", "C+"));
        list.add(new Course("FMLY 1000", "Family Development", "C"));
        list.add(new Course("GEOG 1280", "Human Geography", "D"));
        list.add(new Course("Math 1500", "Intro Calculus", "F"));

        result = temp.calculate(list);

        assertNotNull(result);
        assertEquals(2.56, result);


		System.out.println("Finished Test");
    }

    public void testInvalidGrades()
    {

        System.out.println("Starting Test: Invalid grades");

        ArrayList<Course> list = new ArrayList();
        double result;
        CalculateCurrentCGPA temp = new CalculateCurrentCGPA();

        list.add(new Course("COMP 1010", "Intro to CS", "P+"));
        list.add(new Course("COMP 1020", "Advanced CS", "S"));


        result = temp.calculate(list);

        assertNotNull(result);
        assertEquals(-1.0, result);


        System.out.println("Finished Test");
    }

    public void testValidAndInvalidGrades()
    {

        System.out.println("Starting Test: Valid and Invalid grades");

        ArrayList<Course> list = new ArrayList();
        double result;
        CalculateCurrentCGPA temp = new CalculateCurrentCGPA();

        list.add(new Course("MATH 1010", "Intro to Math", "P+"));
        list.add(new Course("PHYS 1010", "Intro to Physics", "A"));
        list.add(new Course("CHEM 1010", "Intro to Chemistry", "S"));
        list.add(new Course("BIOL 1010", "Intro to Biology", "B+"));


        result = temp.calculate(list);

        assertNotNull(result);
        assertEquals(-1.0, result);


        System.out.println("Finished Test");
    }

    public void testEmptyGrades()
    {
        System.out.println("Starting test: Empty grades");

        ArrayList<Course> list = new ArrayList();
        CalculateCurrentCGPA temp = new CalculateCurrentCGPA();

        list.add(new Course("ENVR 1000", "Environmental Science", " "));
        list.add(new Course("STAT 2000", "Advanced Statistics", " "));

        double result = temp.calculate(list);

        assertNotNull(result);
        assertEquals(-1.0, result);

        System.out.println("Finished Test");
    }

    public void testMixedCombinationGrades()
    {

        System.out.println("Starting Test: Mixed Combination grades");

        ArrayList<Course> list = new ArrayList();
        CalculateCurrentCGPA temp = new CalculateCurrentCGPA();
        double result;

        list.add(new Course("MATH 1010", "Intro to Math", "A"));
        list.add(new Course("PHYS 1010", "Intro to Physics", " "));
        list.add(new Course("CHEM 1010", "Intro to Chemistry", "X"));
        list.add(new Course("BIOL 1010", "Intro to Biology", "2"));
        list.add(new Course("COMP 1010", "Intro to Programming", "B+"));

        result = temp.calculate(list);

        assertNotNull(result);
        assertEquals(-1.0, result);


        System.out.println("Finished Test");
    }

    public void testEmptyList()
    {
        System.out.println("Starting test: Empty list");

        ArrayList<Course> list = new ArrayList();
        CalculateCurrentCGPA temp = new CalculateCurrentCGPA();

        double result = temp.calculate(list);

        assertNotNull(result);
        assertEquals(-1.0, result);

        System.out.println("Finished test");
    }

    public void testNullList()
    {
        System.out.println("Starting test: null list");

        CalculateCurrentCGPA temp = new CalculateCurrentCGPA();
        double result = temp.calculate(null);

        assertNotNull(result);
        assertEquals(-1.0, result);

        System.out.println("Finished Test");
    }

    public void testNullListItem()
    {
        System.out.println("Starting test: null list item");

        CalculateCurrentCGPA temp = new CalculateCurrentCGPA();
        ArrayList<Course> list = new ArrayList();
        list.add(null);
        list.add(null);
        list.add(null);

        double result = temp.calculate(list);

        assertNotNull(result);
        assertEquals(-1.0, result);

        System.out.println("Finished Test");
    }

    public void testLowerCaseGrades()
    {

        System.out.println("Starting Test: Lower Case grades");

        ArrayList<Course> list = new ArrayList();
        CalculateCurrentCGPA temp = new CalculateCurrentCGPA();
        double result;

        list.add(new Course("MATH 1010", "Intro to Math", "a+"));
        list.add(new Course("MATH 2000", "Intro to Math2", "a"));
        list.add(new Course("PHYS 1010", "Intro to Physics", "b+"));
        list.add(new Course("PHYS 2000", "Intro to Physics2", "b"));
        list.add(new Course("CHEM 1010", "Intro to Chemistry", "c+"));
        list.add(new Course("BIOL 1010", "Intro to Biology", "c"));
        list.add(new Course("COMP 1010", "Intro to Programming", "d"));
        list.add(new Course("COMP 2000", "Intro to Programming2", "f"));

        result = temp.calculate(list);

        assertNotNull(result);
        assertEquals(2.56, result);


        System.out.println("Finished Test");
    }

    public void testMixedCasesGrades()
    {

        System.out.println("Starting Test: Mixed Cases grades");

        ArrayList<Course> list = new ArrayList();
        CalculateCurrentCGPA temp = new CalculateCurrentCGPA();
        double result;

        list.add(new Course("MATH 1010", "Intro to Math", "a"));
        list.add(new Course("PHYS 1010", "Intro to Physics", "b+"));
        list.add(new Course("CHEM 1010", "Intro to Chemistry", "A"));
        list.add(new Course("BIOL 1010", "Intro to Biology", "C"));
        list.add(new Course("COMP 1010", "Intro to Programming", "d"));

        result = temp.calculate(list);

        assertNotNull(result);
        assertEquals(2.9, result);


        System.out.println("Finished Test");
    }

}

