package com.example.uju.coursetracker.tests.objects;

import junit.framework.Test;
import junit.framework.TestSuite;

public class ObjectTests
{
    public static TestSuite suite;

    public static Test suite()
    {
        suite = new TestSuite("Object tests");
        suite.addTestSuite(CourseTest.class);
        suite.addTestSuite(BreakdownTest.class);
        suite.addTestSuite(ReminderTest.class);
        return suite;
    }
}
