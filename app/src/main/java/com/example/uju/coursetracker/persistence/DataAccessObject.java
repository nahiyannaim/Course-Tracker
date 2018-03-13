package com.example.uju.coursetracker.persistence;

import com.example.uju.coursetracker.objects.Course;
import com.example.uju.coursetracker.objects.Reminder;
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
    private ResultSet rs2, rs4, rs5;
    private String dbName;
    private String dbType;

    private ArrayList<Course> completedCourses;
    private ArrayList<Course> currentCourses;
    private ArrayList<Reminder> remindersList;

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
            cmdString = "Select * from CompletedCourses";
            rs5 = st3.executeQuery(cmdString);

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
            cmdString = "Select * from CompletedCourses";
            rs4 = st2.executeQuery(cmdString);
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
            cmdString = "Insert into CompletedCourses " +" Values(" +values +")";
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
            cmdString = "Update CompletedCourses " +" Set " +values +" " +where;
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
            cmdString = "Delete from CompletedCourses where CourseID='" +values +"'";

            updateCount = st1.executeUpdate(cmdString);
            result = checkWarning(st1, updateCount);
        }
        catch (Exception e)
        {
            result = processSQLError(e);
        }

        return result;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //   FOR CURRENT COURSES
    ////////////////////////////////////////////////////////////////////////////////////////////////

    public String getCurrentCoursesSeq(List<Course> currentCoursesList)
    {
        Course course;
        String myID, myCourseName, myGrade;
        myID = EOF;
        myCourseName = EOF;
        myGrade = EOF;

        result = null;
        try
        {
            cmdString = "Select * from CurrentCourses";
            rs5 = st3.executeQuery(cmdString);

            while (rs5.next())
            {
                myID = rs5.getString("CourseID");
                myCourseName = rs5.getString("Name");
                myGrade = rs5.getString("Grade");


                course = new Course(myID, myCourseName, myGrade);
                currentCoursesList.add(course);
            }
            rs5.close();
        }
        catch (Exception e)
        {
            result = processSQLError(e);
        }

        return result;
    }

    public  ArrayList<Course> getCurrentCourses()
    {
        String myCourseID, myCourseName, myGrade;
        Course myCS;
        int counter;

        myCourseName = EOF;
        myCourseID = EOF;
        myGrade = EOF;

        counter = 0;
        currentCourses = new ArrayList<Course>();
        try
        {
            cmdString = "Select * from CurrentCourses";
            rs4 = st2.executeQuery(cmdString);
            while (rs4.next())
            {

                myCourseID = rs4.getString("CourseID");
                myCourseName = rs4.getString("Name");
                myGrade = rs4.getString("Grade");

                myCS = new Course(myCourseID, myCourseName, myGrade);
                currentCourses.add(myCS);
                counter++;
            }
            rs4.close();
        }
        catch (Exception e)
        {
            processSQLError(e);
        }

        return currentCourses;
    }

    public String insertCurrentCourse(Course course)
    {
        String values;

        result = null;
        try
        {
            values =  "'" +course.getCourseID() + "', '" +course.getCourseName() + "', '" +course.getGrade()+"'";
            cmdString = "Insert into CurrentCourses " +" Values(" +values +")";
            updateCount = st1.executeUpdate(cmdString);
            result = checkWarning(st1, updateCount);
        }
        catch (Exception e)
        {
            result = processSQLError(e);
        }

        return result;
    }

    public String updateCurrentCourse(Course course)
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
            cmdString = "Update CurrentCourses " +" Set " +values +" " +where;
            updateCount = st1.executeUpdate(cmdString);
            result = checkWarning(st1, updateCount);
        }
        catch (Exception e)
        {
            result = processSQLError(e);
        }

        return result;
    }

    public  String deleteCurrentCourse(Course course)
    {
        String values;

        result = null;
        try
        {
            values = course.getCourseID();
            cmdString = "Delete from CurrentCourses where CourseID='" +values +"'";
            updateCount = st1.executeUpdate(cmdString);
            result = checkWarning(st1, updateCount);
        }
        catch (Exception e)
        {
            result = processSQLError(e);
        }

        return result;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //   FOR REMINDERS
    ////////////////////////////////////////////////////////////////////////////////////////////////

    public String getRemindersSeq(List<Reminder> list)
    {
        Reminder reminder;
        String myID, myType, myDate;
        myID = EOF;
        myType = EOF;
        myDate = EOF;

        result = null;
        try
        {
            cmdString = "Select * from Reminders";
            rs5 = st3.executeQuery(cmdString);

            while (rs5.next())
            {
                myID = rs5.getString("CourseID");
                myType = rs5.getString("Type");
                myDate = rs5.getString("Date");


                reminder = new Reminder(myID, myType, myDate);
                list.add(reminder);
            }
            rs5.close();
        }
        catch (Exception e)
        {
            result = processSQLError(e);
        }

        return result;
    }

    public ArrayList<Reminder> getReminders()
    {

        String myID, myType, myDate;
        Reminder reminder;
        int counter;

        myID = EOF;
        myType = EOF;
        myDate = EOF;

        counter = 0;
        remindersList = new ArrayList<Reminder>();
        try
        {
            cmdString = "Select * from Reminders";
            rs4 = st2.executeQuery(cmdString);

            while (rs4.next())
            {

                myID = rs4.getString("CourseID");
                myType = rs4.getString("Type");
                myDate = rs4.getString("Date");

                reminder = new Reminder(myID, myType, myDate);
                remindersList.add(reminder);
                counter++;
            }
            rs4.close();
        }
        catch (Exception e)
        {
            processSQLError(e);
        }

        return remindersList;
    }

    public String insertReminder(Reminder reminder)
    {
        String values;

        result = null;
        try
        {
            values =  "'" +reminder.getCourseID() + "', '" +reminder.getReminderType() + "', '" +reminder.getDueDate()+"'";
            cmdString = "Insert into Reminders " +" Values(" +values +")";
            updateCount = st1.executeUpdate(cmdString);
            result = checkWarning(st1, updateCount);
        }
        catch (Exception e)
        {
            result = processSQLError(e);
        }

        return result;
    }

    public String updateReminder(Reminder reminder)
    {
        String values;
        String where;

        result = null;
        try
        {
            // Should check for empty values and not update them
            values = "Grade='" +reminder.getDueDate()
                    +"'";
            where = "where CourseID='" +reminder.getCourseID() +"'";
            cmdString = "Update Reminders " +" Set " +values +" " +where;
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

    public String deleteReminder(Reminder reminder)
    {
        String values;

        result = null;
        try
        {
            values = reminder.getCourseID();
            cmdString = "Delete from Reminders where CourseID='" +values +"'";
            updateCount = st1.executeUpdate(cmdString);
            result = checkWarning(st1, updateCount);
        }
        catch (Exception e)
        {
            result = processSQLError(e);
        }

        return result;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //   FOR WARNINGS
    ////////////////////////////////////////////////////////////////////////////////////////////////

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
