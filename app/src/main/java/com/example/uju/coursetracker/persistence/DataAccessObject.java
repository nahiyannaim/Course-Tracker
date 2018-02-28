/**
 * This code is not used in the first iteration. It is provided as
 * an example of usage of HSQLDB (for iteration 2).
 */


package com.example.uju.coursetracker.persistence;


public class DataAccessObject implements DataAccess
{
    private Statement st1, st2, st3;
    private Connection c1;
    private ResultSet rs2, rs3, rs4, rs5;

    private String dbName;
    private String dbType;

    private ArrayList<Student> students;
    private ArrayList<Course> courses;
    private ArrayList<SC> scs;

    private String cmdString;
    private int updateCount;
    private String result;
    private static String EOF = "  ";

    public DataAccessObject(String dbName)
    {
        this.dbName = dbName;
    }

    public void open(String dbPath)
    {
        String url;
        try
        {
            // Setup for HSQL
            dbType = "HSQL";
            Class.forName("org.hsqldb.jdbcDriver").newInstance();
            url = "jdbc:hsqldb:file:" + dbPath; // stored on disk mode
            c1 = DriverManager.getConnection(url, "SA", "");
            st1 = c1.createStatement();
            st2 = c1.createStatement();
            st3 = c1.createStatement();

            /*** Alternate setups for different DB engines, just given as examples. Don't use them. ***/

			/*
			 * // Setup for SQLite. Note that this is undocumented and is not guaranteed to work.
			 * // See also: https://github.com/SQLDroid/SQLDroid
			 * dbType = "SQLite";
			 * Class.forName("SQLite.JDBCDriver").newInstance();
			 * url = "jdbc:sqlite:" + dbPath;
			 * c1 = DriverManager.getConnection(url);
			 *
			 * ... create statements
			 */

            /*** The following two work on desktop builds: ***/

			/*
			 * // Setup for Access
			 * dbType = "Access";
			 * Class.forName("sun.jdbc.odbc.JdbcOdbcDriver").newInstance();
			 * url = "jdbc:odbc:SC";
			 * c1 = DriverManager.getConnection(url,"userid","userpassword");
			 *
			 * ... create statements
			 */

			/*
			 * //Setup for MySQL
			 * dbType = "MySQL";
			 * Class.forName("com.mysql.jdbc.Driver");
			 * url = "jdbc:mysql://localhost/database01";
			 * c1 = DriverManager.getConnection(url, "root", "");
			 *
			 * ... create statements
			 */
        }
        catch (Exception e)
        {
            processSQLError(e);
        }
        System.out.println("Opened " +dbType +" database " +dbPath);
    }

    public void close()
    {
        try
        {	// commit all changes to the database
            cmdString = "shutdown compact";
            rs2 = st1.executeQuery(cmdString);
            c1.close();
        }
        catch (Exception e)
        {
            processSQLError(e);
        }
        System.out.println("Closed " +dbType +" database " +dbName);
    }

    public String getStudentSequential(List<Student> studentResult)
    {
        Student student;
        String myID, myStudentName, myAddress;
        myStudentName = EOF;
        myAddress = EOF;
        myID = EOF;

        result = null;
        try
        {
            cmdString = "Select * from Students";
            rs2 = st1.executeQuery(cmdString);
            //ResultSetMetaData md2 = rs2.getMetaData();
        }
        catch (Exception e)
        {
            processSQLError(e);
        }
        try
        {
            while (rs2.next())
            {
                myID = rs2.getString("StudentID");
                myStudentName = rs2.getString("Name");
                myAddress = rs2.getString("Address");
                student = new Student(myID, myStudentName, myAddress);
                studentResult.add(student);
            }
            rs2.close();
        }
        catch (Exception e)
        {
            result = processSQLError(e);
        }

        return result;
    }

