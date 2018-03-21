package com.example.uju.coursetracker.tests.objects;

import com.example.uju.coursetracker.objects.Breakdown;
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


        //empty values test
        Breakdown breakdown3 = new Breakdown("","",0.0);
        assertNotNull(breakdown3);
        assertEquals("", breakdown3.getCourseID());
        assertEquals("", breakdown3.getBreakdownName());
        assertEquals(0.0, breakdown3.getBreakdownPercentage());


        //null item test
        Breakdown breakdown4 = new Breakdown(null, null, 0);
        assertNotNull(breakdown4);
        assertEquals(null, breakdown4.getCourseID());
        assertEquals(null, breakdown4.getBreakdownName());


        //setters test
        Breakdown breakdown5 = new Breakdown("ECON 1500", "Assignment", 0.3);
        breakdown5.setCourseID("CHEM 1000");
        breakdown5.setBreakdownName("Midterm");
        breakdown5.setBreakdownPercentage(0.5);
        assertNotNull(breakdown5);
        assertEquals("CHEM 1000", breakdown5.getCourseID());
        assertEquals("Midterm", breakdown5.getBreakdownName());
        assertEquals(0.5, breakdown5.getBreakdownPercentage());


        System.out.println("Finished Test");
    }
}
