package com.example.uju.coursetracker.tests.objects;


import com.example.uju.coursetracker.objects.Breakdown;
import com.example.uju.coursetracker.objects.Course;

import junit.framework.TestCase;

public class BreakdownTest extends TestCase
{
    public void testBreakdown()
    {
        System.out.println("Starting Object Test: testBreakdown");

        Breakdown breakdown = new Breakdown("Math 1500", "Term Test 2", 0.25);
        Breakdown breakdown2 =  breakdown;

        assertNotNull(breakdown);
        assertEquals("Math 1500", breakdown.getCourseID());
        assertEquals("Term Test 2", breakdown.getBreakdownName());
        assertEquals(0.25, breakdown.getBreakdownPercentage());
        assertSame(breakdown, breakdown2);

        System.out.println("Finished Test");
    }
}
