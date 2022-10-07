package seedu.taassist.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.taassist.commons.core.index.Index;
import seedu.taassist.commons.util.StringUtil;
import seedu.taassist.logic.parser.exceptions.ParseException;
import seedu.taassist.model.moduleclass.ModuleClass;
import seedu.taassist.model.student.Address;
import seedu.taassist.model.student.Email;
import seedu.taassist.model.student.Name;
import seedu.taassist.model.student.Phone;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses {@code oneBasedIndices} into an {@code List<Index>} and returns it. Leading and trailing whitespaces will
     * be trimmed.
     * @throws ParseException if the any of the specified indices is invalid (not non-zero unsigned integer).
     */
    public static List<Index> parseIndices(String oneBasedIndices) throws ParseException {
        List<Index> indices = new ArrayList<>();
        String[] stringIndices = oneBasedIndices.trim().split(" ");
        for (String stringIndex : stringIndices) {
            if (!StringUtil.isNonZeroUnsignedInteger(stringIndex)) {
                throw new ParseException(MESSAGE_INVALID_INDEX);
            }
            indices.add(Index.fromOneBased(Integer.parseInt(stringIndex)));
        }
        return indices;
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String phone} into a {@code Phone}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code phone} is invalid.
     */
    public static Phone parsePhone(String phone) throws ParseException {
        requireNonNull(phone);
        String trimmedPhone = phone.trim();
        if (!Phone.isValidPhone(trimmedPhone)) {
            throw new ParseException(Phone.MESSAGE_CONSTRAINTS);
        }
        return new Phone(trimmedPhone);
    }

    /**
     * Parses a {@code String address} into an {@code Address}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code address} is invalid.
     */
    public static Address parseAddress(String address) throws ParseException {
        requireNonNull(address);
        String trimmedAddress = address.trim();
        if (!Address.isValidAddress(trimmedAddress)) {
            throw new ParseException(Address.MESSAGE_CONSTRAINTS);
        }
        return new Address(trimmedAddress);
    }

    /**
     * Parses a {@code String email} into an {@code Email}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static Email parseEmail(String email) throws ParseException {
        requireNonNull(email);
        String trimmedEmail = email.trim();
        if (!Email.isValidEmail(trimmedEmail)) {
            throw new ParseException(Email.MESSAGE_CONSTRAINTS);
        }
        return new Email(trimmedEmail);
    }

    /**
     * Parses a {@code String moduleClass} into a {@code ModuleClass}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code moduleClass} is invalid.
     */
    public static ModuleClass parseModuleClass(String moduleClass) throws ParseException {
        requireNonNull(moduleClass);
        String trimmedModuleClass = moduleClass.trim();
        if (!ModuleClass.isValidModuleClassName(trimmedModuleClass)) {
            throw new ParseException(ModuleClass.MESSAGE_CONSTRAINTS);
        }
        return new ModuleClass(trimmedModuleClass);
    }

    /**
     * Parses {@code Collection<String> moduleClasses} into a {@code Set<ModuleClass>}.
     */
    public static Set<ModuleClass> parseModuleClasses(Collection<String> moduleClasses) throws ParseException {
        requireNonNull(moduleClasses);
        final Set<ModuleClass> moduleClassSet = new HashSet<>();
        for (String moduleClassName : moduleClasses) {
            moduleClassSet.add(parseModuleClass(moduleClassName));
        }
        return moduleClassSet;
    }

    /**
     * Returns true if the {@code prefix} does not contain empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    public static boolean isPrefixPresent(ArgumentMultimap argumentMultimap, Prefix prefix) {
        return argumentMultimap.getValue(prefix).isPresent();
    }
}
