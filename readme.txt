ReadMe:

***A copy of the DB script (the canonical DB) is in a .txt file inside the project folder after it’s unzipped, in case a restore is needed.

***The log file can be found inside the project folder after it’s unzipped. 

The packages and major source code files:

The Course Tracker application contains the application, business, objects, persistence and presentation packages.
Application Package: The application package contains Database Services which is responsible for starting, accessing and shutting down the HSQL database.

Business Package: Contains the classes AccessCourses (Interface for the courses database), AccessReminders (interface for the reminders database), CalculateCurrentCGPA (Implementation of the cgpa calculator algorithm), and PredictNextCGPA (Implementation of the cgpa prediction algorithm). AccessCourses and AccessReminders are basically means to access the courses and reminders databases and hide the implementation details of the databases from the user. Similarly, they handle creation, update and deletion of courses and reminders to the SQL database.

Objects package: Contains the Course, Breakdown and Remnder domain objects required for the project.
Persistence Package: The persistence package now consists of the DataAccess interface. It contains all the methods used to access the database of the entire project. The DataAccessObject contains the implementation of those methods using HSQLDB.

Presentation Package: Contains the Main Activity that has the code for the implementation of the home page and it’s the first thing that runs when the app starts. It is also responsible for the handling of the menu items on the navigation bar. This package also contains the implementation of the functionalities for the GUI of the CreateNewReminder page, DueDates page, MyCompletedCourses page, CurrentCGPA Results page, MyCurrentCourses page, PredictNextGPA Results page, MyCourses page and the PickMap page along with its dependent activities for each building map. Additionally, this package contains MessagesActivity which is the implementation of how error and warning messages work in the app.

Res/Layout: Contains the required Android XML files for the GUI's of the project

Tests: Contains single entry point (test suite) for each type of test: unit, integration, and acceptance tests, called RunUnitTests, RunIntegrationTests, and RunAcceptanceTests. Running one file executes all tests of that type. Unit tests run the tests for Objects, Business and Persistence packages. Integration tests run the tests for Business-Persistence Seam test and DataAccessHSQLDB. Acceptance tests run the tests for all the stories of the project.


Overview of the major implemented features and where to find them in the GUI:

The Homepage contains a navigation menu which includes an option to navigate to the MyCourses page, MyCurrentCGPA page, PredictNextCGPA page and My Reminders page. 
By clicking the “My Courses” option in the navigation menu, the user can view their completed courses and their current semester courses in the same place.
By clicking on “My Current CGPA” in the navigation menu, the user would be able to view/modify their completed courses list and then calculate their current CGPA based on that list via “Calculate My Current CGPA” button. Additionally, the user has the option to quickly predict their next CGPA by clicking the “Predict My CGPA” button. 

Similarly, by clicking on “Predict Next CGPA” in the navigation menu, the user would be able to view/modify their current semester courses list. In this page, the user also has the ability to predict their next CGPA based on their current courses list by clicking the “Predict My CGPA” button.

By clicking on “My Reminders” in the navigation menu, the user will be able to view the due dates of their assignments, quizzes , midterms and final for their current courses. Using the Add Reminder and Delete buttons, the user is able to delete reminders and create new ones for any current course with its following due date and reminder type (E.g. Assignments, Quiz, Midterm and Project).

For Iteration 3:
By clicking the “EITC Maps” option in the navigation menu, the user will be able to view the map of a campus building to know where each room is located on every floor. Additionally, he can select a specific part of the building (E.g. EITC-1 and EITC-2) to view its following map.
