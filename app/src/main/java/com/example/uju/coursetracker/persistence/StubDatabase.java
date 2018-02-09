package com.example.uju.coursetracker.persistence;

import com.example.uju.coursetracker.objects.Course;

import java.util.ArrayList;

public class StubDatabase
{
    private String dbName;
    private String dbType = "stub";

    private ArrayList<Course> courses;

    public DataAccessStub(String dbName)
    {
        this.dbName = dbName;
    }

    public void open(String dbName)
	{

		Course course;

		courses = new ArrayList<Course>();

		//Comp Sci
		course = new Course("COMP3010", "Distributed Computing","C");
		courses.add(course);
		course = new Course("COMP3020", "Human-Computer Interaction","A");
		courses.add(course);
		course = new Course("COMP3350", "Software Development","A+");
		courses.add(course);
		course = new Course("COMP3380", "Introduction To Databases","A+");
		courses.add(course);

        //Electives
        course = new Course("FMLY1000", "Introduction To Family Studies", "A+");
        courses.add(course);
        course = new Course("MATH1500", "Calculus 1","A");
        courses.add(course);
        course = new Course("MATH1700", "Calculus 2","A+");
        courses.add(course);
        course = new Course("WOMN1500", "Women And Gender Studies","D");
        courses.add(course);


		System.out.println("Opened " +dbType +" database " +dbName);
	}
    // This is the stub database for the project
    // This stub database will have a set of initial contents and will provide the data for other classes.
}
