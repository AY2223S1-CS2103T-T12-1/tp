package seedu.taassist.model;

import java.nio.file.Path;
import java.util.Collection;
import java.util.function.Predicate;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import seedu.taassist.commons.core.GuiSettings;
import seedu.taassist.model.moduleclass.ModuleClass;
import seedu.taassist.model.student.Student;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Student> PREDICATE_SHOW_ALL_STUDENTS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' TaAssist file path.
     */
    Path getTaAssistFilePath();

    /**
     * Sets the user prefs' TaAssist file path.
     */
    void setTaAssistFilePath(Path taAssistFilePath);

    /**
     * Replaces TaAssist data with the data in {@code taAssist}.
     */
    void setTaAssist(ReadOnlyTaAssist taAssist);

    /** Returns the TaAssist */
    ReadOnlyTaAssist getTaAssist();

    /**
     * Returns true if a student with the same identity as {@code student} exists in TA-Assist.
     */
    boolean hasStudent(Student student);

    /**
     * Deletes the given student.
     * The student must exist in TA-Assist.
     */
    void deleteStudent(Student target);

    /**
     * Adds the given student.
     * {@code student} must not already exist in TA-Assist.
     */
    void addStudent(Student student);

    /**
     * Replaces the given student {@code target} with {@code editedStudent}.
     * {@code target} must exist in TA-Assist.
     * The student identity of {@code editedStudent} must not be the same as another existing student in the
     * TaAssist.
     */
    void setStudent(Student target, Student editedStudent);

    /** Returns an unmodifiable view of the filtered student list */
    ObservableList<Student> getFilteredStudentList();

    /**
     * Updates the filter of the filtered student list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredStudentList(Predicate<Student> predicate);

    /**
     * Returns true if a class with the same identity as {@code moduleClass} exists in TA-Assist.
     */
    boolean hasModuleClass(ModuleClass moduleClass);

    /**
     * Deletes the given class.
     * The class must exist in TA-Assist.
     */
    void deleteModuleClass(ModuleClass moduleClass);

    /**
     * Deletes multiple classes.
     * The classes must exist in TA-Assist.
     */
    void deleteModuleClasses(Collection<ModuleClass> moduleClasses);

    /**
     * Adds the given class.
     * {@code moduleClass} must not already exist in TA-Assist.
     */
    void addModuleClass(ModuleClass moduleClass);

    /** Returns an unmodifiable view of the module class list */
    ObservableList<ModuleClass> getModuleClassList();

    /** Checks if all {@code moduleClass} in {@code moduleClasses} exists */
    boolean areAllExistingModuleClasses(Collection<ModuleClass> moduleClasses);

    /** Enters focus mode. */
    void enterFocusMode(ModuleClass classToFocus);

    /** Exits focus mode. */
    void exitFocusMode();

    /** Checks if the focus mode is active. */
    boolean isInFocusMode();

    /** Returns module class currently in focus. */
    ModuleClass getFocusedClass();

    SimpleStringProperty getFocusLabelProperty();
}
