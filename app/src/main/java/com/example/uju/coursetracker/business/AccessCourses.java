package com.example.uju.coursetracker.business;

import android.util.Log;

import java.util.List;
import java.util.ArrayList;

import com.example.uju.coursetracker.application.MainActivity;
import com.example.uju.coursetracker.application.DatabaseService;
import com.example.uju.coursetracker.objects.Course;
import com.example.uju.coursetracker.persistence.StubDatabase;

public class AccessCourses
{
    private StubDatabase dataAccess;
    private List<Course> courses;
    private Course course;
    private int currentCourse;

    public AccessCourses()
    {
        dataAccess = (StubDatabase) DatabaseService.getDataAccess(MainActivity.dbName);
        courses = null;
        course = null;
        currentCourse = 0;
    }

    public String getCourses(List<Course> courses, String key)
    {
        courses.clear();
        return dataAccess.getCourseSequential(courses, key);
    }

    public ArrayList<Course> getOldCourses()
    {
        courses.clear();
        return getOldCourses();
    }

    public String updateCompletedCourse(Course currentCourse)
    {
        return dataAccess.updateOldCourse(currentCourse);
    }

    public String updateNewCourse(Course currentCourse)
    {
        return dataAccess.updateNewCourse(currentCourse);
    }

    public String deleteCompletedCourse(Course currentCourse)
    {
        return dataAccess.deleteOldCourse(currentCourse);
    }

    public String deleteNewCourse(Course currentCourse)
    {
        return dataAccess.deleteNewCourse(currentCourse);
    }

    public String insertCompletedCourse(Course currentCourse)
    {
        return dataAccess.insertOldCourse(currentCourse);
    }

    public String insertNewCourse(Course currentCourse)
    {
        return dataAccess.insertNewCourse(currentCourse);
    }

}
