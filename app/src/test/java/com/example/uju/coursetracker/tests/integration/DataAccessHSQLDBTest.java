package com.example.uju.coursetracker.tests.integration;

import com.example.uju.coursetracker.application.DatabaseService;
import com.example.uju.coursetracker.persistence.DataAccess;
import com.example.uju.coursetracker.presentation.MainActivity;
import com.example.uju.coursetracker.tests.persistence.DataAccessTest;

import junit.framework.TestCase;


public class DataAccessHSQLDBTest extends TestCase
{
    private static String dbName = MainActivity.dbName;

    public DataAccessHSQLDBTest(String arg0)
    {
        super(arg0);
    }

    public void testDataAccess()
    {
        DataAccess dataAccess;

        DatabaseService.closeDataAccess();

        System.out.println("\nStarting Integration test DataAccess (using default DB)");

        // Use the following two statements to run with the real database
        DatabaseService.createDataAccess(dbName);
        dataAccess = DatabaseService.getDataAccess(dbName);

        DataAccessTest.dataAccessTest(dataAccess);

        DatabaseService.closeDataAccess();

        System.out.println("Finished Integration test DataAccess (using default DB)");
    }
}
