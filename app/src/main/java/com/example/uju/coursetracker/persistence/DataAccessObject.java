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

    private ArrayList<Course> completedCourses;
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

    public String getCompletedCoursesSeq(List<Course> completedCoursesList)
    {
        Course course;
        String myID, myCourseName, myGrade;
        myID = EOF;
        myCourseName = EOF;
        myGrade = EOF;

        result = null;
        try
        {
            cmdString = "Select * from Courses"; //**************** CHANGE TO completedCourses later
            rs5 = st3.executeQuery(cmdString);
            // ResultSetMetaData md5 = rs5.getMetaData();
            while (rs5.next())
            {
                myID = rs5.getString("CourseID");
                myCourseName = rs5.getString("Name");
                myGrade = rs5.getString("Grade");

                course = new Course(myID, myCourseName, myGrade);
                completedCoursesList.add(course);
            }
            rs5.close();
        }
        catch (Exception e)
        {
            result = processSQLError(e);
        }
        return result;
    }


    public ArrayList<Course> getCompletedCourses()
    {
        String myCourseID, myCourseName, myGrade;
        Course myCS;
        int counter;

        myCourseName = EOF;
        myCourseID = EOF;
        myGrade = EOF;

        counter = 0;
        completedCourses = new ArrayList<Course>();
        try
        {
            cmdString = "Select * from Courses";  //**************** CHANGE TO completedCourses later
            rs4 = st2.executeQuery(cmdString);
            // ResultSetMetaData md4 = rs4.getMetaData();
            while (rs4.next())
            {

                myCourseID = rs4.getString("CourseID");
                myCourseName = rs4.getString("Name");
                myGrade = rs4.getString("Grade");

                myCS = new Course(myCourseID, myCourseName, myGrade);
                completedCourses.add(myCS);
                counter++;
            }
            rs4.close();
        }
        catch (Exception e)
        {
            processSQLError(e);
        }
        return completedCourses;
    }



    public String insertCompletedCourse(Course course)
    {
        String values;

        result = null;
        try
        {
            values =  "'" +course.getCourseID() + "', '" +course.getCourseName() + "', '" +course.getGrade()+"'";
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


    public String updateCompletedCourse(Course course)
    {
        String values;
        String where;

        result = null;
        try
        {
            // Should check for empty values and not update them
            values = "Grade='" +course.getGrade()
                    +"'";
            where = "where CourseID='" +course.getCourseID() +"'";
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

    public String deleteCompletedCourse(Course course)
    {
        String values;

        result = null;
        try
        {
            values = course.getCourseID();
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