    public ArrayList<Student> getStudentRandom(Student newStudent)
    {
        Student student;
        String myID, myStudentName, myAddress;
        myID = EOF;
        myStudentName = EOF;
        myAddress = EOF;
        students = new ArrayList<Student>();
        try
        {
            cmdString = "Select * from Students where StudentID=" + newStudent.getStudentID();
            rs3 = st1.executeQuery(cmdString);
            // ResultSetMetaData md2 = rs3.getMetaData();
            while (rs3.next())
            {
                myID = rs3.getString("StudentID");
                myStudentName = rs3.getString("Name");
                myAddress = rs3.getString("Address");
                student = new Student(myID, myStudentName, myAddress);
                students.add(student);
            }
            rs3.close();
        } catch (Exception e)
        {
            processSQLError(e);
        }
        return students;
    }

    public String insertStudent(Student currentStudent)
    {
        String values;

        result = null;
        try
        {
            values = currentStudent.getStudentID()
                    +", '" +currentStudent.getStudentName()
                    +"', '" +currentStudent.getStudentAddress()
                    +"'";
            cmdString = "Insert into Students " +" Values(" +values +")";
            //System.out.println(cmdString);
            updateCount = st1.executeUpdate(cmdString);
            result = checkWarning(st1, updateCount);
        }
        catch (Exception e)
        {
            result = processSQLError(e);
        }
        return result;
    }

    public String updateStudent(Student currentStudent)
    {
        String values;
        String where;

        result = null;
        try
        {
            // Should check for empty values and not update them
            values = "Name='" +currentStudent.getStudentName()
                    +"', Address='" +currentStudent.getStudentAddress()
                    +"'";
            where = "where StudentID=" +currentStudent.getStudentID();
            cmdString = "Update Students " +" Set " +values +" " +where;
            //System.out.println(cmdString);
            updateCount = st1.executeUpdate(cmdString);
            result = checkWarning(st1, updateCount);
        }
        catch (Exception e)
        {
            result = processSQLError(e);
        }
        return result;
    }

    public String deleteStudent(Student currentStudent)
    {
        String values;

        result = null;
        try
        {
            values = currentStudent.getStudentID();
            cmdString = "Delete from Students where StudentID=" +values;
            //System.out.println(cmdString);
            updateCount = st1.executeUpdate(cmdString);
            result = checkWarning(st1, updateCount);
        }
        catch (Exception e)
        {
            result = processSQLError(e);
        }
        return result;
    }

    public String getCourseSequential(List<Course> courseResult)
    {
        Course course;
        String myID, myCourseName;
        myID = EOF;
        myCourseName = EOF;

        result = null;
        try
        {
            cmdString = "Select * from Courses";
            rs5 = st3.executeQuery(cmdString);
            // ResultSetMetaData md5 = rs5.getMetaData();
            while (rs5.next())
            {
                myID = rs5.getString("CourseID");
                myCourseName = rs5.getString("Name");
                course = new Course(myID, myCourseName);
                courseResult.add(course);
            }
            rs5.close();
        }
        catch (Exception e)
        {
            result = processSQLError(e);
        }
        return result;
    }

    public ArrayList<Course> getCourseRandom(Course newCourse)
    {
        Course course;
        String myID, myCourseName;
        myID = EOF;
        myCourseName = EOF;
        courses = new ArrayList<Course>();
        try
        {
            cmdString = "Select * from Courses where CourseID='" +newCourse.getCourseID() +"'";
            rs5 = st3.executeQuery(cmdString);
            // ResultSetMetaData md5 = rs5.getMetaData();
            while (rs5.next())
            {
                myID = rs5.getString("CourseID");
                myCourseName = rs5.getString("Name");
                course = new Course(myID, myCourseName);
                courses.add(course);
            }
            rs5.close();
        }
        catch (Exception e)
        {
            processSQLError(e);
        }
        return courses;
    }

    public String insertCourse(Course currentCourse)
    {
        String values;

        result = null;
        try
        {
            values =  "'" +currentCourse.getCourseID()
                    +"', '" +currentCourse.getCourseName()
                    +"'";
            cmdString = "Insert into Courses " +" Values(" +values +")";
            //System.out.println(cmdString);
            updateCount = st1.executeUpdate(cmdString);
            result = checkWarning(st1, updateCount);
        }
        catch (Exception e)
        {
            result = processSQLError(e);
        }
        return result;
    }

