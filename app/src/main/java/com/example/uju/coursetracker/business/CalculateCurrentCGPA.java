package com.example.uju.coursetracker.business;

import com.example.uju.coursetracker.objects.Course;
import java.util.ArrayList;

public class CalculateCurrentCGPA
{
    //This is where we will calculate the current CGPA based on the already completed courses of the user
    public static double calculate(ArrayList<Course> completedCoursesList)
    {

        final String[] grades = {"A+","A","B+","B","C+","C","D","F"};
        final double[] points = {4.5,4.0,3.5,3.0,2.5,2.0,1.0,0.0};

        String temp = "";
        double totalPoints = 0.0;
        double currCGPA;
        boolean flag = false;
        boolean flag2 = false;

        if(completedCoursesList != null && completedCoursesList.size() > 0 && completedCoursesList.get(0) != null)
        {
            for (int i = 0; i < completedCoursesList.size(); i++)
            {
                temp = (completedCoursesList.get(i)).getGrade();
                flag2 = false;

                for(int j=0; j< grades.length; j++)
                {
                    if (temp.equalsIgnoreCase(grades[j]) )
                    {
                        totalPoints += points[j];
                        flag2 = true;
                    }
                }

                if(!flag2) //No valid grades were found
                {
                    flag = true;
                }
            }
        }
        else
        {
            flag = true;
        }

        if(!flag)
        {
            currCGPA = totalPoints / (completedCoursesList.size());
            currCGPA  = Math.round(currCGPA*100.0)/100.0;
        }
        else
        {
            currCGPA = -1.0;
        }

        return currCGPA;
    }
}
