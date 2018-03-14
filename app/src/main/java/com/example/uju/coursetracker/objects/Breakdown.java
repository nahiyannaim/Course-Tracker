package com.example.uju.coursetracker.objects;

public class Breakdown
{
    private String courseID; // COMP 3350
    private String breakdownName; // Assignments (Ass) / Midterms (Mid) / Final (Fin) / Project (Pro) etc
    private double breakdownPercentage; //For e.g. 0.25 means(25% of the grade), 0.5 means (50% of the grade), 0.35 means (35% of the grade) etc

    public Breakdown(String courseID, String breakdownName, double breakdownPercentage)
    {
        this.courseID = courseID;
        this.breakdownName = breakdownName;
        this.breakdownPercentage = breakdownPercentage;
    }

    public String getCourseID()
    {
        return courseID;
    }

    public void setCourseID(String courseID)
    {
        this.courseID = courseID;
    }

    public String getBreakdownName()
    {
        return breakdownName;
    }

    public void setBreakdownName(String breakdownName)
    {
        this.breakdownName = breakdownName;
    }

    public double getBreakdownPercentage()
    {
        return breakdownPercentage;
    }

    public void setBreakdownPercentage(double breakdownPercentage)
    {
        this.breakdownPercentage = breakdownPercentage;
    }
}
