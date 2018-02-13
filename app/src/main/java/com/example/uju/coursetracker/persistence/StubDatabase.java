package com.example.uju.coursetracker.persistence;

import android.util.Log;

import com.example.uju.coursetracker.application.MainActivity;
import com.example.uju.coursetracker.objects.Breakdown;
import com.example.uju.coursetracker.objects.Course;

import java.util.*;

public class StubDatabase
{
    private String dbName;
    private String dbType = "stub";

    private ArrayList<Course> oldCourses;
    private ArrayList<Course> newCourses;
    private ArrayList<Course> fullCourses;

    public StubDatabase(String dbName)
    {
        this.dbName = dbName;
    }

    public StubDatabase()
    {
        this(MainActivity.dbName);
    }

    public void open(String dbName)
	{

		Course course;
        Breakdown nwBk;
        ArrayList<Breakdown> bkDwnLst;
		oldCourses= new ArrayList<Course>();
        newCourses= new ArrayList<Course>();

        //Old Courses------------------------------------------------------------------------------
		//Comp Sci
        course = new Course("COMP 1010", "Introduction To Java 1","A");
        oldCourses.add(course);
        course = new Course("COMP 1020", "Introduction To Java 2","B+");
        oldCourses.add(course);
        course = new Course("COMP 2140", "Data Structures","B");
        oldCourses.add(course);
        course = new Course("COMP 2080", "Analysis Of Algorithms","C+");
        oldCourses.add(course);

		course = new Course("COMP 3010", "Distributed Computing","C");
        oldCourses.add(course);
		course = new Course("COMP 3020", "Human-Computer Interaction","A");
        oldCourses.add(course);
		course = new Course("COMP 3350", "Software Development","A+");
        oldCourses.add(course);
		course = new Course("COMP 3380", "Introduction To Databases","A+");
        oldCourses.add(course);

        //Electives
        course = new Course("FMLY 1000", "Introduction To Family Studies", "B+");
        oldCourses.add(course);
        course = new Course("MATH 1500", "Calculus 1","A");
        oldCourses.add(course);
        course = new Course("MATH 1700", "Calculus 2","A+");
        oldCourses.add(course);
        course = new Course("WOMN 1500", "Women And Gender Studies","D");
        oldCourses.add(course);

        //New Courses------------------------------------------------------------------------------
        //Electives
        course = new Course("SOCO 1200", "Introduction To Sociology", "A");
        bkDwnLst = course.getBreakdownList();
        nwBk = new Breakdown("SOCO 1200", "Assignment",0.25);
        bkDwnLst.add(nwBk);
        nwBk = new Breakdown("SOCO 1200", "Midterm",0.35);
        bkDwnLst.add(nwBk);
        nwBk = new Breakdown("SOCO 1200", "Final",0.40);
        bkDwnLst.add(nwBk);
        newCourses.add(course);

        course = new Course("ECON 1020", "Introduction To Economics", "A");
        bkDwnLst = course.getBreakdownList();
        nwBk = new Breakdown("ECON 1020", "Assignment",0.80);
        bkDwnLst.add(nwBk);
        nwBk = new Breakdown("ECON 1020", "Final",0.20);
        bkDwnLst.add(nwBk);
        newCourses.add(course);

        course = new Course("ENGL 1300", "Intro To English Literature", "A");
        bkDwnLst = course.getBreakdownList();
        nwBk = new Breakdown("ENGL 1300", "Assignment",0.10);
        bkDwnLst.add(nwBk);
        nwBk = new Breakdown("ENGL 1300", "Project",0.20);
        bkDwnLst.add(nwBk);
        nwBk = new Breakdown("ENGL 1300", "Midterm",0.30);
        bkDwnLst.add(nwBk);
        nwBk = new Breakdown("ENGL 1300", "Final",0.40);
        bkDwnLst.add(nwBk);
        newCourses.add(course);

        System.out.println("Opened " +dbType +" database " +dbName);
	}

    public void close()
    {
        System.out.println("Closed " +dbType +" database " +dbName);
    }

	public void createFullList()
    {
        int count= 0 ;
        int cnt2=0;
        for (int i =0 ; i<oldCourses.size()+newCourses.size() ; i++)
        {

            if(count<oldCourses.size())
            {
                fullCourses.add(oldCourses.get(i));
            }//if end
            else
            {
                fullCourses.add(newCourses.get(cnt2));
                cnt2++;
            }//else end
            count++;
        }//for end
    }

    public String updateOldCourse(Course currentCourse)
    {
        Log.v("myTag", currentCourse.getGrade());
        Log.v("myTag", currentCourse.getCourseID());
        int index;

        index = oldCourses.indexOf(currentCourse);
        Log.v("myTag", index + "");
        if (index >= 0)
        {
            oldCourses.set(index, currentCourse);
            Log.v("myTag", "1");
        }
        return null;
    }

    public String updateNewCourse(Course currentCourse)
    {
        int index;

        index = newCourses.indexOf(currentCourse);
        if (index >= 0)
        {
            newCourses.set(index, currentCourse);
        }
        return null;
    }

    public String deleteOldCourse(Course currentCourse)
    {
        int index;

        index = oldCourses.indexOf(currentCourse);
        if (index >= 0)
        {
            oldCourses.remove(index);
        }
        return null;
    }

    public String deleteNewCourse(Course currentCourse)
    {
        int index;

        index = newCourses.indexOf(currentCourse);
        if (index >= 0)
        {
            newCourses.remove(index);
        }
        return null;
    }


    public String insertOldCourse(Course currentCourse)
    {
        oldCourses.add(currentCourse);

        return null;
    }

    public String insertNewCourse(Course currentCourse)
    {
        newCourses.add(currentCourse);

        return null;
    }



    public String getCourseSequential(List<Course> courseResult,String key)
    {
        if(key.equals("old")) {
            courseResult.addAll(oldCourses);
        }
        else if (key.equals("new")) {
            courseResult.addAll(newCourses);
        }
        else{
            courseResult.addAll(fullCourses);
        }
        return null;
    }

    public ArrayList<Course> getOldCourses() {
        return oldCourses;
    }
    public ArrayList<Course> getNewCourses()
    {
        return newCourses;

    }
    // This is the stub database for the project
    // This stub database will have a set of initial contents and will provide the data for other classes.
}
