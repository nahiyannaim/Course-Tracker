package com.example.uju.coursetracker.acceptance;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AcceptanceTests
{
    public static TestSuite suite;

    public static Test suite()
    {
        suite = new TestSuite("Acceptance tests");
        suite.addTestSuite(CurrentCGPATest.class);
       // suite.addTestSuite(PredictCGPATest.class);
        suite.addTestSuite(RemindersTest.class);
        return suite;
    }
}
