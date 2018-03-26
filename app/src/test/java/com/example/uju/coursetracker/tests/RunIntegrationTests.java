package com.example.uju.coursetracker.tests;

import com.example.uju.coursetracker.tests.integration.IntegrationTests;
import junit.framework.Test;
import junit.framework.TestSuite;

public class RunIntegrationTests
{
    public static TestSuite suite;

    public static Test suite()
    {
        suite = new TestSuite("Integration tests");
        suite.addTest(IntegrationTests.suite());
        return suite;
    }
}
