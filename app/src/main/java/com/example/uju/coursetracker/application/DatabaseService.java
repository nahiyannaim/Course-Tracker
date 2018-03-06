package com.example.uju.coursetracker.application;

import com.example.uju.coursetracker.persistence.StubDatabase;
import com.example.uju.coursetracker.persistence.DataAccess;
import com.example.uju.coursetracker.persistence.DataAccessObject;

public class DatabaseService
{
    //TOD0 CHANGE VARIABLE TYPE FROM StubDatabase to DataAccess
    //private static StubDatabase dataAccessService = null;
    private static DataAccess dataAccessService = null;

//    public static StubDatabase createDataAccess(String dbName)
//    {
//        if (dataAccessService == null)
//        {
//            dataAccessService = new StubDatabase(dbName);
//            dataAccessService.open(dbName);
//        }
//        return dataAccessService;
//    }

//    public static StubDatabase getDataAccess(String dbName)
//    {
//        if (dataAccessService == null)
//        {
//            System.out.println("Connection to data access has not been established.");
//            System.exit(1);
//        }
//        return dataAccessService;
//    }

    //For Iteration 2
    //***************************************************************************************
    public static DataAccess createDataAccess(String dbName)
    {
        if (dataAccessService == null)
        {
            dataAccessService = new DataAccessObject(dbName);
            dataAccessService.open(MainActivity.getDBPathName());
        }
        return dataAccessService;
    }

    public static DataAccess createDataAccess(DataAccess alternateDataAccessService)
    {
        if (dataAccessService == null)
        {
            dataAccessService = alternateDataAccessService;
            dataAccessService.open(MainActivity.getDBPathName());
        }

        return dataAccessService;
    }
    //************************************************************************************

    public static DataAccess getDataAccess(String dbName)
    {
        if (dataAccessService == null)
        {
            System.out.println("Connection to data access has not been established.");
            System.exit(1);
        }
        return dataAccessService;
    }

    public static void closeDataAccess()
    {
        if (dataAccessService != null)
        {
            dataAccessService.close();
        }
        dataAccessService = null;
    }

}

