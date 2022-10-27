package seedu.taassist.logic.commands.result;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

import seedu.taassist.logic.commands.exceptions.StorageActionException;
import seedu.taassist.logic.commands.storageaction.StorageAction;
import seedu.taassist.storage.Storage;

/**
 * Represents the result of a command execution that require actions from UI.
 */
public class StorageCommandResult extends CommandResult {

    public static final String DEFAULT_FEEDBACK_TO_USER = "";

    private final StorageAction storageAction;

    /**
     * Constructs a {@code StorageCommandResult} with the specified {@code StorageAction} and
     * default {@code feedbackToUser}.
     */
    public StorageCommandResult(StorageAction storageAction) {
        super(DEFAULT_FEEDBACK_TO_USER);
        requireNonNull(storageAction);
        this.storageAction = storageAction;
    }

    /**
     * Perform the {@code StorageAction} on the specified {@code Storage} and return the result
     * of the action.
     */
    public CommandResult performStorageAction(Storage storage) throws StorageActionException {
        StorageActionResult storageActionResult = storageAction.act(storage);
        return storageActionResult;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof StorageCommandResult)) {
            return false;
        }

        StorageCommandResult otherCommandResult = (StorageCommandResult) other;
        return getFeedbackToUser().equals(otherCommandResult.getFeedbackToUser())
                && storageAction.equals(otherCommandResult.storageAction);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getFeedbackToUser(), storageAction);
    }
}
