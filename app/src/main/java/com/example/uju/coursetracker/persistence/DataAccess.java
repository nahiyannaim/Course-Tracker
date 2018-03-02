package com.example.uju.coursetracker.persistence;


import com.example.uju.coursetracker.objects.Course;
import java.util.ArrayList;
import java.util.List;


public interface DataAccess
{
    void open(String string);

    void close();


    //For Completed Courses
    String getCompletedCoursesSeq(List<Course> completedCoursesList);

    ArrayList<Course> getCompletedCourses();

    String insertCompletedCourse(Course course);

    String updateCompletedCourse(Course course);

    String deleteCompletedCourse(Course course);


    //For Current Semester Courses
    String getCurrentCoursesSeq(List<Course> currentCoursesList);

    ArrayList<Course> getCurrentCourses();

    String insertCurrentCourse(Course course);

    String updateCurrentCourse(Course course);

    String deleteCurrentCourse(Course course);

}
