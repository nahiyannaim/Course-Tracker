package com.example.uju.coursetracker.business;

import java.util.ArrayList;
import java.util.List;
import com.example.uju.coursetracker.application.MainActivity;
import com.example.uju.coursetracker.application.DatabaseService;
import com.example.uju.coursetracker.objects.Course;
//import com.example.uju.coursetracker.persistence.StubDatabase;
import com.example.uju.coursetracker.persistence.DataAccess;

public class AccessCourses
{
//    private StubDatabase dataAccess;
    private DataAccess dataAccess;
    private List<Course> courses;
    private Course course;
    private int currentCourse;

    public AccessCourses()
    {
//        dataAccess = (StubDatabase) DatabaseService.getDataAccess(MainActivity.dbName);
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
            // the following line was added as a result of a failing test in AccessCoursesTest!
            courses = new ArrayList<Course>();
            result = dataAccess.getCourseSequential(courses);
            currentCourse = 0;
        }
        if (currentCourse < courses.size())
        {
            course = courses.get(currentCourse);
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

    public String insertCourse(Course currentCourse)
    {
        return dataAccess.insertCourse(currentCourse);
    }

    public String updateCourse(Course currentCourse)
    {
        return dataAccess.updateCourse(currentCourse);
    }

    public String deleteCourse(Course currentCourse)
    {
        return dataAccess.deleteCourse(currentCourse);
    }

}
