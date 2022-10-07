package seedu.taassist.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.taassist.model.ReadOnlyTaAssist;
import seedu.taassist.model.TaAssist;
import seedu.taassist.model.moduleclass.ModuleClass;
import seedu.taassist.model.student.Address;
import seedu.taassist.model.student.Email;
import seedu.taassist.model.student.Name;
import seedu.taassist.model.student.Phone;
import seedu.taassist.model.student.Student;

/**
 * Contains utility methods for populating {@code TaAssist} with sample data.
 */
public class SampleDataUtil {
    public static Student[] getSampleStudents() {
        return new Student[] {
            new Student(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40"),
                getModuleClassSet("friends")),
            new Student(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                getModuleClassSet("colleagues", "friends")),
            new Student(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                getModuleClassSet("neighbours")),
            new Student(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                getModuleClassSet("family")),
            new Student(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new Address("Blk 47 Tampines Street 20, #17-35"),
                getModuleClassSet("classmates")),
            new Student(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new Address("Blk 45 Aljunied Street 85, #11-31"),
                getModuleClassSet("colleagues"))
        };
    }

    public static ReadOnlyTaAssist getSampleTaAssist() {
        TaAssist sampleTaAssist = new TaAssist();
        for (Student sampleStudent : getSampleStudents()) {
            addModuleClasses(sampleTaAssist, sampleStudent);
            sampleTaAssist.addStudent(sampleStudent);
        }
        return sampleTaAssist;
    }

    /**
     * Adds the modules classes of a {@code Student} to a {@code TaAssist} if they do not already exist.
     *
     * @param taAssist The {@code TaAssist} to add the {@code ModuleClass} to.
     * @param student The {@code Student} to get the {@code ModuleClass} from.
     */
    private static void addModuleClasses(TaAssist taAssist, Student student) {
        for (ModuleClass moduleClass : student.getModuleClasses()) {
            if (!taAssist.hasModuleClass(moduleClass)) {
                taAssist.addModuleClass(moduleClass);
            }
        }
    }

    /**
     * Returns a {@code ModuleClass} set containing the list of strings given.
     */
    public static Set<ModuleClass> getModuleClassSet(String... strings) {
        return Arrays.stream(strings)
                .map(ModuleClass::new)
                .collect(Collectors.toSet());
    }

}
