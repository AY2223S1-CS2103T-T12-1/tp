---
layout: page
title: Developer Guide
---
* Table of Contents
{:toc}
--------------------------------------------------------------------------------------------------------------------

## **Acknowledgements**

* {list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

--------------------------------------------------------------------------------------------------------------------

## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------

## **Design**

<div markdown="span" class="alert alert-primary">

:bulb: **Tip:** The `.puml` files used to create diagrams in this document can be found in the [diagrams](https://github.com/AY2223S1-CS2103T-T12-1/tp/tree/master/docs/diagrams/) folder. Refer to the [_PlantUML Tutorial_ at se-edu/guides](https://se-education.org/guides/tutorials/plantUml.html) to learn how to create and edit diagrams.
</div>

### Architecture

<img src="images/ArchitectureDiagram.png" width="280" />

The ***Architecture Diagram*** given above explains the high-level design of the App.

Given below is a quick overview of main components and how they interact with each other.

**Main components of the architecture**

**`Main`** has two classes called [`Main`](https://github.com/AY2223S1-CS2103T-T12-1/tp/blob/master/src/main/java/seedu/taassist/Main.java) and [`MainApp`](https://github.com/AY2223S1-CS2103T-T12-1/tp/blob/master/src/main/java/seedu/taassist/MainApp.java). It is responsible for,
* At app launch: Initializes the components in the correct sequence, and connects them up with each other.
* At shut down: Shuts down the components and invokes cleanup methods where necessary.

[**`Commons`**](#common-classes) represents a collection of classes used by multiple other components.

The rest of the App consists of four components.

* [**`UI`**](#ui-component): The UI of the App.
* [**`Logic`**](#logic-component): The command executor.
* [**`Model`**](#model-component): Holds the data of the App in memory.
* [**`Storage`**](#storage-component): Reads data from, and writes data to, the hard disk.


**How the architecture components interact with each other**

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user issues the command `delete 1`.

<img src="images/ArchitectureSequenceDiagram.png" width="574" />

Each of the four main components (also shown in the diagram above),

* defines its *API* in an `interface` with the same name as the Component.
* implements its functionality using a concrete `{Component Name}Manager` class (which follows the corresponding API `interface` mentioned in the previous point.

For example, the `Logic` component defines its API in the `Logic.java` interface and implements its functionality using the `LogicManager.java` class which follows the `Logic` interface. Other components interact with a given component through its interface rather than the concrete class (reason: to prevent outside component's being coupled to the implementation of a component), as illustrated in the (partial) class diagram below.

<img src="images/ComponentManagers.png" width="300" />

The sections below give more details of each component.

### UI component

The **API** of this component is specified in [`Ui.java`](https://github.com/AY2223S1-CS2103T-T12-1/tp/blob/master/src/main/java/seedu/taassist/ui/Ui.java)

![Structure of the UI Component](images/UiClassDiagram.png)

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `StudentListPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class which captures the commonalities between classes that represent parts of the visible GUI.

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/AY2223S1-CS2103T-T12-1/tp/blob/master/src/main/java/seedu/taassist/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/AY2223S1-CS2103T-T12-1/tp/blob/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* executes user commands using the `Logic` component.
* listens for changes to `Model` data so that the UI can be updated with the modified data.
* keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
* depends on some classes in the `Model` component, as it displays `Student` object residing in the `Model`.

### Logic component

**API** : [`Logic.java`](https://github.com/AY2223S1-CS2103T-T12-1/tp/blob/master/src/main/java/seedu/taassist/logic/Logic.java)

Here's a (partial) class diagram of the `Logic` component:

<img src="images/LogicClassDiagram.png" width="550"/>

How the `Logic` component works:
1. When `Logic` is called upon to execute a command, it uses the `TaAssistParser` class to parse the user command.
2. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `AddCommand`) which is executed by the `LogicManager`.
3. The command can communicate with the `Model` when it is executed (e.g. to add a student).
4. The result of the command execution is encapsulated as a `CommandResult` object which is returned back from `Logic`.

The Sequence Diagram below illustrates the interactions within the `Logic` component for the `execute("delete 1")` API call.

![Interactions Inside the Logic Component for the `delete 1` Command](images/DeleteSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `DeleteCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</div>

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

<img src="images/ParserClasses.png" width="600"/>

How the parsing works:
* When called upon to parse a user command, the `TaAssistParser` class creates an `XYZCommandParser` (`XYZ` is a placeholder for the specific command name e.g., `AddCommandParser`) which uses the other classes shown above to parse the user command and create a `XYZCommand` object (e.g., `AddCommand`) which the `TaAssistParser` returns back as a `Command` object.
* All `XYZCommandParser` classes (e.g., `AddCommandParser`, `DeleteCommandParser`, ...) inherit from the `Parser` interface so that they can be treated similarly where possible e.g, during testing.

### Model component
**API** : [`Model.java`](https://github.com/AY2223S1-CS2103T-T12-1/tp/blob/master/src/main/java/seedu/taassist/model/Model.java)

<img src="images/ModelClassDiagram.png" width="450" />


The `Model` component,

* stores data in TA Assist:
  * all `Student` objects are contained in a `UniqueList` object.
  * all `ModuleClass` objects are also contained in a `UniqueList` object.
* stores the currently 'selected' `Student` objects (e.g., results of a search query) as a separate _filtered_ list which is exposed to outsiders as an unmodifiable `ObservableList<Student>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* stores the currently 'focused' `ModuleClass` object.
* stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as a `ReadOnlyUserPref` objects.
* does not depend on any of the other three components (i.e. `Ui`, `Logic` and `Storage`) as the `Model` represents data entities of the domain, they should make sense on their own without depending on other components.

The `UniqueList` class is a generic class that stores a collection of unique elements. In TA Assist, a `UniqueList` stores either all the `Student` objects or all the `ModuleClass` objects.

<img src="images/TaAssistObjectDiagram.png" width="600"/>

Each `Student` object stores all module-class-related data in a `StudentModuleData` object. All `Session` objects corresponding to a `Student` are stored in a `SessionData` object.

<img src="images/StudentAndModuleClassDiagram.png" width="600"/>

### Storage component

**API** : [`Storage.java`](https://github.com/AY2223S1-CS2103T-T12-1/tp/blob/master/src/main/java/seedu/taassist/storage/Storage.java)

<img src="images/StorageClassDiagram.png" width="550" />

The `Storage` component,
* can save both TA Assist data and user preference data in json format, and read them back into corresponding objects.
* inherits from both `TaAssistStorage` and `UserPrefStorage`, which means it can be treated as either one (if only the functionality of only one is needed).
* depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects that belong to the `Model`)

### Common classes

Classes used by multiple components are in the `seedu.taassist.commons` package.

--------------------------------------------------------------------------------------------------------------------

## **Implementation**


### Assigning students to a class
<img src="images/AssignCommandSequenceDiagram.png" width="700" />

DESCRIPTION TODO

### Tracking the state of focus mode
The state of focus mode is tracked by `ModelManager`, which stores the current focused `ModuleClass` (`focusedClass`, as seen in the [class diagram for `Model`](#model-component)).
When `focusedClass` is `null`, it indicates that focus mode is inactive. `ModuleManager` returns the state of the focus mode via the following methods:
* `ModelManager#isInFocusMode()` - Checks whether focus mode is active.
* `ModelManager#getFocusedClass()` - Returns the current `ModuleClass` in focus.

The following methods in `ModelManager` toggles the state of the focus mode:
* `ModelManager#enterFocusMode(ModuleClass classToFocus)` - Sets focus mode to be active.
  * This is achieved by setting `focusedClass` to be a non-null `ModuleClass` object.
  * This method assumes that `focusedClass` is an existing `ModuleClass` in `TaAssist`.

* `ModuleClass#exitFocusMode()` - Sets focus mode to be inactive.
  * This is achieved by setting `focusedClass` to be `null`.

The above methods are also exposed to the `Model` interface.

The `Logic` component calls these methods in `Model` to execute commands that require access to the state of the focus mode.

For example, the following sequence diagram shows how the `focus` command activates focus mode with the `CS1101S` module class:

<img src="images/FocusCommandSequenceDiagram.png" width="700" />

On the other hand, the `unfocus` command deactivates focus mode by setting `focusedClass` to `null`.

--------------------------------------------------------------------------------------------------------------------

## **Documentation, logging, testing, configuration, dev-ops**

* [Documentation guide](Documentation.md)
* [Testing guide](Testing.md)
* [Logging guide](Logging.md)
* [Configuration guide](Configuration.md)
* [DevOps guide](DevOps.md)

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Requirements**

### Product scope

**Target user profile**:

* works as teaching assistants.
* has a need to keep track of students' grades, attendance, and work submission status of relevant modules.
* prefers desktop apps over other types.
* can type fast.
* prefers typing to mouse interactions.
* is reasonably comfortable using CLI apps.

**Value proposition**:
* Fast management of students' grades over the typical GUI-driven app.
* Easy navigation and batch processing with the help of filter and search functionality.
* CSV file generation of student data.

### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​         | I want to …​                                               | So that I can…​                                                  |
|---------|-----------------|------------------------------------------------------------|------------------------------------------------------------------|
| `* * *` | New user        | Get help for specific commands                             | Learn the complete features of a command and know how to use it. |
| `* * *` | User            | Add students to my class                                   | Decide who to have in my class.                                  |
| `* * *` | User            | Delete students from my class                              | Decide who to have in my class.                                  |
| `* * *` | User            | Give participation points to students                      | Keep track of their participation in class.                      |
| `* * *` | User            | Take attendance of my students                             | Keep track of their class attendance.                            |
| `* * *` | User            | Delete students from TA Assist                             | Keep my list of students concise.                                |
| `* * *` | User            | View all my classes                                        | See what classes I am teaching.                                  |
| `* *  ` | Infrequent user | Remember the last used commands                            | Quickly find the commands that I need.                           |
| `* *  ` | An expert user  | Create macros to perform multiple tasks                    | Be more efficient at using the system.                           |
| `* *  ` | User            | Edit students' information                                 | Easily and quickly update their information.                     |
| `* * *` | User            | Create assignments that contribute to CA components        | Assign grades to assignments done by students.                   |
| `* *  ` | User            | Assign weightage to my created assignments                 | Estimate the overall performance of my students.                 |
| `* * *` | User            | Change participation marks previously given to my students | Correctly and accurately reflect the marks for my students.      |

*{More to be added}*

### Use cases

(For all use cases below, the **System** is `TA Assist` and the **Actor** is the `User`, unless specified otherwise)

**Use case: UC1 - Enter focus mode for a class**

**MSS**
1. User requests to enter focus mode for a class.
2. TA Assist enters focus mode for the class.
3. TA Assist indicates that the user is in the focus mode for the class.
4. TA Assist lists all the students in the class.
5. TA Assist lists all the sessions in the class.

    Use case ends.

**Extensions**
* *a. User requests to exit focus mode.
    * *a1. TA Assist exits focus mode.
    * *a2. TA Assist indicates that the user has exited focus mode.

      Use case ends.

* 2a. The class does not exist.
  * 2a1. TA Assist tells the user that the class does not exist.
  
    Use case ends

* 4a. The list of students is empty.

  Use case resumes at step 5.

* 5a. The list of sessions is empty.

  Use case ends.

**Use case: UC2 - Give a student a score for a session in a class**

**MSS**
1. User requests to <u>enter focus mode for a class (UC1)</u>.
2. User requests to allocate a score for a specific student in the class, for a specific session.
3. TA Assist updates the score for the student.
4. TA Assist indicates that the score for the student has been updated.

   Use case ends.

**Extensions**
* 3a. The student does not exist in the class.
  * 3a1. TA Assist tells the user that the student does not exist.

    Use case ends.

* 3b. The session does not exist in the class.
  * 3b1. TA Assist tells the user that the session does not exist.

    Use case ends.

**Use case: UC3 - Add a student**

**MSS**
1. User requests to add a student with the specified name and information.
2. TA Assist creates a student with the given name.
3. TA Assist indicates that the student has been added.

   Use case ends.

**Extensions**
* 2a. The student name is empty.
  * 2a1. TA Assist shows an error message.

    Use case ends.

* 2b. The student already exists.
  * 2b1. TA Assist tells the user that the student already exists.

    Use case ends.

* 2c. The student's phone number, email address, home address and/or class is/are provided.
  * 2c1. TA Assist creates a student with a name along with these information.

    Use case resumes at step 3.

**Use case: UC4 - Delete a student**

**MSS**
1. User requests to list students.
2. TA Assist shows a list of students.
3. User requests to delete a specific student in the list.
4. TA Assist deletes the student.
5. TA Assist indicates that the student has been deleted.

   Use case ends.

**Extensions**
* 2a. The list is empty.

  Use case ends.

* 3a. The given index is invalid.
  * 3a1. TA Assist indicates that the index is invalid.

    Use case resumes at step 2.

**Use case: UC5 - Create a class**

**MSS**
1. User requests to create a new class with the specified class name.
2. TA Assist creates a new class with the given class name.
3. TA Assist indicates that the class has been created.

   Use case ends.

**Extensions**
* 2a. The class already exists.
  * 2a1. TA Assist tells the user that the class already exists.

    Use case ends.

* 2b. The class name is empty.
  * 2b1. TA Assist shows an error message.

    Use case ends.

**Use case: UC6 - Create a session in a class**

**MSS**
1. User requests to <u>enter focus mode for a class (UC1)</u>.
2. User requests to create a new session for the class.
3. TA Assist creates a new session for the class.
4. TA Assist indicates that the session has been created.

    Use case ends.

**Extensions**
* 3a. The session already exists.
  * 3a1. TA Assist tells the user that the session already exists.

    Use case ends.

* 3b. The session name is empty.
    * 3b1. TA Assist shows an error message.

      Use case ends.

*{More to be added}*
### Non-Functional Requirements

1. Should work on any mainstream OS with Java 11 or above installed.
2. Should work without requiring an installer.
3. Should not depend on a remote server.
4. Should not use a DBMS (Database Management System).
5. Should store data in a human editable text file.
6. Product JAR file should not exceed 100MB.
7. A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.
8. GUI should be usable for screen resolutions of 1280x720 and higher with 150% scaling.
9. GUI should work well for screen resolutions of 1920x1080 and higher with 100-125% scaling.

### Glossary

* **Mainstream OS**: Windows, Linux, Unix, and macOS

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Instructions for manual testing**

Given below are instructions to test the app manually.

<div markdown="span" class="alert alert-info">:information_source: **Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.

</div>

### Launch and shutdown

1. Initial launch

   1. Download the jar file and copy into an empty folder

   2. Double-click the jar file Expected: Shows the GUI with a set of sample contacts. The window size may not be optimum.

2. Saving window preferences

   1. Resize the window to an optimum size. Move the window to a different location. Close the window.

   2. Re-launch the app by double-clicking the jar file.<br>
       Expected: The most recent window size and location is retained.

3. _{ more test cases …​ }_

### Deleting a student

1. Deleting a student while all students are being shown

   1. Prerequisites: List all students using the `list` command. Multiple students in the list.

   2. Test case: `delete 1`<br>
      Expected: First contact is deleted from the list. Details of the deleted contact shown in the status message. Timestamp in the status bar is updated.

   3. Test case: `delete 0`<br>
      Expected: No student is deleted. Error details shown in the status message. Status bar remains the same.

   4. Other incorrect delete commands to try: `delete`, `delete x`, `...` (where x is larger than the list size)<br>
      Expected: Similar to previous.

2. _{ more test cases …​ }_

### Saving data

1. Dealing with missing/corrupted data files

   1. _{explain how to simulate a missing/corrupted file, and the expected behavior}_

2. _{ more test cases …​ }_
