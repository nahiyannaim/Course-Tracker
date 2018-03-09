package com.example.uju.coursetracker.persistence;


import com.example.uju.coursetracker.presentation.MainActivity;
import com.example.uju.coursetracker.objects.Breakdown;
import com.example.uju.coursetracker.objects.Course;
import com.example.uju.coursetracker.objects.Reminder;

import java.util.*;


// This is the stub database for the project
// This stub database will have a set of initial contents and will provide the data for other classes.

public class StubDatabase
{
    private String dbName;
    private String dbType = "stub";

    private ArrayList<Course> oldCourses;
    private ArrayList<Course> newCourses;
    private ArrayList<Course> fullCourses;
    private ArrayList<Reminder> remList;

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
        remList = new ArrayList<Reminder>();
		oldCourses= new ArrayList<Course>();
        newCourses= new ArrayList<Course>();
        genCrs(oldCourses,newCourses);
        genRem();

        System.out.println("Opened " +dbType +" database " +dbName);
	}

    public void genRem()
    {
        Reminder currRem;

        //SOCO 1200 Reminders
        currRem = new Reminder("SOCO 1200", "Assignment", "01/02/2018");
        currRem = new Reminder("SOCO 1200", "Assignment", "02/09/2018");
        currRem = new Reminder("SOCO 1200", "Midterm", "02/20/2018");
        currRem = new Reminder("SOCO 1200", "Final", "04/02/2018");

        //ECON 1020 Reminders
        currRem = new Reminder("ECON 1020", "Assignment", "03/15/2018");
        currRem = new Reminder("ECON 1020", "Quiz", "01/17/2018");
        currRem = new Reminder("ECON 1020", "Midterm", "02/23/2018");
        currRem = new Reminder("ECON 1020", "Assignment", "01/09/2018");
        currRem = new Reminder("ECON 1020", "Quiz", "01/03/2018");
        currRem = new Reminder("ECON 1020", "Final", "04/12/2018");

        //ENGL 1300 Reminders
        currRem = new Reminder("ENGL 1300", "Final", "04/09/2018");
        currRem = new Reminder("ENGL 1300", "Assignment", "02/25/2018");
        currRem = new Reminder("ENGL 1300", "Midterm", "02/19/2018");



    }

	public void genCrs(ArrayList<Course> old ,ArrayList<Course> newC )
    {
        Course course;
        Breakdown nwBk;
        ArrayList<Breakdown> bkDwnLst;
        //Old Courses------------------------------------------------------------------------------
        //Comp Sci
        course = new Course("COMP 1010", "Introduction To Java 1","A");
        old.add(course);
        course = new Course("COMP 1020", "Introduction To Java 2","B+");
        old.add(course);
        course = new Course("COMP 2140", "Data Structures","B");
        old.add(course);
        course = new Course("COMP 2080", "Analysis Of Algorithms","C+");
        old.add(course);

        course = new Course("COMP 3010", "Distributed Computing","C");
        old.add(course);
        course = new Course("COMP 3020", "Human-Computer Interaction","A");
        old.add(course);
        course = new Course("COMP 3350", "Software Development","A+");
        old.add(course);
        course = new Course("COMP 3380", "Introduction To Databases","A+");
        old.add(course);

        //Electives
        course = new Course("FMLY 1000", "Introduction To Family Studies", "B+");
        old.add(course);
        course = new Course("MATH 1500", "Calculus 1","A");
        old.add(course);
        course = new Course("MATH 1700", "Calculus 2","A+");
        old.add(course);
        course = new Course("WOMN 1500", "Women And Gender Studies","D");
        old.add(course);

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
        newC.add(course);

        course = new Course("ECON 1020", "Introduction To Economics", "A");
        bkDwnLst = course.getBreakdownList();
        nwBk = new Breakdown("ECON 1020", "Assignment",0.80);
        bkDwnLst.add(nwBk);
        nwBk = new Breakdown("ECON 1020", "Final",0.20);
        bkDwnLst.add(nwBk);
        newC.add(course);

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
        newC.add(course);
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
        int index;

        index = oldCourses.indexOf(currentCourse);
        if (index >= 0)
        {
            oldCourses.set(index, currentCourse);
        }
        return null;
    }

    public String updateRem(Reminder rem)
    {
        int index;
        index = remList.indexOf(rem);
        if(index >= 0)
        {
            remList.set(index,rem);
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

    public String deleteRem(Reminder rem)
    {
        int index;

        index = remList.indexOf(rem);
        if (index >= 0)
        {
            remList.remove(index);
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

    public String insertRem(Reminder rem)
    {
        remList.add(rem);

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

    public ArrayList<Reminder> getRem()
    {
        return remList;
    }

}
