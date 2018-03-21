package com.example.uju.coursetracker.business;

import com.example.uju.coursetracker.objects.Course;
import java.util.ArrayList;

public class PredictNextCGPA
{
    //This is where we will predict the next CGPA of the user based on currentCGPA AND grades in current semester courses
    // totalCoursesCompleted is the number of courses completed already EXCLUDING current semester

    public static double calculate(ArrayList<Course> currentCoursesList, double currentCGPA, int totalCoursesCompleted)
    {
        final double maxGPA = 4.5;
        String temp = "";
        double prevPoints = 0.0;
        double prediction;
        boolean flag = false;
        CalculateCurrentCGPA curr = new CalculateCurrentCGPA();

        if(currentCGPA >= 0 && currentCGPA <= maxGPA && totalCoursesCompleted >= 0)
        {
            prevPoints  = currentCGPA * totalCoursesCompleted;

            if (currentCoursesList != null && currentCoursesList.size() > 0 && currentCoursesList.get(0) != null)
            {
                if(curr.calculate(currentCoursesList) != -1.0)
                {
                    prevPoints += curr.calculate(currentCoursesList) * currentCoursesList.size();
                }
                else
                {
                    flag = true;
                }
            }
            else
            {
                flag = true;
            }
        }
        else
        {
            flag = true;
        }

        if(!flag)
        {
            prediction = prevPoints  / ( currentCoursesList.size() + totalCoursesCompleted);
            prediction  = Math.round(prediction*100.0)/100.0; //Rounding to 2 decimal places
        }
        else
        {
            prediction = -1.0;
        }

        return prediction;
    }
}
