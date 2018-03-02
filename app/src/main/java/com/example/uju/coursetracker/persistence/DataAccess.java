package com.example.uju.coursetracker.persistence;


import com.example.uju.coursetracker.objects.Course;
import java.util.ArrayList;
import java.util.List;


public interface DataAccess
{
    void open(String string);

    void close();

    String getCourseSequential(List<Course> courseResult);

    // WE COULD HAVE TWO VERSIONS OF THESE METHODS HERE to facilitate use in AccessCourses.java like in iteration 1
    // insertOldCourse
    // insertNewCourse

    String insertCourse(Course course);

    String updateCourse(Course course);

    String deleteCourse(Course course);

    //ArrayList<SC> getCS(SC sc);
}
