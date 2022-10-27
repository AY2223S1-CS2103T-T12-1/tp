package seedu.taassist.logic.commands.result;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

import seedu.taassist.logic.commands.storageaction.StorageAction;

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

    public StorageAction getStorageAction() {
        return storageAction;
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
