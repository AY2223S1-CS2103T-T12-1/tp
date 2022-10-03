package seedu.taassist.model;

import javafx.collections.ObservableList;
import seedu.taassist.model.student.Student;

/**
 * Unmodifiable view of an TaAssist
 */
public interface ReadOnlyTaAssist {

    /**
     * Returns an unmodifiable view of the students list.
     * This list will not contain any duplicate students.
     */
    ObservableList<Student> getStudentList();

}
