package com.example.uju.coursetracker.objects;

import java.util.ArrayList;

public class Course
{
    private String courseID; // "COMP 1010"
    private String courseName; // "Introduction to Computer Programming"
    private ArrayList<Breakdown> breakdownList; // List: [br1, br2, br3]  For e.g. Assignment br1, Midterm br2, Final br3
    private String grade;
    private final String NO_GRADE = "-1";

    public Course(String id, String name, String grade)
    {
        this.courseID = id;
        this.courseName = name;
        this.breakdownList = new ArrayList<>();
        this.grade = grade;
    }

    //Constructor for new courses with no assigned grade
    public Course(String id, String name)
    {
        this.courseID = id;
        this.courseName = name;
        this.breakdownList = new ArrayList<>();
        this.grade = NO_GRADE; // -1 = NO_GRADE
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

    public String getGrade() { return grade; }

    public void setGrade(String grade) { this.grade = grade; }

    public ArrayList<Breakdown> getBreakdownList()
    {
        return breakdownList;
    }

    public void printBreakdownList()
    {
        String txt = "";
        for(int i = 0 ; i< breakdownList.size() ; i++){
            txt+=breakdownList.get(i)+ " ";
        }
        System.out.print("Breakdown List: " + txt);
    }

    public boolean equals(Course other)
    {
        return (this.courseID.equals(other.getCourseID())) && (this.courseName.equals(other.getCourseName()));
    }
}
