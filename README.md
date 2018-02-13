COMP 3350 
COURSE TRACKER
The Course Tracker is an app that will be used as a guide for post-secondary students and course advisors to calculate both ensuing and preceding CGPAs from completed and uncompleted courses respectively. The application will be used to calculate the CGPA of students and also predict their next CGPA based on their assumed final grades for the current semester. It will help the student acknowledge the fact that they are falling short of their academic goals and help them allocate study resources appropriately. 

The Homepage contains the menu which includes the options to navigate to a page where the user can edit their courses, course breakdowns and course grades called MyCourses. It also includes an option to navigate to the user's current CGPA and their predicted CGPA. The user will also be able to calculate the user's current and predicted CGPA from the "My Courses" page. 

The app contains the application,business,persistence and presentation packages. it also contains two main objects: Course and Breakdown.

Application Package: Contains the Database Services and the Main Activity that contains the code for the implementation of the home page.

Business Package: Contains the AccessCourse, CalculateCurrentCGPA, PredictNextCGPA .java files. 

Persistence Package: Contains the StubDatabase in the form of an ArrayList.

Presentation Package: Contains the implementation for the GUI of the CurrentCGPA, Messages and MyCourses page

Res/Layout: Contains the XML files for the GUI's for the entire project

Tests: The objects all have their own unit tests and so do the calculation parts of the project, CalculateCurrentCGPA and PredictMyCGPA, contained in the android test package. Lastly, the AllTests.java file contains an overall test for all the objects and business packages
