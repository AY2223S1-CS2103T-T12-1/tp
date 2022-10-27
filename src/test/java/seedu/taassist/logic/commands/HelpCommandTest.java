package seedu.taassist.logic.commands;

import static seedu.taassist.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.taassist.logic.commands.HelpCommand.SHOWING_HELP_MESSAGE;
import static seedu.taassist.logic.commands.result.UiCommandResult.UiAction.UI_HELP;

import org.junit.jupiter.api.Test;

import seedu.taassist.logic.commands.result.CommandResult;
import seedu.taassist.logic.commands.result.UiCommandResult;
import seedu.taassist.model.Model;
import seedu.taassist.model.ModelManager;

public class HelpCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_help_success() {
        CommandResult expectedCommandResult = new UiCommandResult(SHOWING_HELP_MESSAGE, UI_HELP);
        assertCommandSuccess(new HelpCommand(), model, expectedCommandResult, expectedModel);
    }
}