    public String updateCourse(Course currentCourse)
    {
        String values;
        String where;

        result = null;
        try
        {
            // Should check for empty values and not update them
            values = "Name='" +currentCourse.getCourseName()
                    +"'";
            where = "where CourseID='" +currentCourse.getCourseID() +"'";
            cmdString = "Update Courses " +" Set " +values +" " +where;
            //System.out.println(cmdString);
            updateCount = st1.executeUpdate(cmdString);
            result = checkWarning(st1, updateCount);
        }
        catch (Exception e)
        {
            result = processSQLError(e);
        }
        return result;
    }

    public String deleteCourse(Course currentCourse)
    {
        String values;

        result = null;
        try
        {
            values = currentCourse.getCourseID();
            cmdString = "Delete from Courses where CourseID='" +values +"'";
            //System.out.println(cmdString);
            updateCount = st1.executeUpdate(cmdString);
            result = checkWarning(st1, updateCount);
        }
        catch (Exception e)
        {
            result = processSQLError(e);
        }
        return result;
    }

    public ArrayList<SC> getSC(SC newSC)
    {
        String myStudentID, myCourseID, myCourseName, myGrade;
        SC mySC;
        int counter;

        myStudentID = EOF;
        myCourseID = EOF;
        myGrade = EOF;
        myCourseName = EOF;
        counter = 0;
        scs = new ArrayList<SC>();
        try
        {
            cmdString = "Select * from Courses,StudentsCourses where Courses.CourseID=StudentsCourses.CourseID and StudentID=" + newSC.getStudentID();
            rs4 = st2.executeQuery(cmdString);
            // ResultSetMetaData md4 = rs4.getMetaData();
            while (rs4.next())
            {
                myStudentID = rs4.getString("StudentID");
                myCourseID = rs4.getString("CourseID");
                myGrade = rs4.getString("Grade");
                myCourseName = rs4.getString("Name");
                mySC = new SC(myStudentID, myCourseID, "", myCourseName, myGrade);
                scs.add(mySC);
                counter++;
            }
            rs4.close();
        }
        catch (Exception e)
        {
            processSQLError(e);
        }
        return scs;
    }

    public ArrayList<SC> getCS(SC newSC)
    {
        String myStudentID, myCourseID, myStudentName, myGrade;
        SC myCS;
        int counter;

        myStudentID = EOF;
        myCourseID = EOF;
        myGrade = EOF;
        myStudentName = EOF;
        counter = 0;
        scs = new ArrayList<SC>();
        try
        {
            cmdString = "Select * from Students,StudentsCourses where Students.StudentID=StudentsCourses.StudentID and CourseID='" +newSC.getCourseID() +"'";
            rs4 = st2.executeQuery(cmdString);
            // ResultSetMetaData md4 = rs4.getMetaData();
            while (rs4.next())
            {
                myStudentID = rs4.getString("StudentID");
                myCourseID = rs4.getString("CourseID");
                myGrade = rs4.getString("Grade");
                myStudentName = rs4.getString("Name");
                myCS = new SC(myStudentID, myCourseID, myStudentName, "", myGrade);
                scs.add(myCS);
                counter++;
            }
            rs4.close();
        }
        catch (Exception e)
        {
            processSQLError(e);
        }
        return scs;
    }

    public String checkWarning(Statement st, int updateCount)
    {
        String result;

        result = null;
        try
        {
            SQLWarning warning = st.getWarnings();
            if (warning != null)
            {
                result = warning.getMessage();
            }
        }
        catch (Exception e)
        {
            result = processSQLError(e);
        }
        if (updateCount != 1)
        {
            result = "Tuple not inserted correctly.";
        }
        return result;
    }

    public String processSQLError(Exception e)
    {
        String result = "*** SQL Error: " + e.getMessage();

        // Remember, this will NOT be seen by the user!
        e.printStackTrace();

        return result;
    }
}
