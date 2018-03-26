package com.example.uju.coursetracker.tests;

import com.example.uju.coursetracker.tests.business.BusinessTests;
import com.example.uju.coursetracker.tests.objects.ObjectTests;
import com.example.uju.coursetracker.tests.persistence.PersistenceTests;
import junit.framework.Test;
import junit.framework.TestSuite;

public class RunUnitTests
{
    public static TestSuite suite;

    public static Test suite()
    {
        suite = new TestSuite("Unit tests");
        suite.addTest(ObjectTests.suite());
        suite.addTest(BusinessTests.suite());
        suite.addTest(PersistenceTests.suite());
        return suite;
    }
}
