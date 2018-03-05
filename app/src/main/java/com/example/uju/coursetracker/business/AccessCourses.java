package com.example.uju.coursetracker.business;

import java.util.ArrayList;
import java.util.List;
import com.example.uju.coursetracker.application.MainActivity;
import com.example.uju.coursetracker.application.DatabaseService;
import com.example.uju.coursetracker.objects.Course;
import com.example.uju.coursetracker.persistence.DataAccess;

public class AccessCourses
{

    private DataAccess dataAccess;
    private List<Course> courses;
    private Course course;
    private int currentCourse;

    public AccessCourses()
    {
        //dataAccess = (StubDatabase) DatabaseService.getDataAccess(MainActivity.dbName);
        dataAccess = DatabaseService.getDataAccess(MainActivity.dbName);
        courses = null;
        course = null;
        currentCourse = 0;
    }

//    public String getCourses(List<Course> courses, String key)
//    {
//        courses.clear();
//        return dataAccess.getCourseSequential(courses, key);
//    }

//    public String updateCompletedCourse(Course currentCourse)
//    {
//        return dataAccess.updateOldCourse(currentCourse);
//    }
//
//    public String updateNewCourse(Course currentCourse)
//    {
//        return dataAccess.updateNewCourse(currentCourse);
//    }
//
//    public String deleteCompletedCourse(Course currentCourse)
//    {
//        return dataAccess.deleteOldCourse(currentCourse);
//    }
//
//    public String deleteNewCourse(Course currentCourse)
//    {
//        return dataAccess.deleteNewCourse(currentCourse);
//    }
//
//    public String insertCompletedCourse(Course currentCourse)
//    {
//        return dataAccess.insertOldCourse(currentCourse);
//    }
//
//    public String insertNewCourse(Course currentCourse)
//    {
//        return dataAccess.insertNewCourse(currentCourse);
//    }


    //ITERATION 2 ****************************
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


}
