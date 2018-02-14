package com.example.uju.coursetracker.business;

import com.example.uju.coursetracker.objects.Course;
import java.util.ArrayList;

public class PredictNextCGPA
{
    //This is where we will predict the next CGPA of the user based on currentCGPA AND grades in current semester courses
    // totalCoursesCompleted is the number of courses completed already EXCLUDING current semester

    public static double calculate(ArrayList<Course> currentCoursesList, double currentCGPA, int totalCoursesCompleted)
    {
        String temp = "";
        double points = 0.0;
        double prediction;
        boolean flag = false;

        if(currentCGPA >= 0 && currentCGPA <= 4.5 && totalCoursesCompleted >= 0)
        {
            points = currentCGPA * totalCoursesCompleted;

            if (currentCoursesList != null && currentCoursesList.size() > 0 && currentCoursesList.get(0) != null)
            {
                for (int i = 0; i < currentCoursesList.size(); i++)
                {
                    temp = (currentCoursesList.get(i)).getGrade();

                    if (temp.equals("A+"))
                    {
                        points += 4.5;
                    }
                    else if (temp.equals("A"))
                    {
                        points += 4.0;
                    }
                    else if (temp.equals("B+"))
                    {
                        points += 3.5;
                    }
                    else if (temp.equals("B"))
                    {
                        points += 3.0;
                    }
                    else if (temp.equals("C+"))
                    {
                        points += 2.5;
                    }
                    else if (temp.equals("C"))
                    {
                        points += 2.0;
                    }
                    else if (temp.equals("D"))
                    {
                        points += 1.0;
                    }
                    else if (temp.equals("F"))
                    {
                        points += 0.0;
                    }
                    else
                    {
                        flag = true;
                    }

                }//for
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
            prediction = points / ( currentCoursesList.size() + totalCoursesCompleted);
            prediction  = Math.round(prediction*100.0)/100.0;
        }
        else
        {
            prediction = -1.0;
        }

        return prediction;

    }

}
