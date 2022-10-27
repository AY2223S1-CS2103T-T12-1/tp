package seedu.taassist.logic.commands.result;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

/**
 * Represents the result of a command execution that require actions from UI.
 */
public class UiCommandResult extends CommandResult {

    private final UiAction uiAction;

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser} and {@code UiAction}.
     */
    public UiCommandResult(String feedbackToUser, UiAction uiAction) {
        super(feedbackToUser);
        requireNonNull(uiAction);
        this.uiAction = uiAction;
    }

    public UiAction getUiAction() {
        return uiAction;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UiCommandResult)) {
            return false;
        }

        UiCommandResult otherCommandResult = (UiCommandResult) other;
        return this.getFeedbackToUser().equals(otherCommandResult.getFeedbackToUser())
                && this.uiAction == otherCommandResult.uiAction;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getFeedbackToUser(), uiAction);
    }

    /**
     * Represents the different actions the UI can perform.
     */
    public enum UiAction {
        UI_HELP,
        UI_EXIT,
        UI_FOCUS,
        UI_UNFOCUS;
    }
}
