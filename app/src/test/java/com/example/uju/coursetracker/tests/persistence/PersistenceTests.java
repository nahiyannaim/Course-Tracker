package com.example.uju.coursetracker.tests.persistence;

import junit.framework.Test;
import junit.framework.TestSuite;

public class PersistenceTests
{
    public static TestSuite suite;

    public static Test suite()
    {
        suite = new TestSuite("Persistence tests");
        suite.addTestSuite(DataAccessTest.class);
        return suite;
    }
}
