package seedu.taassist.logic.commands;

import static seedu.taassist.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.taassist.logic.commands.ExitCommand.MESSAGE_EXIT_ACKNOWLEDGEMENT;
import static seedu.taassist.logic.commands.result.UiCommandResult.UiAction.UI_EXIT;

import org.junit.jupiter.api.Test;

import seedu.taassist.logic.commands.result.CommandResult;
import seedu.taassist.logic.commands.result.UiCommandResult;
import seedu.taassist.model.Model;
import seedu.taassist.model.ModelManager;

public class ExitCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_exit_success() {
        CommandResult expectedCommandResult = new UiCommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT,
                UI_EXIT);
        assertCommandSuccess(new ExitCommand(), model, expectedCommandResult, expectedModel);
    }
}
