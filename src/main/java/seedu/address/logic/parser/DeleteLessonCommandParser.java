package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteLessonCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * A DeleteLessonCommand Parser to parse the command for deletion of a lesson.
 */
public class DeleteLessonCommandParser implements Parser<DeleteLessonCommand> {

    /**
     * To parse the deletion command.
     * @param args The command.
     * @return The DeleteLessonCommand object.
     * @throws ParseException If an error while parsing.
     */
    public DeleteLessonCommand parse(String args) throws ParseException {
        String[] parts = args.trim().split("\\|");

        try {
            Index index = ParserUtil.parseIndex(parts[0]);
            Index lessonIndex = ParserUtil.parseIndex(parts[1]);
            return new DeleteLessonCommand(lessonIndex, index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteLessonCommand.MESSAGE_USAGE), pe);
        }
    }
}
