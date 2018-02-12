package com.example.uju.coursetracker.business;

import java.util.List;

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

    public String getCourses(List<Course> courses)
    {
        courses.clear();
        return dataAccess.getCourseSequential(courses);
    }

    public Course getSequential()
    {
        String result = null;
        if (courses == null)
        {
            result = dataAccess.getCourseSequential(courses);
            currentCourse = 0;
        }
        if (currentCourse < courses.size())
        {
            course = (Course) courses.get(currentCourse);
            currentCourse++;
        }
        else
        {
            courses = null;
            course = null;
            currentCourse = 0;
        }
        return course;
    }

    public Course getRandom(String courseID)
    {
        courses = dataAccess.getCourseRandom(new Course(courseID));
        currentCourse = 0;
        if (currentCourse < courses.size())
        {
            course = (Course) courses.get(currentCourse);
            currentCourse++;
        }
        else
        {
            courses = null;
            course = null;
            currentCourse = 0;
        }
        return course;
    }

    public String insertCompletedCourse(Course currentCourse)
    {
        return dataAccess.insertOldCourse(currentCourse);
    }

    public String insertNewCourse(Course currentCourse)
    {
        return dataAccess.insertNewCourse(currentCourse);
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

}
