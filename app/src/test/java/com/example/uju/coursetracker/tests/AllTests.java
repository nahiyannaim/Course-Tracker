package com.example.uju.coursetracker.tests;

import com.example.uju.coursetracker.tests.business.CalculateCurrentCGPATest;
import com.example.uju.coursetracker.tests.business.PredictNextCGPATest;
import com.example.uju.coursetracker.tests.objects.BreakdownTest;
import com.example.uju.coursetracker.tests.objects.CourseTest;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests
{
    public static TestSuite suite;

    public static Test suite()
    {
        suite = new TestSuite("All tests for the project");
        testObjects();
        testBusiness();
        return suite;
    }

    private static void testObjects()
    {
        suite.addTestSuite(BreakdownTest.class);
        suite.addTestSuite(CourseTest.class);
    }

    private static void testBusiness()
    {
        suite.addTestSuite(CalculateCurrentCGPATest.class);
        suite.addTestSuite(PredictNextCGPATest.class);
    }
}
