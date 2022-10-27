package seedu.taassist.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.taassist.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.taassist.logic.parser.CliSyntax.PREFIX_MODULE_CLASS;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.taassist.logic.commands.exceptions.CommandException;
import seedu.taassist.model.Model;
import seedu.taassist.model.moduleclass.ModuleClass;

/**
 * Deletes a moduleClass identified using it's className from TA-Assist.
 */
public class DeletecCommand extends Command {

    public static final String COMMAND_WORD = "deletec";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the class(es) identified by the class name(s).\n"
            + "Parameters: "
            + PREFIX_MODULE_CLASS + "CLASS_NAME... (case sensitive)\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_MODULE_CLASS + "CS1101S" + " "
            + PREFIX_MODULE_CLASS + "CS1231S";

    public static final String MESSAGE_SUCCESS = "Deleted class(es): %1$s";
    public static final String MESSAGE_MODULE_CLASSES_DOES_NOT_EXIST =
            "The class(es) do(es) not exist in TA-Assist: %1$s";

    private final Set<ModuleClass> moduleClasses;

    /**
     * Creates a DeletecCommand to delete the given classes.
     */
    public DeletecCommand(Set<ModuleClass> moduleClasses) {
        requireAllNonNull(moduleClasses);
        this.moduleClasses = moduleClasses;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Set<ModuleClass> existingClasses = new HashSet<>();
        Set<ModuleClass> nonExistentClasses = new HashSet<>();

        for (ModuleClass moduleClass : moduleClasses) {
            if (model.hasModuleClass(moduleClass)) {
                existingClasses.add(moduleClass);
            } else {
                nonExistentClasses.add(moduleClass);
            }
        }
        model.removeModuleClasses(existingClasses);

        String message = getCommandMessage(existingClasses, nonExistentClasses);
        return new CommandResult(message);
    }

    public static String getCommandMessage(Set<ModuleClass> existingClasses, Set<ModuleClass> nonExistentClasses) {
        requireAllNonNull(existingClasses);
        requireAllNonNull(nonExistentClasses);

        StringBuilder outputString = new StringBuilder();
        if (!existingClasses.isEmpty()) {
            outputString.append(getClassesDeletedMessage(existingClasses)).append("\n");
        }

        if (!nonExistentClasses.isEmpty()) {
            outputString.append(getNonExistentClassesMessage(nonExistentClasses)).append("\n");
        }

        // remove trailing newline character
        outputString.setLength(outputString.length() - 1);
        return outputString.toString();
    }

    private static String getClassesDeletedMessage(Set<ModuleClass> deletedClasses) {
        requireAllNonNull(deletedClasses);
        String deletedClassesStr = deletedClasses.stream().map(Object::toString).collect(Collectors.joining(" "));
        return String.format(MESSAGE_SUCCESS, deletedClassesStr);
    }

    private static String getNonExistentClassesMessage(Set<ModuleClass> nonExistentClasses) {
        requireAllNonNull(nonExistentClasses);
        String nonExistentClassesStr = nonExistentClasses.stream().map(Object::toString)
                .collect(Collectors.joining(" "));
        return String.format(MESSAGE_MODULE_CLASSES_DOES_NOT_EXIST, nonExistentClassesStr);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeletecCommand // instanceof handles nulls
                && moduleClasses.equals(((DeletecCommand) other).moduleClasses)); // state check
    }
}

