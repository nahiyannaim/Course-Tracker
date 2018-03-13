package com.example.uju.coursetracker.tests.business;

import com.example.uju.coursetracker.application.DatabaseService;
import com.example.uju.coursetracker.business.AccessCourses;
import com.example.uju.coursetracker.objects.Course;
import com.example.uju.coursetracker.presentation.MainActivity;
import com.example.uju.coursetracker.tests.persistence.StubDatabase;
import junit.framework.TestCase;
import java.util.ArrayList;

public class AccessCoursesTest extends TestCase
{
    private static String dbName = MainActivity.dbName;

    public AccessCoursesTest(String arg0)
    {
        super(arg0);
    }

    public void testCompletedCourses()
    {
        System.out.println("\nStarting test AccessCourses for: Completed Courses");

        DatabaseService.closeDataAccess();
        DatabaseService.createDataAccess(new StubDatabase(dbName));

        Course course;
        AccessCourses ac = new AccessCourses();
        String result ="";


        // Accessing the 1st course
        course = ac.getSequentialCompleted();

        assertNotNull(ac);
        assertNotNull(course);
        assertEquals("COMP 1010", course.getCourseID());
        assertEquals("Introduction To Java 1", course.getCourseName());
        assertEquals("A", course.getGrade());



        // Accessing the 3rd course
        course = ac.getSequentialCompleted();
        course = ac.getSequentialCompleted();

        assertNotNull(ac);
        assertNotNull(course);
        assertEquals("COMP 2140", course.getCourseID());
        assertEquals("Data Structures", course.getCourseName());
        assertEquals("B", course.getGrade());



        // Accessing the 6th course
        course = ac.getSequentialCompleted();
        course = ac.getSequentialCompleted();
        course = ac.getSequentialCompleted();

        assertNotNull(ac);
        assertNotNull(course);
        assertEquals("COMP 3020", course.getCourseID());
        assertEquals("Human-Computer Interaction", course.getCourseName());
        assertEquals("A", course.getGrade());



        // Finished traversing completed courses list in database after 12 items
        course = ac.getSequentialCompleted();
        course = ac.getSequentialCompleted();
        course = ac.getSequentialCompleted();
        course = ac.getSequentialCompleted();
        course = ac.getSequentialCompleted();
        course = ac.getSequentialCompleted();
        course = ac.getSequentialCompleted();

        assertNull(course);
        assertNotNull(ac);



        ArrayList<Course> list = new ArrayList<Course>();
        course = new Course("BIOL 5000","Advanced Bio","B");
        Course course2 = new Course("MUSIC 2000","Advanced Music","C");
        Course course3 = new Course("LAW 7000","Advanced Law","D");


        assertNotNull(ac);
        assertNotNull(course);
        assertNotNull(course2);
        assertNotNull(course3);
        assertNotNull(list);

        ac.getCompletedCoursesSeq(list);
        assertEquals(12, list.size());

        ac.insertCompletedCourse(course);
        ac.insertCompletedCourse(course2);
        ac.insertCompletedCourse(course3);

        ac.getCompletedCoursesSeq(list);
        assertEquals(15, list.size());
        assertEquals("MUSIC 2000", (list.get(list.indexOf(course2))).getCourseID());
        assertEquals("Advanced Music", (list.get(list.indexOf(course2))).getCourseName());
        assertEquals("C", (list.get(list.indexOf(course2))).getGrade());


        //Find by courseID and then update grade B to F
        ac.updateCompletedCourse(new Course("BIOL 5000","Advanced Bio","F"));

        ac.getCompletedCoursesSeq(list);
        assertEquals(15, list.size());
        assertEquals("BIOL 5000", (list.get(list.indexOf(course))).getCourseID());
        assertEquals("Advanced Bio", (list.get(list.indexOf(course))).getCourseName());
        assertEquals("F", (list.get(list.indexOf(course))).getGrade());



        ac.deleteCompletedCourse(course);
        ac.deleteCompletedCourse(course2);

        ac.getCompletedCoursesSeq(list);
        assertEquals(13, list.size());
        assertEquals(-1, (list.indexOf(course)));
        assertEquals(-1, (list.indexOf(course2)));
        assertEquals("LAW 7000", (list.get(list.indexOf(course3))).getCourseID());



        ac.deleteCompletedCourse(course3);
        ac.insertCompletedCourse(course);
        ac.insertCompletedCourse(course2);
        ac.insertCompletedCourse(course3);
        ac.getCompletedCoursesSeq(list);
        assertEquals(15, list.size());



        ac.insertCompletedCourse(null);
        ac.updateCompletedCourse(null);
        ac.deleteCompletedCourse(null);
        ac.getCompletedCoursesSeq(list);
        assertEquals(15, list.size());



        DatabaseService.closeDataAccess();
        System.out.println("Finished test");
    }

