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

		course = new Course("COMP3010", "Distributed Computing");
		courses.add(course);
		course = new Course("COMP3020", "Human-Computer Interaction");
		courses.add(course);
		course = new Course("COMP3350", "Software Development");
		courses.add(course);
		course = new Course("COMP3380", "Databases");
		courses.add(course);


		System.out.println("Opened " +dbType +" database " +dbName);
	}
    // This is the stub database for the project
    // This stub database will have a set of initial contents and will provide the data for other classes.
}
