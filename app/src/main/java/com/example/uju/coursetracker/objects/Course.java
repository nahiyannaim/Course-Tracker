package com.example.uju.coursetracker.objects;

import java.util.ArrayList;

public class Course
{
    private String courseID; // "COMP 1010"
    private String courseName; // "Introduction to Computer Programming"
    private ArrayList<Breakdown> breakdownList; // List: [br1, br2, br3]  For e.g. Assignment br1, Midterm br2, Final br3
    private String grade;

    public Course(String id, String name, String grade)
    {
        this.courseID = id;
        this.courseName = name;
        this.breakdownList = new ArrayList<>();
        this.grade = grade;
    }

    //Constructor for new courses with no assigned grade, grade wont be instantiated
    public Course(String id, String name)
    {
        this.courseID = id;
        this.courseName = name;
        this.breakdownList = new ArrayList<>();
    }

    public String getCourseID()
    {
        return courseID;
    }

    public void setCourseID(String courseID)
    {
        this.courseID = courseID;
    }

    public String getCourseName()
    {
        return courseName;
    }

    public void setCourseName(String courseName)
    {
        this.courseName = courseName;
    }

    public String getGrade()
    {
        return grade;
    }

    public void setGrade(String grade)
    {
        this.grade = grade;
    }

    public ArrayList<Breakdown> getBreakdownList()
    {
        return breakdownList;
    }

    public boolean equals(Object other)
    {
        boolean result;
        Course c;

        result = false;

        if (other instanceof Course)
        {
            c = (Course) other;
            if (((c.getCourseID() == null) && (courseID == null)) || (c.getCourseID().equals(courseID)))
            {
                result = true;
            }
        }
        return result;
    }

}
