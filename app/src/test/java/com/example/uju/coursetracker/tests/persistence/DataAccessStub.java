package com.example.uju.coursetracker.tests.persistence;

import com.example.uju.coursetracker.objects.Reminder;
import com.example.uju.coursetracker.persistence.DataAccess;
import com.example.uju.coursetracker.presentation.MainActivity;
import com.example.uju.coursetracker.objects.Breakdown;
import com.example.uju.coursetracker.objects.Course;
import java.util.*;

// This is the stub database for the project
// This stub database will have a set of initial contents and will provide the data for Testing

public class DataAccessStub implements DataAccess
{
    private String dbName;
    private String dbType = "stub";
    private ArrayList<Course> oldCourses;
    private ArrayList<Course> newCourses;
    private ArrayList<Reminder> remList;

    public DataAccessStub(String dbName)
    {
        this.dbName = dbName;
    }

    public DataAccessStub()
    {
        this(MainActivity.dbName);
    }

    public void open(String dbName)
    {
        remList = new ArrayList<Reminder>();
        oldCourses= new ArrayList<Course>();
        newCourses= new ArrayList<Course>();
        genCrs(oldCourses, newCourses);
        genRem(remList);

        System.out.println("Opened " +dbType +" database " +dbName);
    }

    private void genRem(ArrayList<Reminder> remList)
    {
        Reminder currRem;

        //ECON 1020 Reminders
        currRem = new Reminder("ECON 1020", "Assignment", "01/09/2018");
        remList.add(currRem);
        currRem = new Reminder("ECON 1020", "Assignment", "03/15/2018");
        remList.add(currRem);
        currRem = new Reminder("ECON 1020", "Final", "04/12/2018");
        remList.add(currRem);
        currRem = new Reminder("ECON 1020", "Midterm", "02/23/2018");
        remList.add(currRem);
        currRem = new Reminder("ECON 1020", "Quiz", "01/03/2018");
        remList.add(currRem);
        currRem = new Reminder("ECON 1020", "Quiz", "01/17/2018");
        remList.add(currRem);

        //ENGL 1300 Reminders
        currRem = new Reminder("ENGL 1300", "Assignment", "02/25/2018");
        remList.add(currRem);
        currRem = new Reminder("ENGL 1300", "Final", "04/09/2018");
        remList.add(currRem);
        currRem = new Reminder("ENGL 1300", "Midterm", "02/19/2018");
        remList.add(currRem);

        //SOCO 1200 Reminders
        currRem = new Reminder("SOCO 1200", "Assignment", "01/02/2018");
        remList.add(currRem);
        currRem = new Reminder("SOCO 1200", "Assignment", "02/09/2018");
        remList.add(currRem);
        currRem = new Reminder("SOCO 1200", "Final", "04/02/2018");
        remList.add(currRem);
        currRem = new Reminder("SOCO 1200", "Midterm", "02/20/2018");
        remList.add(currRem);
    }

    private void genCrs(ArrayList<Course> old ,ArrayList<Course> newC )
    {
        Course course;
        Breakdown nwBk;
        ArrayList<Breakdown> bkDwnLst;
        //Old Courses------------------------------------------------------------------------------
        //Comp Sci
        course = new Course("COMP 1010", "Introduction To Java 1","A");
        old.add(course);
        course = new Course("COMP 1020", "Introduction To Java 2","B+");
        old.add(course);
        course = new Course("COMP 2140", "Data Structures","B");
        old.add(course);
        course = new Course("COMP 2080", "Analysis Of Algorithms","C+");
        old.add(course);

        course = new Course("COMP 3010", "Distributed Computing","C");
        old.add(course);
        course = new Course("COMP 3020", "Human-Computer Interaction","A");
        old.add(course);
        course = new Course("COMP 3350", "Software Development","A+");
        old.add(course);
        course = new Course("COMP 3380", "Introduction To Databases","A+");
        old.add(course);

        //Electives
        course = new Course("FMLY 1000", "Introduction To Family Studies", "B+");
        old.add(course);
        course = new Course("MATH 1500", "Calculus 1","A");
        old.add(course);
        course = new Course("MATH 1700", "Calculus 2","A+");
        old.add(course);
        course = new Course("WOMN 3000", "Women And Gender Studies","D");
        old.add(course);

        //New Courses------------------------------------------------------------------------------
        //Electives
        course = new Course("ECON 1020", "Introduction To Economics", "A");
        bkDwnLst = course.getBreakdownList();
        nwBk = new Breakdown("ECON 1020", "Assignment",0.80);
        bkDwnLst.add(nwBk);
        nwBk = new Breakdown("ECON 1020", "Final",0.20);
        bkDwnLst.add(nwBk);
        newC.add(course);

        course = new Course("ENGL 1300", "Intro To English Literature", "A");
        bkDwnLst = course.getBreakdownList();
        nwBk = new Breakdown("ENGL 1300", "Assignment",0.10);
        bkDwnLst.add(nwBk);
        nwBk = new Breakdown("ENGL 1300", "Project",0.20);
        bkDwnLst.add(nwBk);
        nwBk = new Breakdown("ENGL 1300", "Midterm",0.30);
        bkDwnLst.add(nwBk);
        nwBk = new Breakdown("ENGL 1300", "Final",0.40);
        bkDwnLst.add(nwBk);
        newC.add(course);

        course = new Course("SOCO 1200", "Introduction To Sociology", "A");
        bkDwnLst = course.getBreakdownList();
        nwBk = new Breakdown("SOCO 1200", "Assignment",0.25);
        bkDwnLst.add(nwBk);
        nwBk = new Breakdown("SOCO 1200", "Midterm",0.35);
        bkDwnLst.add(nwBk);
        nwBk = new Breakdown("SOCO 1200", "Final",0.40);
        bkDwnLst.add(nwBk);
        newC.add(course);
    }

