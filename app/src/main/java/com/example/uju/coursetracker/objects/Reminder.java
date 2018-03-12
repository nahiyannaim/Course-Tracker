package com.example.uju.coursetracker.objects;


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


    public boolean equals(Object other)
    {
        boolean result;
        Reminder r;

        result = false;

        if (other instanceof Reminder)
        {
            r = (Reminder) other;
            if (((r.getCourseID() == null) && (courseID == null)) || (r.getCourseID().equals(courseID)))
            {
                result = true;
            }
        }
        return result;
    }





}