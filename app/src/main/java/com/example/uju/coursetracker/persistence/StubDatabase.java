package com.example.uju.coursetracker.persistence;

import com.example.uju.coursetracker.objects.Breakdown;
import com.example.uju.coursetracker.objects.Course;

import java.util.ArrayList;

public class StubDatabase
{
    private String dbName;
    private String dbType = "stub";

    private ArrayList<Course> oldCourses;
    private ArrayList<Course> newCourses;

    public StubDatabase(String dbName)
    {
        this.dbName = dbName;
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
        course = new Course("COMP1010", "Introduction To Java 1","A");
        oldCourses.add(course);
        course = new Course("COMP1020", "Introduction To Java 2","B");
        oldCourses.add(course);
        course = new Course("COMP2140", "Data Structures","B");
        oldCourses.add(course);
        course = new Course("COMP2080", "Analysis Of Algorithms","C");
        oldCourses.add(course);

		course = new Course("COMP3010", "Distributed Computing","C");
        oldCourses.add(course);
		course = new Course("COMP3020", "Human-Computer Interaction","A");
        oldCourses.add(course);
		course = new Course("COMP3350", "Software Development","A+");
        oldCourses.add(course);
		course = new Course("COMP3380", "Introduction To Databases","A+");
        oldCourses.add(course);

        //Electives
        course = new Course("FMLY1000", "Introduction To Family Studies", "A+");
        oldCourses.add(course);
        course = new Course("MATH1500", "Calculus 1","A");
        oldCourses.add(course);
        course = new Course("MATH1700", "Calculus 2","A+");
        oldCourses.add(course);
        course = new Course("WOMN1500", "Women And Gender Studies","D");
        oldCourses.add(course);

        //New Courses------------------------------------------------------------------------------
        //Electives
        course = new Course("SOCO1200", "Introduction To Sociology");
        bkDwnLst = course.getBreakdownList();
        nwBk = new Breakdown("SOCO1200", "Ass",0.25);
        bkDwnLst.add(nwBk);
        nwBk = new Breakdown("SOCO1200", "Mid",0.35);
        bkDwnLst.add(nwBk);
        nwBk = new Breakdown("SOCO1200", "Fin",0.40);
        bkDwnLst.add(nwBk);
        newCourses.add(course);

        course = new Course("ECON1020", "Introduction To Economics");
        bkDwnLst = course.getBreakdownList();
        nwBk = new Breakdown("ECON1020", "Ass",0.80);
        bkDwnLst.add(nwBk);
        nwBk = new Breakdown("ECON1020", "Fin",0.20);
        bkDwnLst.add(nwBk);
        newCourses.add(course);

        course = new Course("ENGL1300", "Intro To English Literature");
        bkDwnLst = course.getBreakdownList();
        nwBk = new Breakdown("ENGL1300", "Ass",0.10);
        bkDwnLst.add(nwBk);
        nwBk = new Breakdown("ENGL1300", "Pro",0.20);
        bkDwnLst.add(nwBk);
        nwBk = new Breakdown("ENGL1300", "Mid",0.30);
        bkDwnLst.add(nwBk);
        nwBk = new Breakdown("ENGL1300", "Fin",0.40);
        bkDwnLst.add(nwBk);
        newCourses.add(course);

	}

    public String updateOldCourse(Course currentCourse)
    {
        int index;

        index = oldCourses.indexOf(currentCourse);
        if (index >= 0)
        {
            oldCourses.set(index, currentCourse);
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
    // This is the stub database for the project
    // This stub database will have a set of initial contents and will provide the data for other classes.
}