    public void testCurrentCourses()
    {
        System.out.println("\nStarting test AccessCourses for: Current Courses");

        DatabaseService.closeDataAccess();
        DatabaseService.createDataAccess(new StubDatabase(dbName));

        Course course;
        AccessCourses ac;
        String result ="";
        ac = new AccessCourses();



        // Accessing the 1st course
        course = ac.getSequentialCurrent();

        assertNotNull(ac);
        assertNotNull(course);
        assertEquals("SOCO 1200", course.getCourseID());
        assertEquals("Introduction To Sociology", course.getCourseName());
        assertEquals("A", course.getGrade());



        // Accessing the last course
        course = ac.getSequentialCurrent();
        course = ac.getSequentialCurrent();

        assertNotNull(ac);
        assertNotNull(course);
        assertEquals("ENGL 1300", course.getCourseID());
        assertEquals("Intro To English Literature", course.getCourseName());
        assertEquals("A", course.getGrade());



        // Finished traversing current courses list in database after 3 items
        course = ac.getSequentialCurrent();

        assertNull(course);
        assertNotNull(ac);



        ArrayList<Course> list = new ArrayList<Course>();
        course = new Course("BIOL 5000","Advanced Bio","B");
        Course course2 = new Course("MUSIC 2000","Advanced Music","C");
        Course course3 = new Course("LAW 7000","Advanced Law","D");


        assertNotNull(ac);
        assertNotNull(course);
        assertNotNull(course2);
        assertNotNull(course3);
        assertNotNull(list);

        ac.getCurrentCoursesSeq(list);
        assertEquals(3, list.size());

        ac.insertCurrentCourse(course);
        ac.insertCurrentCourse(course2);
        ac.insertCurrentCourse(course3);

        ac.getCurrentCoursesSeq(list);
        assertEquals(6, list.size());
        assertEquals("MUSIC 2000", (list.get(list.indexOf(course2))).getCourseID());
        assertEquals("Advanced Music", (list.get(list.indexOf(course2))).getCourseName());
        assertEquals("C", (list.get(list.indexOf(course2))).getGrade());


        //Find by courseID and then update grade B to F
        ac.updateCurrentCourse(new Course("BIOL 5000","Advanced Bio","F"));

        ac.getCurrentCoursesSeq(list);
        assertEquals(6, list.size());
        assertEquals("BIOL 5000", (list.get(list.indexOf(course))).getCourseID());
        assertEquals("Advanced Bio", (list.get(list.indexOf(course))).getCourseName());
        assertEquals("F", (list.get(list.indexOf(course))).getGrade());



        ac.deleteCurrentCourse(course);
        ac.deleteCurrentCourse(course2);

        ac.getCurrentCoursesSeq(list);
        assertEquals(4, list.size());
        assertEquals(-1, (list.indexOf(course)));
        assertEquals(-1, (list.indexOf(course2)));
        assertEquals("LAW 7000", (list.get(list.indexOf(course3))).getCourseID());



        ac.deleteCurrentCourse(course3);
        ac.insertCurrentCourse(course);
        ac.insertCurrentCourse(course2);
        ac.insertCurrentCourse(course3);
        ac.getCurrentCoursesSeq(list);
        assertEquals(6, list.size());



        ac.insertCurrentCourse(null);
        ac.updateCurrentCourse(null);
        ac.deleteCurrentCourse(null);
        ac.getCurrentCoursesSeq(list);
        assertEquals(6, list.size());



        DatabaseService.closeDataAccess();
        System.out.println("Finished test");
    }
}
