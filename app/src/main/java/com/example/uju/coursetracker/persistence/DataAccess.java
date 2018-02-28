package com.example.uju.coursetracker.persistence;


public interface DataAccess
{
    void open(String string);

    void close();

    String getStudentSequential(List<Student> studentResult);

    ArrayList<Student> getStudentRandom(Student student);

    String insertStudent(Student student);

    String updateStudent(Student student);

    String deleteStudent(Student student);

    String getCourseSequential(List<Course> courseResult);

    ArrayList<Course> getCourseRandom(Course course);

    String insertCourse(Course course);

    String updateCourse(Course course);

    String deleteCourse(Course course);

    ArrayList<SC> getSC(SC sc);

    ArrayList<SC> getCS(SC sc);
}
