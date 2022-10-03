package seedu.taassist.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.taassist.commons.exceptions.IllegalValueException;
import seedu.taassist.model.ReadOnlyTaAssist;
import seedu.taassist.model.TaAssist;
import seedu.taassist.model.student.Student;

/**
 * An Immutable TaAssist that is serializable to JSON format.
 */
@JsonRootName(value = "taassist")
class JsonSerializableTaAssist {

    public static final String MESSAGE_DUPLICATE_STUDENT = "Students list contains duplicate student(s).";

    private final List<JsonAdaptedStudent> students = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableTaAssist} with the given students.
     */
    @JsonCreator
    public JsonSerializableTaAssist(@JsonProperty("students") List<JsonAdaptedStudent> students) {
        this.students.addAll(students);
    }

    /**
     * Converts a given {@code ReadOnlyTaAssist} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableTaAssist}.
     */
    public JsonSerializableTaAssist(ReadOnlyTaAssist source) {
        students.addAll(source.getStudentList().stream().map(JsonAdaptedStudent::new).collect(Collectors.toList()));
    }

    /**
     * Converts this TaAssist into the model's {@code TaAssist} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public TaAssist toModelType() throws IllegalValueException {
        TaAssist taAssist = new TaAssist();
        for (JsonAdaptedStudent jsonAdaptedStudent : students) {
            Student student = jsonAdaptedStudent.toModelType();
            if (taAssist.hasStudent(student)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_STUDENT);
            }
            taAssist.addStudent(student);
        }
        return taAssist;
    }

}