    public void close()
    {
        System.out.println("Closed " +dbType +" database " +dbName);
    }

    public String updateCompletedCourse(Course course)
    {
        int index;
        String result;

        if(course != null)
        {
            index = oldCourses.indexOf(course);
            if (index >= 0)
            {
                oldCourses.set(index, course);
            }
            result = null;
        }
        else
        {
            result = "ERROR: Updating a null course";
        }

        return result;
    }

    public String updateReminder(Reminder reminder)
    {
        int index;
        String result;

        if(reminder != null)
        {
            index = remList.indexOf(reminder);
            if (index >= 0)
            {
                remList.set(index, reminder);
            }
            result = null;
        }
        else
        {
            result = "ERROR: Updating a null reminder";
        }

        return result;
    }

    public String updateCurrentCourse(Course course)
    {
        int index;
        String result;

        if(course != null)
        {
            index = newCourses.indexOf(course);
            if (index >= 0)
            {
                newCourses.set(index, course);
            }
            result = null;
        }
        else
        {
            result = "ERROR: Updating a null course";
        }

        return result;
    }

    public String deleteCompletedCourse(Course course)
    {
        int index;
        String result;

        if(course != null)
        {
            index = oldCourses.indexOf(course);
            if (index >= 0)
            {
                oldCourses.remove(index);
            }
            result = null;
        }
        else
        {
            result = "ERROR: Deleting a null course";
        }

        return result;
    }

    public String deleteReminder(Reminder reminder)
    {
        int index;
        String result;

        if(reminder != null)
        {
            index = remList.indexOf(reminder);
            if (index >= 0)
            {
                remList.remove(index);
            }
            result = null;
        }
        else
        {
            result = "ERROR: Deleting a null reminder";
        }

        return result;
    }

    public String deleteCurrentCourse(Course course)
    {
        int index;
        String result;

        if(course != null)
        {
            index = newCourses.indexOf(course);
            if (index >= 0)
            {
                newCourses.remove(index);
            }
            result = null;
        }
        else
        {
            result = "ERROR: Deleting a null course";
        }

        return result;
    }

    public String insertCompletedCourse(Course course)
    {
        String result;

        if(course != null)
        {
            oldCourses.add(course);
            result = null;
        }
        else
        {
            result = "ERROR: Inserting a course which is null";
        }

        return result;
    }

    public String insertReminder(Reminder reminder)
    {
        String result;

        if(reminder != null)
        {
            remList.add(reminder);
            result = null;
        }
        else
        {
            result = "ERROR: Inserting a reminder which is null";
        }

        return result;
    }

    public String insertCurrentCourse(Course course)
    {
        String result;

        if(course != null)
        {
            newCourses.add(course);
            result = null;
        }
        else
        {
            result = "ERROR: Inserting a course which is null";
        }

        return result;
    }

    public String getCompletedCoursesSeq(List<Course> completedCoursesList)
    {
        if(completedCoursesList != null)
        {
            completedCoursesList.addAll(oldCourses);
        }

        return null;
    }

    public String getCurrentCoursesSeq(List<Course> currentCoursesList)
    {
        if(currentCoursesList != null)
        {
            currentCoursesList.addAll(newCourses);
        }
        return null;
    }

    public String getRemindersSeq(List<Reminder> list)
    {
        if(list != null)
        {
            list.addAll(remList);
        }

        return null;
    }

    public  ArrayList<Course> getCompletedCourses()
    {
        return oldCourses;
    }

    public ArrayList<Course> getCurrentCourses()
    {
        return newCourses;
    }

    public  ArrayList<Reminder> getReminders()
    {
        return remList;
    }
}
