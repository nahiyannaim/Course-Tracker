/**
 * This code is not used in the first iteration. It is provided as
 * an example of usage of HSQLDB (for iteration 2).
 */


package com.example.uju.coursetracker.persistence;


import com.example.uju.coursetracker.objects.Course;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLWarning;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DataAccessObject implements DataAccess
{
    private Statement st1, st2, st3;
    private Connection c1;
    private ResultSet rs2, rs3, rs4, rs5;

    private String dbName;
    private String dbType;

    private ArrayList<Course> courses;
    //Here we could have 2 lists, completedCourses, currentCourses

    private String cmdString;
    private int updateCount;
    private String result;
    private static String EOF = "  ";

    public DataAccessObject(String dbName)
    {
        this.dbName = dbName;
    }

    public void open(String dbPath)
    {
        String url;
        try
        {
            // Setup for HSQL
            dbType = "HSQL";
            Class.forName("org.hsqldb.jdbcDriver").newInstance();
            url = "jdbc:hsqldb:file:" + dbPath; // stored on disk mode
            c1 = DriverManager.getConnection(url, "SA", "");
            st1 = c1.createStatement();
            st2 = c1.createStatement();
            st3 = c1.createStatement();

        }
        catch (Exception e)
        {
            processSQLError(e);
        }
        System.out.println("Opened " +dbType +" database " +dbPath);
    }

    public void close()
    {
        try
        {	// commit all changes to the database
            cmdString = "shutdown compact";
            rs2 = st1.executeQuery(cmdString);
            c1.close();
        }
        catch (Exception e)
        {
            processSQLError(e);
        }
        System.out.println("Closed " +dbType +" database " +dbName);
    }

    public String getCourseSequential(List<Course> courseResult)
    {
        Course course;
        String myID, myCourseName;
        myID = EOF;
        myCourseName = EOF;

        result = null;
        try
        {
            cmdString = "Select * from Courses";
            rs5 = st3.executeQuery(cmdString);
            // ResultSetMetaData md5 = rs5.getMetaData();
            while (rs5.next())
            {
                myID = rs5.getString("CourseID");
                myCourseName = rs5.getString("Name");
                course = new Course(myID, myCourseName);
                courseResult.add(course);
            }
            rs5.close();
        }
        catch (Exception e)
        {
            result = processSQLError(e);
        }
        return result;
    }

    public String insertCourse(Course currentCourse)
    {
        String values;

        result = null;
        try
        {
            values =  "'" +currentCourse.getCourseID()
                    +"', '" +currentCourse.getCourseName()
                    +"'";
            cmdString = "Insert into Courses " +" Values(" +values +")";
            //System.out.println(cmdString);
            updateCount = st1.executeUpdate(cmdString);
            result = checkWarning(st1, updateCount);
        }
        catch (Exception e)
        {
            result = processSQLError(e);
        }
        return result;
    }


    public String updateCourse(Course currentCourse)
    {
        String values;
        String where;

        result = null;
        try
        {
            // Should check for empty values and not update them
            values = "Name='" +currentCourse.getCourseName()
                    +"'";
            where = "where CourseID='" +currentCourse.getCourseID() +"'";
            cmdString = "Update Courses " +" Set " +values +" " +where;
            //System.out.println(cmdString);
            updateCount = st1.executeUpdate(cmdString);
            result = checkWarning(st1, updateCount);
        }
        catch (Exception e)
        {
            result = processSQLError(e);
        }
        return result;
    }

    public String deleteCourse(Course currentCourse)
    {
        String values;

        result = null;
        try
        {
            values = currentCourse.getCourseID();
            cmdString = "Delete from Courses where CourseID='" +values +"'";
            //System.out.println(cmdString);
            updateCount = st1.executeUpdate(cmdString);
            result = checkWarning(st1, updateCount);
        }
        catch (Exception e)
        {
            result = processSQLError(e);
        }
        return result;
    }

    public String checkWarning(Statement st, int updateCount)
    {
        String result;

        result = null;
        try
        {
            SQLWarning warning = st.getWarnings();
            if (warning != null)
            {
                result = warning.getMessage();
            }
        }
        catch (Exception e)
        {
            result = processSQLError(e);
        }
        if (updateCount != 1)
        {
            result = "Tuple not inserted correctly.";
        }
        return result;
    }

    public String processSQLError(Exception e)
    {
        String result = "*** SQL Error: " + e.getMessage();

        // Remember, this will NOT be seen by the user!
        e.printStackTrace();

        return result;
    }
}
