package com.example.uju.coursetracker.business;

import java.util.ArrayList;
import java.util.List;
import com.example.uju.coursetracker.presentation.MainActivity;
import com.example.uju.coursetracker.application.DatabaseService;
import com.example.uju.coursetracker.objects.Course;
import com.example.uju.coursetracker.persistence.DataAccess;


public class AccessCourses
{

    private DataAccess dataAccess;
    private List<Course> completedCourses;
    private List<Course> currentCourses;
    private Course course;
    private Course course2;
    private int count;
    private int count2;

    public AccessCourses()
    {
        //dataAccess = (StubDatabase) DatabaseService.getDataAccess(MainActivity.dbName);
        dataAccess = DatabaseService.getDataAccess(MainActivity.dbName);
        completedCourses = null;
        currentCourses = null;
        course = null;
        count = 0;
        count2 = 0;
    }



    /////////////////////////////
    //Completed Courses:
    /////////////////////////////

    public String getCompletedCoursesSeq(List<Course> courses)
    {
        courses.clear();
        return dataAccess.getCompletedCoursesSeq(courses);
    }

    public String insertCompletedCourse(Course currentCourse)
    {
        return dataAccess.insertCompletedCourse(currentCourse);
    }

    public String updateCompletedCourse(Course currentCourse)
    {
        return dataAccess.updateCompletedCourse(currentCourse);
    }

    public String deleteCompletedCourse(Course currentCourse)
    {
        return dataAccess.deleteCompletedCourse(currentCourse);
    }

    public Course getSequentialCompleted()
    {
        String result = null;
        if (completedCourses == null)
        {
            // the following line was added as a result of a failing test in AccessCoursesTest!
            completedCourses = new ArrayList<Course>();
            result = dataAccess.getCompletedCoursesSeq(completedCourses);
            count = 0;
        }
        if (count < completedCourses.size())
        {
            course = completedCourses.get(count);
            count++;
        }
        else
        {
            completedCourses = null;
            course = null;
            count = 0;
        }
        return course;
    }



    /////////////////////////////
    //Current Courses:
    /////////////////////////////

    public String getCurrentCoursesSeq(List<Course> courses)
    {
        courses.clear();
        return dataAccess.getCurrentCoursesSeq(courses);
    }

    public String insertCurrentCourse(Course currentCourse)
    {
        return dataAccess.insertCurrentCourse(currentCourse);
    }

    public String updateCurrentCourse(Course currentCourse)
    {
        return dataAccess.updateCurrentCourse(currentCourse);
    }

    public String deleteCurrentCourse(Course currentCourse)
    {
        return dataAccess.deleteCurrentCourse(currentCourse);
    }

    public Course getSequentialCurrent()
    {
        String result = null;
        if (currentCourses == null)
        {
            // the following line was added as a result of a failing test in AccessCoursesTest!
            currentCourses = new ArrayList<Course>();
            result = dataAccess.getCurrentCoursesSeq(currentCourses);
            count2 = 0;
        }
        if (count2 < currentCourses.size())
        {
            course2 = currentCourses.get(count2);
            count2++;
        }
        else
        {
            currentCourses = null;
            course2 = null;
            count2 = 0;
        }
        return course2;
    }

}
