package com.example.uju.coursetracker.tests.integration;


import junit.framework.TestCase;

public class BusinessPersistenceSeamTest extends TestCase
{
    public BusinessPersistenceSeamTest(String arg0)
    {
        super(arg0);
    }

//    public void testAccessStudents()
//    {
//        AccessStudents as;
//        Student student;
//        String result;
//
//        Services.closeDataAccess();
//
//        System.out.println("\nStarting Integration test of AccessStudents to persistence");
//
//        Services.createDataAccess(Main.dbName);
//
//        as = new AccessStudents();
//
//        student = as.getRandom("400");
//        assertTrue("400".equals(student.getStudentID()));
//
//        result = as.deleteStudent(student);
//        assertNull(result);
//        assertNull(as.getRandom("400"));
//
//        result = as.insertStudent(student);
//        assertNull(result);
//        student = as.getRandom("400");
//        assertTrue("400".equals(student.getStudentID()));
//
//        student = as.getRandom("500");
//        assertNull(student);
//
//        // Need to add some more tests to exercise all CRUD operations
//
//        Services.closeDataAccess();
//
//        System.out.println("Finished Integration test of AccessStudents to persistence");
//    }
}
