package com.example.uju.coursetracker.application;

import com.example.uju.coursetracker.persistence.StubDatabase;

public class DatabaseService
{
    private static StubDatabase stubDatabaseService = null;

    public static StubDatabase createDataAccess(String dbName)
    {
        if (stubDatabaseService == null)
        {
            stubDatabaseService = new StubDatabase(dbName);
            stubDatabaseService.open(dbName);
        }
        return stubDatabaseService;
    }

    public static StubDatabase getDataAccess(String dbName)
    {
        if (stubDatabaseService == null)
        {
            System.out.println("Connection to data access has not been established.");
            System.exit(1);
        }
        return stubDatabaseService;
    }

    public static void closeDataAccess()
    {
        if (stubDatabaseService != null)
        {
            stubDatabaseService.close();
        }
        stubDatabaseService = null;
    }
}

