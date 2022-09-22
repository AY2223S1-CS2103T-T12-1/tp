package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.Remark;

/**
 * Edits the remarks of an existing person in the address book.
 */
public class RemarkCommand extends Command {

    public static final String COMMAND_WORD = "remark";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Modified the remark of the person at the provided\n"
            + "index number in the list.\n"
            + "Existing remarks will be overwritten.\n"
            + "If remark is not provided, previous remarks will be removed.\n"
            + "Parameters: INDEX (must be a positive integer) [r/REMARK]\n"
            + "Example: " + COMMAND_WORD + " 1 r/Hates swimming.";
    public static final String MESSAGE_REMARK_PERSON_SUCCESS = "Edited %1$s's remark: %2$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book.";

    private final Index index;
    private final String remark;

    /**
     * @param index of the person in the filtered person list to be remarked
     * @param remark the person's remark
     */
    public RemarkCommand(Index index, String remark) {
        requireAllNonNull(index, remark);
        this.index = index;
        this.remark = remark;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        Remark remark = new Remark(this.remark);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());
        Person editedPerson = new Person(personToEdit.getName(), personToEdit.getPhone(),
                personToEdit.getEmail(), personToEdit.getAddress(), remark, personToEdit.getTags());

        if (!personToEdit.isSamePerson(editedPerson) && model.hasPerson(editedPerson)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_REMARK_PERSON_SUCCESS, editedPerson.getName(),
                editedPerson.getRemark()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof RemarkCommand)) {
            return false;
        }
        RemarkCommand oth = (RemarkCommand) other;
        return oth.index.equals(index) && oth.remark.equals(remark);
    }

}
