The log file can be found inside the project folder after it’s unzipped. 




The packages and major source code files:

The Course Tracker application contains the application, business, objects, persistence and presentation packages.

Application Package: Contains the Main Activity that has the code for the implementation of the home page and it’s the first thing that runs when the app starts. It is also responsible for the handling of options on the navigation bar The application package also contains Database Services which is responsible for starting, accessing and shutting down the stub database.

Business Package: Contains the AccessCourses (Interface for the database), CalculateCurrentCGPA (Implementation of the cgpa calculator algorithm), PredictNextCGPA (Implementation of the cgpa prediction algorithm). AccessCourses is basically a means to access the courses database and hide the implementation details of the database from the user. Similarly, AccessCourses handles creation, update and deletion of courses to the stub database.

Objects package: Contains the Course and Breakdown domain objects required for the project.

Persistence Package: Contains the StubDatabase which is the entire database of courses in the form of  ArrayLists.

Presentation Package: Contains the implementation of the functionalities for the GUI of the MyCompletedCourses page, CurrentCGPA Results page, MyCurrentCourses page, PredictNextGPA Results page, and MyCourses page. Additionally, this package contains MessagesActivity which is the implementation of how error and warning messages should work.

Res/Layout: Contains the Android XML files for the GUI's of the project

Tests: The objects all have their own unit tests which are called BreakdownTest and CourseTest. Similarly, the calculation parts of the project have their own unit tests called CalculateCurrentCGPATest and PredictNextCGPATest. Finally, the AllTests.java contains the test suite that runs all of the unit tests for all the objects and business packages.




Overview of the major implemented features and where to find them in the GUI:

The Homepage contains a menu which includes an option to navigate to a page where the user can view all of his courses via clicking on “My Courses”. The navigation bar also includes an option to navigate to the completed courses page where the in this page, the user can click a button to calculate the current CGPA. Similarly, the navigation bar also includes an option to navigate to the current courses page where the user can click a button to calculate their predicted CGPA.
By clicking the “My Courses” option in the navigation menu, the user can view their completed courses and their current semester courses in the same place.

By clicking on “My Current CGPA” in the navigation menu, the user would be able to view/modify their completed courses list and then calculate their current CGPA based on that list via “Calculate My Current CGPA” button. Additionally, the user has the option to quickly predict their next CGPA by clicking the “Predict My CGPA” button. 

Similarly, by clicking on “Predict Next CGPA” in the navigation menu, the user would be able to view/modify their current semester courses list. In this page, the user also has the ability to predict their next CGPA based on their current courses list by clicking the “Predict My CGPA” button.
