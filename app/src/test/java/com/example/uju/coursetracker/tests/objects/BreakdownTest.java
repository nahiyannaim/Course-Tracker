package com.example.uju.coursetracker.tests.objects;


import com.example.uju.coursetracker.objects.Breakdown;
import com.example.uju.coursetracker.objects.Course;

import junit.framework.TestCase;

public class BreakdownTest extends TestCase
{
    public void testBreakdown()
    {
        System.out.println("Starting Object Test: testBreakdown");

        Breakdown breakdown = new Breakdown("GEOG 1280", "Term Test 2", 0.25);

        assertNotNull(breakdown);
        assertEquals("GEOG 1280", breakdown.getCourseID());
        assertEquals("Term Test 2", breakdown.getBreakdownName());
        assertEquals(0.25, breakdown.getBreakdownPercentage());

        Breakdown breakdown2 =  breakdown;
        assertSame(breakdown, breakdown2);

        System.out.println("Finished Test");
    }
}
