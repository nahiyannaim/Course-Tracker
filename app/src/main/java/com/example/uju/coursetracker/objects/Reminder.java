package com.example.uju.coursetracker.objects;

/**
 * Created by Uju on 2018-03-02.
 */

public class Reminder {

    private String courseID; // e.g. COMP 1010
    private String reminderType; // e.g. Assignment/ Project / Term test
    private String dueDate; // MM/DD/YYYY
    public Reminder(String courseID, String reminderType, String dueDate)
    {
        this.courseID = courseID;
        this.reminderType = reminderType;
        this.dueDate = dueDate;
    }
    public String getCourseID()
    {
        return courseID;
    }
    public void setCourseID(String courseID)
    {
        this.courseID = courseID;
    }
    public String getReminderType()
    {
        return reminderType;
    }
    public void setReminderType(String reminderType)
    {
        this.reminderType = reminderType;
    }
    public String getDueDate()
    {
        return dueDate;
    }
    public void setDueDate(String dueDate)
    {
        this.dueDate = dueDate;
    }

}