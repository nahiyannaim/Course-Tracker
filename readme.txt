The log file can be found inside the project folder after it’s unzipped. 

ReadMe:

The packages and major source code files:

The Course Tracker application contains the application, business, objects, persistence and presentation packages.

Application Package: The application package contains Database Services which is responsible for starting, accessing and shutting down the HSQL database.

Business Package: Contains the classes AccessCourses (Interface for the courses database), AccessReminders (interface for the reminders database), CalculateCurrentCGPA (Implementation of the cgpa calculator algorithm), and PredictNextCGPA (Implementation of the cgpa prediction algorithm). AccessCourses and AccessReminders are basically means to access the courses and reminders databases and hide the implementation details of the databases from the user. Similarly, they handle creation, update and deletion of courses and reminders to the SQL database.

Objects package: Contains the Course, Breakdown and Remnder domain objects required for the project.

Persistence Package: The persistence package now consists of the DataAccess interface. It contains all the methods used to access the database of the entire project. The DataAccessObject contains the implementation of those methods using HSQLDB.

Presentation Package: Contains the Main Activity that has the code for the implementation of the home page and it’s the first thing that runs when the app starts. It is also responsible for the handling of the menu items on the navigation bar. This package also contains the implementation of the functionalities for the GUI of the CreateNewReminder page, DueDates page, MyCompletedCourses page, CurrentCGPA Results page, MyCurrentCourses page, PredictNextGPA Results page, and MyCourses page. Additionally, this package contains MessagesActivity which is the implementation of how error and warning messages work in the app.

Res/Layout: Contains the required Android XML files for the GUI's of the project

Tests: Contains three test packages. The first package is the business test package containing unit tests for AccessCourses, AccessReminders, CalculateCurrentCGPA and predictNextCGPA. The second package is the objects test package containing unit tests for Breakdowns, Course and Reminders. The third package is the persistence test package containing the StubDatabase and its corresponding test file. The StubDatabase is the database of courses and reminders in the form of  ArrayLists. The StubDatabase is solely used for testing of the app. Finally, the AllTests.java contains the test suite that runs all of the unit tests for all the objects, business and persistence test packages.


Overview of the major implemented features and where to find them in the GUI:

The Homepage contains a navigation menu which includes an option to navigate to the MyCourses page, MyCurrentCGPA page, PredictNextCGPA page and My Reminders page. 
By clicking the “My Courses” option in the navigation menu, the user can view their completed courses and their current semester courses in the same place.
By clicking on “My Current CGPA” in the navigation menu, the user would be able to view/modify their completed courses list and then calculate their current CGPA based on that list via “Calculate My Current CGPA” button. Additionally, the user has the option to quickly predict their next CGPA by clicking the “Predict My CGPA” button. 

Similarly, by clicking on “Predict Next CGPA” in the navigation menu, the user would be able to view/modify their current semester courses list. In this page, the user also has the ability to predict their next CGPA based on their current courses list by clicking the “Predict My CGPA” button.

For Iteration 2:
The user will be able to view the due dates of their assignments, quizzes , midterms and final 
for their current courses by clicking the My Reminders button on the navigation menu. Using the Add Reminder and Delete buttons, the user is able to modify and create new reminders for any current course.
