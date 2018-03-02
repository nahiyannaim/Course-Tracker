package com.example.uju.coursetracker.persistence;


import com.example.uju.coursetracker.objects.Course;
import java.util.ArrayList;
import java.util.List;


public interface DataAccess
{
    void open(String string);

    void close();

    String getCompletedCourses(List<Course> completedCourses);

    String insertCourse(Course course);

    String updateCourse(Course course);

    String deleteCourse(Course course);

    //ArrayList<SC> getCS(SC sc);
}
