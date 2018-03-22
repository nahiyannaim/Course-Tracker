package com.example.uju.coursetracker.acceptance;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AcceptanceTests
{
    public static TestSuite suite;

    static {
        suite = new TestSuite("Acceptance tests");
    }

    public static Test suite()
    {
        suite.addTestSuite(CurrentCGPATest.class);
        suite.addTestSuite(PredictCGPATest.class);
        suite.addTestSuite(RemindersTest.class);
        suite.addTestSuite(MapTest.class);
        return suite;
    }
}
