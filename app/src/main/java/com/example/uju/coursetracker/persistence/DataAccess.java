package com.example.uju.coursetracker.persistence;


import com.example.uju.coursetracker.objects.Course;
import java.util.ArrayList;
import java.util.List;


public interface DataAccess
{
    void open(String string);

    void close();

    String getCompletedCoursesSeq(List<Course> completedCoursesList);

    ArrayList<Course> getCompletedCourses();

    String insertCompletedCourse(Course course);

    String updateCompletedCourse(Course course);

    String deleteCompletedCourse(Course course);



    // WE COULD HAVE TWO VERSIONS OF THESE METHODS HERE to facilitate use in AccessCourses.java like in iteration 1
    // insertOldCourse
    // insertNewCourse



    //ArrayList<SC> getCS(SC sc);
}
