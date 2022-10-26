---
layout: page
title: Rezwan Arefin's Project Portfolio Page
---

### Project: TA-Assist

TA Assist is a desktop application targeted at NUS Teaching Assistants (TA). It helps them to keep track of their students' grades, attendance, and work submission status of relevant modules.
The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java.
Given below are my contributions to the project.

* **New Feature**: 
    * Implemented grading related behavior, which includes: 
        * A `SessionData` class to hold the data regarding a session for a student.
        * A `StudentModuleData` to hold all the `SessionData` for a student in a module.  
        * Integration between the new `SessionData` and `StudentModuleData` classes with the TA-Assist architecture. Which includes: 
            * Adding a list of `StudentModuleData` to `Student` class.
            * Saving all the newly created classes in storage. 
        * The `grade` command to allow users to give grades to students for a session.
    * Implemented the `view` command to allow users to view session-wise grades of a student in a module.
    * Implemented feature to display current focused class (if any) in the GUI using bindings. 

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=RezwanArefin01&breakdown=true).

* **Project management**: To be added soon.
    * Authored 18 issues to help keep track of current issues and progress.
    * Reviewed 14 pull requests to ensure quality and consistency across the database.

* **Enhancements to existing features**: 
    * Fixed the help window not being loaded in Linux environment.
    * Extracted a generic `UniqueList<T>` class replacing previously existing `Unique*List` classes.
        * The generic type `T` implements the `Identity<T>` interface, which makes a way to compare two objects of type `T` with a defined identity, ignoring other data fields.    
        * This change was necessary to reduce code duplication and improve code quality.
    * Updated the `find` command to filter on the current displayed list instead of all students. 

* **Documentation**: 
    * Implementation details and sequence diagrams for the `grade` command.
    * Implementation details and sequence diagrams for the `view` command.

* **Community**: To be added soon.

* **Tools**: To be added soon.
