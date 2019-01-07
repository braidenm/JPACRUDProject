# JPA CRUD Project

## Week 9 HomeWork (added another table and worked with different git branches)
## Week 8 Homework

### Technology Used

* Java
* j-unit tests
* Spring Boot
* JPA/JPQL
* HTML/CSS with Bootstrap
* TOMCAT Servers
* Eclipse IDE
* MYSQL Database
* MAMP MYSQL Workspace
* Gradle Project
* REG-EX
* JSP and Controllers
* Annotation Driven

### Week 9 Updates

With additional tables I used many to many mappings and added functionality to incorporate the new tables. There is a button when viewing plans, days, or exercises that will allow you to see what they are grouped with.

The most difficult part of this addition is editing the mapped entities. For example: an exercise is apart of a workout day. When editing the day that the exercise is a part of, the day needs to be updated and the exercise. This all needs to be represented in the database as well. Also, if unselecting all of the attributes a null entry needs to be handled as well as remove those attributed in the database. See any of the update methods in a controller.

Also, I lightly styled the pages.

Stretch goals:
* more connections between tables
* links for the dropdown list items to go to their respective items
* more tables to have a more fully functioning exercise app
* Login page to see users specific exercises
* short GIFs showing users how to do each exercise

### File Structure

The folder 'WorkOutProgram' contains the j-unit tests for the MYSQL Data Accessor classes. The 'BootWorkOutProgram' folder connects the 'WorkOutProgram' folder and includes the Web App logic. The 'DB' folder contains the Database files.

### How it Works

The Main page lets you search the database by id number, search word or words, or to display all of the exercises in the database. The browser will not let you input a non-number into the id field nor will it allow you to submit nothing.

The Search by keyword button will take any number of words and search the names and categories of all the exercises in the database using a JPQL/JPA query statement. If any are found it is added to a set (to not display duplicates) and displayed on the page.

When exercises are displayed the user has the option to edit the exercise or delete it.

When in the Update(edit) page, the current information is pre-loaded into the form and the User can edit whichever field they choose, except for the ID. The form is protected from users not entering proper data (ex: putting a letter where a number goes, too many characters, etc.) When the user presses submit they are "redirected" to the home page where the recently edited exercise is displayed.

When delete is selected the user will see the home page and a message stating which exercise was deleted.

Create is a tab on the nav bar for the user to make a new exercise in the database. When they fill out the required fields they are redirected back to the home page where their exercise is displayed showing a new id number as well.

## Highlights

* The controller class in general and getting the mapping correct with the re-directs
* Getting the search button to split the keywords by spaces and adding the '%' in the front and back of each word so to search the database properly in my query statement.
* J-unit tests that Works
* The Data Accessor Object and getting it to talk to the database

## stretch goals
* make a log-in with different views for logged in user vs not logged-in
* more databases
* more exercises in the database
* make a model for the delete button to ask the user if they are sure they want to delete the exercise
