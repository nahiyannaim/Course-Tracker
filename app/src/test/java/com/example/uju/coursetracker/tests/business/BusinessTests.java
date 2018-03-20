package com.example.uju.coursetracker.tests.business;

import junit.framework.Test;
import junit.framework.TestSuite;

public class BusinessTests
{
    public static TestSuite suite;

    public static Test suite()
    {
        suite = new TestSuite("Business tests");
        suite.addTestSuite(AccessCoursesTest.class);
        suite.addTestSuite(AccessRemindersTest.class);
        suite.addTestSuite(CalculateCurrentCGPATest.class);
        suite.addTestSuite(PredictNextCGPATest.class);
        return suite;
    }
}
